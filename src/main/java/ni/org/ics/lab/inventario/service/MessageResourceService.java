package ni.org.ics.lab.inventario.service;

import java.util.List;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.language.MessageResource;

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

@Service("messageResourceService")
@Transactional
public class MessageResourceService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos los mensajes
	 * 
	 * @return una lista de <code>MessageResource</code>(s)
	 */

	public void addCatalogo(){
		Session session = sessionFactory.getCurrentSession();
		MessageResource addT = new MessageResource();
		addT.setMessageKey("civil3");
		addT.setCatKey("3");
		addT.setEnglish("Test english");
		addT.setSpanish("Prueba español");
		session.saveOrUpdate(addT);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<MessageResource> loadAllMessages() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM MessageResource");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<MessageResource> loadMessages(String parametro) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM MessageResource mr where mr.messageKey like :parametro or mr.spanish like :parametro or mr.english like :parametro");
		query.setParameter("parametro", '%'+parametro+'%');
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<MessageResource> getCatalogo(String catalogo) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM MessageResource mens where mens.isCat ='0'" +
				" and mens.catRoot =:catalogo and mens.catKey is not null and mens.pasive = '0' order by mens.order");
		query.setParameter("catalogo", catalogo);
		// Retrieve all
		return  query.list();
	}
	
	
	public MessageResource getMensaje(String idMensaje) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM MessageResource mens where mens.messageKey =:idMensaje");
		query.setParameter("idMensaje", idMensaje);
		// Retrieve all
		return  (MessageResource) query.uniqueResult();
	}
	
	/**
	 * Guarda un mensaje
	 * 
	 * 
	 */
	public void saveMensaje(MessageResource message) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(message);
	}
	
	
}
