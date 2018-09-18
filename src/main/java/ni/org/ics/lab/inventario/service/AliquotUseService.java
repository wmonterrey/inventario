package ni.org.ics.lab.inventario.service;

import ni.org.ics.lab.inventario.domain.AliquotUse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * Servicio para el objeto AliquotUse
 * 
 * @author ics
 * 
 **/

@Service("aliquotUseService")
@Transactional
public class AliquotUseService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	
	/**
	 * Guarda un AliquotUse
	 * 
	 * 
	 */
	public void saveAliquotUse(AliquotUse alicUse) {
		Session session = sessionFactory.getCurrentSession();
		session.save(alicUse);
	}


	/**
	 * Actualiza un AliquotUse
	 *
	 *
	 */
	public void updateAliquotUse(AliquotUse alicUse) {
		Session session = sessionFactory.getCurrentSession();
		session.update(alicUse);
	}



}
