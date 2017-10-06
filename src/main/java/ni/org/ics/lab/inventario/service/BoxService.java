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

	public Box getBoxSugerida(String boxStudy, String centerCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Box b where " +
				"b.boxStudy.studyCode =:boxStudy and b.boxRack.rackEquip.equipRoom.roomCenter.centerCode =:centerCode and b.pasive = '0' " +
				"and b.boxAlicType like '%1b%' and b.boxAlicUse = 'PCR' and b.boxTemp = -80 and b.boxResult = 'NR' " +
				"and b.boxCode in (Select box.boxCode from Box box)");
		query.setParameter("boxStudy",boxStudy);
		query.setParameter("centerCode",centerCode);
		query.setMaxResults(1);
		Box box = (Box) query.uniqueResult();
		return box;
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
	
}
