package ni.org.ics.lab.inventario.service;

import java.util.List;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.Aliquot;
import ni.org.ics.lab.inventario.domain.AliquotOutput;
import ni.org.ics.lab.inventario.domain.AliquotUse;
import ni.org.ics.lab.inventario.domain.relationships.UserCenter;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto Aliquot
 * 
 * @author William Aviles
 * 
 **/

@Service("aliquotService")
@Transactional
public class AliquotService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Aliquot> getAliquots(String boxCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Aliquot a where a.aliBox.boxCode =:boxCode order by a.aliPosition");
		query.setParameter("boxCode",boxCode);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Aliquot> getActiveAliquots(String boxCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Aliquot a where a.aliBox.boxCode =:boxCode and a.pasive ='0' order by a.aliPosition");
		query.setParameter("boxCode",boxCode);
		return query.list();
	}
	
	/**
	 * Regresa un Aliquot
	 * 
	 * @return un <code>Aliquot</code>
	 */

	public Aliquot getAliquot(String aliCode,String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Aliquot a where " +
				"a.aliId.aliCode =:aliCode and a.pasive= '0'");
		query.setParameter("aliCode",aliCode);
		Aliquot alic = (Aliquot) query.uniqueResult();
		if (alic!=null){
			String centerCode = alic.getAliBox().getBoxRack().getRackEquip().getEquipRoom().getRoomCenter().getCenterCode();
			query = session.createQuery("FROM UserCenter uc where " +
					"uc.userCenterId.center =:centerCode and uc.userCenterId.username =:username and uc.pasive ='0'");
			query.setParameter("centerCode",centerCode);
			query.setParameter("username",username);
			UserCenter usercentro = (UserCenter) query.uniqueResult();
			if (usercentro!=null){
				return alic;
			}
		}
		return null;
	}
	
	/**
	 * Guarda un Aliquot
	 * 
	 * 
	 */
	public void saveAliquot(Aliquot alic) {
		Session session = sessionFactory.getCurrentSession();
		session.save(alic);
	}
	
	/**
	 * Actualiza un Aliquot
	 * 
	 * 
	 */
	public void updateAliquot(Aliquot alic) {
		Session session = sessionFactory.getCurrentSession();
		session.update(alic);
	}

	/**
	 * Regresa un Aliquot
	 *
	 * @return un <code>Aliquot</code>
	 */

	public Aliquot getAliquotByPos(String boxCode,String username, Integer pos) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Aliquot a where " +
				"a.aliBox.boxCode =:boxCode and a.aliPosition =:pos and pasive ='0'");
		query.setParameter("boxCode",boxCode);
		query.setParameter("pos", pos);
		Aliquot alic = (Aliquot) query.uniqueResult();
		if (alic!=null){
			String centerCode = alic.getAliBox().getBoxRack().getRackEquip().getEquipRoom().getRoomCenter().getCenterCode();
			query = session.createQuery("FROM UserCenter uc where " +
					"uc.userCenterId.center =:centerCode and uc.userCenterId.username =:username and uc.pasive ='0'");
			query.setParameter("centerCode",centerCode);
			query.setParameter("username",username);
			UserCenter usercentro = (UserCenter) query.uniqueResult();
			if (usercentro!=null){
				return alic;
			}
		}
		return null;
	}

	/**
	 * Regresa un Aliquot
	 *
	 * @return un <code>Aliquot</code>
	 */

	public Aliquot getAliquotByCode(String boxCode,String username, String alicode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Aliquot a where " +
				"a.aliBox.boxCode =:boxCode and a.aliId.aliCode =:alicode ");
		query.setParameter("boxCode",boxCode);
		query.setParameter("alicode", alicode);
		Aliquot alic = (Aliquot) query.uniqueResult();
		if (alic!=null){
			String centerCode = alic.getAliBox().getBoxRack().getRackEquip().getEquipRoom().getRoomCenter().getCenterCode();
			query = session.createQuery("FROM UserCenter uc where " +
					"uc.userCenterId.center =:centerCode and uc.userCenterId.username =:username and uc.pasive ='0'");
			query.setParameter("centerCode",centerCode);
			query.setParameter("username",username);
			UserCenter usercentro = (UserCenter) query.uniqueResult();
			if (usercentro!=null){
				return alic;
			}
		}
		return null;
	}

	/**
	 * Regresa un Aliquot
	 *
	 * @return un <code>Aliquot</code>
	 */

	public Aliquot getAliquotByStudyCode(String aliCode,String username, String study) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Aliquot a where " +
				"a.aliId.aliCode =:aliCode and a.aliId.aliStudy =:study and a.pasive= '0'");
		query.setParameter("aliCode",aliCode);
		query.setParameter("study",study);
		Aliquot alic = (Aliquot) query.uniqueResult();
		if (alic!=null){
			String centerCode = alic.getAliBox().getBoxRack().getRackEquip().getEquipRoom().getRoomCenter().getCenterCode();
			query = session.createQuery("FROM UserCenter uc where " +
					"uc.userCenterId.center =:centerCode and uc.userCenterId.username =:username and uc.pasive ='0'");
			query.setParameter("centerCode",centerCode);
			query.setParameter("username",username);
			UserCenter usercentro = (UserCenter) query.uniqueResult();
			if (usercentro!=null){
				return alic;
			}
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Aliquot> getAliquotsSearch(String aliCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Aliquot a where a.aliId.aliCode like:aliCode");
		query.setParameter("aliCode",aliCode+'%');
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AliquotUse> getAliquotsUseSearch(String aliCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM AliquotUse a where a.aliCode like:aliCode");
		query.setParameter("aliCode",aliCode+'%');
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AliquotOutput> getAliquotsOutputSearch(String aliCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM AliquotOutput a where a.aliCode like:aliCode");
		query.setParameter("aliCode",aliCode+'%');
		return query.list();
	}
	
}
