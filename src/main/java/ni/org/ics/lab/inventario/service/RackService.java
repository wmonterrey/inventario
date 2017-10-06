package ni.org.ics.lab.inventario.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.Equipment;
import ni.org.ics.lab.inventario.domain.Rack;
import ni.org.ics.lab.inventario.domain.relationships.UserCenter;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto Rack
 * 
 * @author William Aviles
 * 
 **/

@Service("rackService")
@Transactional
public class RackService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Rack> getRacks(String equipCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Rack r where r.rackEquip.equipCode =:equipCode order by r.rackPosition");
		query.setParameter("equipCode",equipCode);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Rack> getActiveRacks(String equipCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Rack r where r.rackEquip.equipCode =:equipCode and r.pasive ='0' order by r.rackPosition");
		query.setParameter("equipCode",equipCode);
		return query.list();
	}
	
	public List<String> getAvailablePos(String equipCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Equipment e where " +
				"e.equipCode =:equipCode");
		query.setParameter("equipCode",equipCode);
		Equipment equipo = (Equipment) query.uniqueResult();
		List<String> posiciones =  new ArrayList<String>();
		List<Rack> racks = getActiveRacks(equipCode);
		for(Integer i = 1; i<=equipo.getEquipCapacity(); i++){
			posiciones.add(i.toString());
		}
		for(Rack rack:racks){
			String o = String.valueOf(rack.getRackPosition());
			posiciones.remove(o);
		}
		return posiciones;
	}

	
	/**
	 * Regresa un Rack
	 * 
	 * @return un <code>Rack</code>
	 */

	public Rack getRack(String rackCode,String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Rack r where " +
				"r.rackCode =:rackCode");
		query.setParameter("rackCode",rackCode);
		Rack rack = (Rack) query.uniqueResult();
		if (rack!=null){
			String centerCode = rack.getRackEquip().getEquipRoom().getRoomCenter().getCenterCode();
			query = session.createQuery("FROM UserCenter uc where " +
					"uc.userCenterId.center =:centerCode and uc.userCenterId.username =:username and uc.pasive ='0'");
			query.setParameter("centerCode",centerCode);
			query.setParameter("username",username);
			UserCenter usercentro = (UserCenter) query.uniqueResult();
			if (usercentro!=null){
				return rack;
			}
		}
		return null;
	}
	
	
	/**
	 * Regresa un Rack
	 * 
	 * @return un <code>Rack</code>
	 */

	public Rack getRackxName(String rackName,String centerCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Rack r where " +
				"r.rackName =:rackName and r.rackEquip.equipRoom.roomCenter.centerCode =:centerCode");
		query.setParameter("rackName",rackName);
		query.setParameter("centerCode",centerCode);
		Rack rack = (Rack) query.uniqueResult();
		return rack;
	}
	
	
	/**
	 * Regresa lista de racks
	 * 
	 * @return un <code>Rack</code>
	 */

	@SuppressWarnings("unchecked")
	public List<Rack> getRacksxName(String rackName,String centerCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Rack r where " +
				"r.rackName like:rackName and r.rackEquip.equipRoom.roomCenter.centerCode =:centerCode");
		query.setParameter("rackName",'%'+rackName+'%');
		query.setParameter("centerCode",centerCode);
		List<Rack> racks = query.list();
		return racks;
	}
	
	/**
	 * Guarda un Rack
	 * 
	 * 
	 */
	public void saveRack(Rack rack) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(rack);
	}
	
}
