package ni.org.ics.lab.inventario.service;

import java.util.List;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.relationships.StudyCenter;
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

@Service("studyCenterService")
@Transactional
public class StudyCenterService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Guarda un StudyCenter
	 * 
	 * 
	 */
	public void saveStudyCenter(StudyCenter studyCenter) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(studyCenter);
	}
	
	/**
	 * Regresa un StudyCenter
	 * 
	 * @return un <code>StudyCenter</code>
	 */

	public StudyCenter getStudyCenter(String studyCode, String centerCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM StudyCenter sc where " +
				"sc.estudioCentroId.centro =:centerCode and sc.estudioCentroId.estudio =:studyCode");
		query.setParameter("centerCode",centerCode);
		query.setParameter("studyCode",studyCode);
		StudyCenter estudiocentro = (StudyCenter) query.uniqueResult();
		return estudiocentro;
	}
	

	/**
	 * Regresa todos los StudyCenter de un Center
	 * 
	 * @return una lista de <code>StudyCenter</code>
	 */

	@SuppressWarnings("unchecked")
	public List<StudyCenter> getAllStudyCenter(String centerCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM StudyCenter sc where " +
				"sc.estudioCentroId.centro =:centerCode");
		query.setParameter("centerCode",centerCode);
		List<StudyCenter> estudioscentro = query.list();
		return estudioscentro;
	}
	
	/**
	 * Regresa los StudyCenter activos de un Center
	 * 
	 * @return una lista de <code>StudyCenter</code>
	 */

	@SuppressWarnings("unchecked")
	public List<StudyCenter> getActiveStudyCenter(String centerCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM StudyCenter sc where " +
				"sc.estudioCentroId.centro =:centerCode and sc.pasive ='0'");
		query.setParameter("centerCode",centerCode);
		List<StudyCenter> estudioscentro = query.list();
		return estudioscentro;
	}
	
	
}
