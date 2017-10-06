package ni.org.ics.lab.inventario.service;

import java.util.List;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.relationships.AlicTypeStudy;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto AlicTypeStudy
 * 
 * @author William Aviles
 * 
 **/

@Service("alicTypeStudyService")
@Transactional
public class AlicTypeStudyService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Guarda un AlicTypeStudy
	 * 
	 * 
	 */
	public void saveAlicTypeStudy(AlicTypeStudy alicTypeStudy) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(alicTypeStudy);
	}
	
	/**
	 * Regresa un AlicTypeStudy
	 * 
	 * @return un <code>AlicTypeStudy</code>
	 */

	public AlicTypeStudy getAlicTypeStudy(String studyCode, String alicCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM AlicTypeStudy ats where " +
				"ats.tipoAlicuotaEstudioId.tipoAlicuota =:alicCode and ats.tipoAlicuotaEstudioId.estudio =:studyCode");
		query.setParameter("alicCode",alicCode);
		query.setParameter("studyCode",studyCode);
		AlicTypeStudy tipoalicuotaestudio = (AlicTypeStudy) query.uniqueResult();
		return tipoalicuotaestudio;
	}
	

	/**
	 * Regresa todos los AlicTypeStudy de un Study
	 * 
	 * @return una lista de <code>AlicTypeStudy</code>
	 */

	@SuppressWarnings("unchecked")
	public List<AlicTypeStudy> getAllAlicTypeStudy(String studyCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM AlicTypeStudy ats where " +
				"ats.tipoAlicuotaEstudioId.estudio =:studyCode");
		query.setParameter("studyCode",studyCode);
		List<AlicTypeStudy> tipoalicuotaestudio = query.list();
		return tipoalicuotaestudio;
	}
	
	/**
	 * Regresa los AlicTypeStudy activos de un Study
	 * 
	 * @return una lista de <code>AlicTypeStudy</code>
	 */

	@SuppressWarnings("unchecked")
	public List<AlicTypeStudy> getActiveAlicTypeStudy(String studyCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM AlicTypeStudy ats where " +
				"ats.tipoAlicuotaEstudioId.estudio =:studyCode and ats.pasive ='0'");
		query.setParameter("studyCode",studyCode);
		List<AlicTypeStudy> tipoalicuotaestudio = query.list();
		return tipoalicuotaestudio;
	}
	
	
	/**
	 * Regresa los AlicTypeStudy activos
	 * 
	 * @return una lista de <code>AlicTypeStudy</code>
	 */

	@SuppressWarnings("unchecked")
	public List<AlicTypeStudy> getAllActiveAlicTypeStudy() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM AlicTypeStudy ats where ats.pasive ='0'");
		List<AlicTypeStudy> tipoalicuotaestudio = query.list();
		return tipoalicuotaestudio;
	}
	
	
}
