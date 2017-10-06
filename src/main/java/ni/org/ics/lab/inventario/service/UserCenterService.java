package ni.org.ics.lab.inventario.service;

import java.util.List;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.relationships.UserCenter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto UserSistema
 * 
 * @author William Aviles
 * 
 **/

@Service("userCenterService")
@Transactional
public class UserCenterService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Guarda un usercenter
	 * 
	 * 
	 */
	public void saveUserCenter(UserCenter usercenter) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(usercenter);
	}
	
	/**
	 * Regresa un UserCenter
	 * 
	 * @return un <code>UserCenter</code>
	 */

	public UserCenter getUserCenter(String username, String centerCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserCenter uc where " +
				"uc.userCenterId.center =:centerCode and uc.userCenterId.username =:username");
		query.setParameter("centerCode",centerCode);
		query.setParameter("username",username);
		UserCenter usercentro = (UserCenter) query.uniqueResult();
		return usercentro;
	}
	
	/**
	 * Regresa los UserCenter activos de un usuario
	 * 
	 * @return una lista de <code>UserCenter</code>
	 */
	
	@SuppressWarnings("unchecked")
	public List<UserCenter> getAllCentersUser(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserCenter uc where " +
				"uc.userCenterId.username =:username");
		query.setParameter("username",username);
		List<UserCenter> userscentro = query.list();
		return userscentro;
	}
	
	/**
	 * Regresa todos los UserCenter de un usuario
	 * 
	 * @return una lista de <code>UserCenter</code>
	 */
	@SuppressWarnings("unchecked")
	public List<UserCenter> getActiveCentersUser(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserCenter uc where " +
				"uc.userCenterId.username =:username and uc.pasive ='0'");
		query.setParameter("username",username);
		List<UserCenter> userscentro = query.list();
		return userscentro;
	}
	
	/**
	 * Regresa todos los UserCenter de un Center
	 * 
	 * @return una lista de <code>UserCenter</code>
	 */

	@SuppressWarnings("unchecked")
	public List<UserCenter> getAllUsersCenter(String centerCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserCenter uc where " +
				"uc.userCenterId.center =:centerCode");
		query.setParameter("centerCode",centerCode);
		List<UserCenter> userscentro = query.list();
		return userscentro;
	}
	
	/**
	 * Regresa los UserCenter activos de un Center
	 * 
	 * @return una lista de <code>UserCenter</code>
	 */

	@SuppressWarnings("unchecked")
	public List<UserCenter> getActiveUsersCenter(String centerCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserCenter uc where " +
				"uc.userCenterId.center =:centerCode and uc.pasive ='0'");
		query.setParameter("centerCode",centerCode);
		List<UserCenter> userscentro = query.list();
		return userscentro;
	}
	
	
}
