package ni.org.ics.lab.inventario.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.Box;
import ni.org.ics.lab.inventario.domain.Rack;
import ni.org.ics.lab.inventario.domain.relationships.UserCenter;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto Box
 * 
 * @author William Aviles
 * 
 **/

@Service("boxService")
@Transactional
public class BoxService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Box> getBoxes(String rackCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Box b where b.boxRack.rackCode =:rackCode order by b.boxPosition");
		query.setParameter("rackCode",rackCode);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Box> getActiveBoxes(String rackCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Box b where b.boxRack.rackCode =:rackCode and b.pasive ='0' order by b.boxPosition");
		query.setParameter("rackCode",rackCode);
		return query.list();
	}
	
	public List<String> getAvailablePos(String rackCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Rack r where " +
				"r.rackCode =:rackCode");
		query.setParameter("rackCode",rackCode);
		Rack rack = (Rack) query.uniqueResult();
		List<String> posiciones =  new ArrayList<String>();
		List<Box> boxes = getActiveBoxes(rackCode);
		for(Integer i = 1; i<=rack.getRackCapacity(); i++){
			posiciones.add(i.toString());
		}
		for(Box box:boxes){
			String o = String.valueOf(box.getBoxPosition());
			posiciones.remove(o);
		}
		return posiciones;
	}
	
	/**
	 * Regresa un Box
	 * 
	 * @return un <code>Box</code>
	 */

	public Box getBox(String boxCode,String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Box b where " +
				"b.boxCode =:boxCode");
		query.setParameter("boxCode",boxCode);
		Box box = (Box) query.uniqueResult();
		if (box!=null){
			String centerCode = box.getBoxRack().getRackEquip().getEquipRoom().getRoomCenter().getCenterCode();
			query = session.createQuery("FROM UserCenter uc where " +
					"uc.userCenterId.center =:centerCode and uc.userCenterId.username =:username and uc.pasive ='0'");
			query.setParameter("centerCode",centerCode);
			query.setParameter("username",username);
			UserCenter usercentro = (UserCenter) query.uniqueResult();
			if (usercentro!=null){
				return box;
			}
		}
		return null;
	}
	
	/**
	 * Regresa un Box
	 * 
	 * @return un <code>Box</code>
	 */

	public Box getBoxxName(String boxName,String centerCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Box b where " +
				"b.boxName =:boxName and b.boxRack.rackEquip.equipRoom.roomCenter.centerCode =:centerCode");
		query.setParameter("boxName",boxName);
		query.setParameter("centerCode",centerCode);
		Box box = (Box) query.uniqueResult();
		return box;
	}
	
	/**
	 * Regresa un Box
	 * 
	 * @return un <code>Box</code>
	 */

	@SuppressWarnings("unchecked")
	public List<Box> getBoxesxName(String boxName,String centerCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Box b where " +
				"b.boxName like:boxName and b.boxRack.rackEquip.equipRoom.roomCenter.centerCode =:centerCode");
		query.setParameter("boxName",'%'+boxName+'%');
		query.setParameter("centerCode",centerCode);
		List<Box> box = query.list();
		return box;
	}
	
	
	/**
	 * Regresa un Box
	 * 
	 * @return un <code>Box</code>
	 */

	@SuppressWarnings("unchecked")
	public List<Object[]> getBoxSugerida(String boxStudy, String centerCode, String alicTypeName, String alicTypeUse, String alicTypeTemp, String alicTypeResult) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select inv_cajas.CODIGO_CAJA, inv_cajas.capacidad - count(t.codigo_caja) AS disponibles "
				+ "from inv_cajas LEFT JOIN (select * from inv_alicuotas where inv_alicuotas.pasivo='0') as t ON inv_cajas.codigo_caja = t.codigo_caja "
				+ "INNER JOIN inv_racks ON inv_cajas.CODIGO_RACK = inv_racks.CODIGO_RACK "
				+ "INNER JOIN inv_equipos ON inv_racks.CODIGO_EQUIPO = inv_equipos.CODIGO_EQUIPO "
				+ "INNER JOIN inv_cuartos ON inv_equipos.CODIGO_CUARTO = inv_cuartos.CODIGO_CUARTO "
				+ "INNER JOIN inv_centros ON inv_cuartos.CODIGO_CENTRO = inv_centros.CODIGO_CENTRO "
				+ "where inv_cajas.pasivo='0' and inv_cajas.CODIGO_ESTUDIO='" + boxStudy + "' and inv_centros.CODIGO_CENTRO='" + centerCode + "' "
				+ "and (inv_cajas.TIPO_ALICUOTAS like '%,"+alicTypeName+",%' "
				+ "or inv_cajas.TIPO_ALICUOTAS like '%"+alicTypeName+",%' "
				+ "or inv_cajas.TIPO_ALICUOTAS like '%,"+alicTypeName+"%' "
				+ "or inv_cajas.TIPO_ALICUOTAS = '"+alicTypeName+"') "
				+ "and inv_cajas.USO_ALICUOTAS='" + alicTypeUse + "' "
				+ "and inv_cajas.TIPO_RESULTADO='" + alicTypeResult + "' "
				+ "and inv_cajas.TEMPERATURA='" + alicTypeTemp + "' "
				+ "GROUP BY inv_cajas.codigo_caja HAVING (((count(t.codigo_caja))<max(inv_cajas.capacidad))) "
				+ "ORDER BY inv_equipos.PRIORIDAD, inv_racks.PRIORIDAD, inv_cajas.PRIORIDAD, inv_cajas.NOMBRE_CAJA");
		List<Object[]> resultados = query.list();
		return resultados;
	}
	
	//Regresa espacios ocupados en la caja
	public Long getEspaciosOcupados(String boxCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select count(*) from Aliquot ali where ali.pasive='0' and ali.aliBox.boxCode =:boxCode");
		query.setParameter("boxCode", boxCode);
		return (Long) query.uniqueResult();
	}
	
	
	/**
	 * Guarda un Box
	 * 
	 * 
	 */
	public void saveBox(Box box) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(box);
	}

	/**
	 * Regresa un Box
	 *
	 * @return un <code>Box</code>
	 */

	/*public Box getBox1(String boxCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Box b where " +
				"b.boxCode =:boxCode");
		query.setParameter("boxCode",boxCode);
		Box box = (Box) query.uniqueResult();

		return box;
	}*/
	
}
