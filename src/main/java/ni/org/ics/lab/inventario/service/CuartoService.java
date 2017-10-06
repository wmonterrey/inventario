package ni.org.ics.lab.inventario.service;

import java.util.List;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.Room;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto Cuarto
 * 
 * @author William Aviles
 * 
 **/

@Service("cuartoService")
@Transactional
public class CuartoService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Room> getCuartos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Room");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Room> getCuartosActivos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Room r where r.pasive='0'");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Room> getCuartosActivosCentro(String centerCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Room r where r.pasive='0' and r.roomCenter.centerCode =:centerCode");
		query.setParameter("centerCode",centerCode);
		return query.list();
	}
	

	/**
	 * Regresa un Room
	 * 
	 * @return un <code>Room</code>
	 */

	public Room getCuarto(String roomCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Room r where " +
				"r.roomCode =:roomCode");
		query.setParameter("roomCode",roomCode);
		Room centro = (Room) query.uniqueResult();
		return centro;
	}
	
	/**
	 * Guarda un Room
	 * 
	 * 
	 */
	public void saveRoom(Room room) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(room);
	}
		
	
}
