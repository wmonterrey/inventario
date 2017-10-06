package ni.org.ics.lab.inventario.service;

import java.util.List;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.audit.AuditTrail;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto AuditTrail
 * 
 * @author William Aviles
 * 
 **/

@Service("auditTrailService")
@Transactional
public class AuditTrailService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<AuditTrail> getBitacora(String id) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM AuditTrail audit where audit.entityId =:id order by audit.operationDate desc");
		query.setParameter("id", id);
		return query.list();
	}
	
}
