package ni.org.ics.lab.inventario.service;

import java.util.List;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.Equipment;
import ni.org.ics.lab.inventario.domain.Room;
import ni.org.ics.lab.inventario.domain.relationships.UserCenter;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto Equipment
 * 
 * @author William Aviles
 * 
 **/

@Service("equipoService")
@Transactional
public class EquipoService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Equipment> getEquipos(String centerCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Equipment e where e.equipRoom.roomCenter.centerCode =:centerCode order by e.equipName");
		query.setParameter("centerCode",centerCode);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Equipment> getActiveEquipos(String centerCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Equipment e where e.equipRoom.roomCenter.centerCode =:centerCode and e.pasive ='0' order by e.equipName");
		query.setParameter("centerCode",centerCode);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Equipment> getActiveEquiposCuarto(String roomCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Equipment e where e.equipRoom.roomCode =:roomCode and e.pasive ='0' order by e.equipName");
		query.setParameter("roomCode",roomCode);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Equipment> getActiveEquiposUseTemp(String centerCode, String boxAlicUse, float boxTemp) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Equipment e where e.equipRoom.roomCenter.centerCode =:centerCode and e.pasive ='0' " +
				"and e.equipUse like :boxAlicUse and e.equipTempMin <= :boxTemp and e.equipTempMax >= :boxTemp order by e.equipName");
		query.setParameter("centerCode",centerCode);
		query.setParameter("boxAlicUse",'%'+boxAlicUse+'%');
		query.setParameter("boxTemp",boxTemp);
		return query.list();
	}
	
	/**
	 * Regresa un Equipment
	 * 
	 * @return un <code>Equipment</code>
	 */

	public Equipment getEquipment(String equipCode,String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Equipment e where " +
				"e.equipCode =:equipCode");
		query.setParameter("equipCode",equipCode);
		Equipment equipo = (Equipment) query.uniqueResult();
		if (equipo!=null){
			String centerCode = equipo.getEquipRoom().getRoomCenter().getCenterCode();
			query = session.createQuery("FROM UserCenter uc where " +
					"uc.userCenterId.center =:centerCode and uc.userCenterId.username =:username and uc.pasive ='0'");
			query.setParameter("centerCode",centerCode);
			query.setParameter("username",username);
			UserCenter usercentro = (UserCenter) query.uniqueResult();
			if (usercentro!=null){
				return equipo;
			}
		}
		return null;
	}
	
	
	/**
	 * Regresa un Equipment
	 * 
	 * @return un <code>Equipment</code>
	 */

	public Equipment getEquipmentxName(String equipName, String centerCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Equipment e where " +
				"e.equipName =:equipName and e.equipRoom.roomCenter.centerCode =:centerCode");
		query.setParameter("equipName",equipName);
		query.setParameter("centerCode",centerCode);
		Equipment equipo = (Equipment) query.uniqueResult();
		return equipo;
	}
	
	/**
	 * Valida que el usuario esta en el centro
	 * 
	 * @return un <code>Equipment</code>
	 */
	public boolean validateCenter(Room room, String username){
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserCenter uc where " +
				"uc.userCenterId.center =:centerCode and uc.userCenterId.username =:username and uc.pasive ='0'");
		query.setParameter("centerCode",room.getRoomCenter().getCenterCode());
		query.setParameter("username",username);
		UserCenter usercentro = (UserCenter) query.uniqueResult();
		return usercentro!=null;
	}
	
	/**
	 * Guarda un Equipment
	 * 
	 * 
	 */
	public void saveEquipment(Equipment equipment) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(equipment);
	}
	
}
