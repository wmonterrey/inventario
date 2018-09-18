package ni.org.ics.lab.inventario.service;

import java.util.List;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.Center;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto Center
 * 
 * @author William Aviles
 * 
 **/

@Service("centroService")
@Transactional
public class CentroService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Center> getCentros() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Center");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Center> getCentrosActivos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Center c where c.pasive='0'");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Center> getCentrosActivos2(String centerCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Center c where c.pasive='0' and centerCode not like :centerCode");
		query.setParameter("centerCode",centerCode);
		return query.list();
	}
	

	/**
	 * Regresa un Center
	 * 
	 * @return un <code>Center</code>
	 */

	public Center getCentro(String centerCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Center c where " +
				"c.centerCode =:centerCode");
		query.setParameter("centerCode",centerCode);
		Center centro = (Center) query.uniqueResult();
		return centro;
	}
	
	/**
	 * Guarda un center
	 * 
	 * 
	 */
	public void saveCenter(Center center) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(center);
	}
		
	
}
