package ni.org.ics.lab.inventario.service;

import java.util.List;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.Study;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto Study
 * 
 * @author William Aviles
 * 
 **/

@Service("estudioService")
@Transactional
public class EstudioService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Study> getEstudios() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Study");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Study> getActiveEstudios() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Study s where s.pasive ='0'");
		return query.list();
	}
	
	/**
	 * Regresa un Study
	 * 
	 * @return un <code>Study</code>
	 */

	public Study getStudy(String studyCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Study s where " +
				"s.studyCode =:studyCode");
		query.setParameter("studyCode",studyCode);
		Study estudio = (Study) query.uniqueResult();
		return estudio;
	}
	
	/**
	 * Guarda un Study
	 * 
	 * 
	 */
	public void saveStudy(Study study) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(study);
	}
	
}
