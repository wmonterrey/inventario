package ni.org.ics.lab.inventario.service;

import java.util.List;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.AliquotType;
import ni.org.ics.lab.inventario.domain.relationships.AlicTypeStudy;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto AliquotType
 * 
 * @author William Aviles
 * 
 **/

@Service("aliquotTypeService")
@Transactional
public class AliquotTypeService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<AliquotType> getAliquotTypes() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM AliquotType");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AliquotType> getActiveAliquotTypes() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM AliquotType at where at.pasive ='0'");
		return query.list();
	}
	
	/**
	 * Regresa un AliquotType
	 * 
	 * @return un <code>AliquotType</code>
	 */

	public AliquotType getAliquotType(String alicTypeCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM AliquotType at where " +
				"at.alicTypeCode =:alicTypeCode");
		query.setParameter("alicTypeCode",alicTypeCode);
		AliquotType alicType = (AliquotType) query.uniqueResult();
		return alicType;
	}
	
	/**
	 * Guarda un AliquotType
	 * 
	 * 
	 */
	public void saveAliquotType(AliquotType alicType) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(alicType);
	}
	
	@SuppressWarnings("unchecked")
	public List<AlicTypeStudy> getActiveAliquotTypes(String study, String use, float temp) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM AlicTypeStudy ats where ats.pasive ='0' and ats.estudio.pasive = '0' and ats.tipoAlicuota.pasive = '0' " +
				"and ats.estudio.studyCode =:studyCode and ats.tipoAlicuota.alicTypeUse =:alicTypeUse and ats.tipoAlicuota.alicTypeTemp =:alicTypeTemp");
		query.setParameter("studyCode",study);
		query.setParameter("alicTypeUse",use);
		query.setParameter("alicTypeTemp",temp);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AlicTypeStudy> getActiveAliquotTypesxStudy(String study) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM AlicTypeStudy ats where ats.pasive ='0' and ats.estudio.pasive = '0' and ats.tipoAlicuota.pasive = '0' " +
				"and ats.estudio.studyCode =:studyCode");
		query.setParameter("studyCode",study);
		return query.list();
	}
	
}
