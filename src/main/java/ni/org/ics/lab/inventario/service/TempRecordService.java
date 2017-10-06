package ni.org.ics.lab.inventario.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.TempRecord;
import ni.org.ics.lab.inventario.domain.relationships.UserCenter;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto TempRecord
 * 
 * @author William Aviles
 * 
 **/

@Service("tempRecordService")
@Transactional
public class TempRecordService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<TempRecord> getTempRecords(String equipCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM TempRecord tr where tr.tempEquip.equipCode =:equipCode");
		query.setParameter("equipCode",equipCode);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TempRecord> getTempRecords(String equipCode, Date fecha1, Date fecha2) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStampInicio = null;
		Timestamp timeStampFinal = null;
		if (fecha1 != null) timeStampInicio = new Timestamp(fecha1.getTime());
		if (fecha2 != null) timeStampFinal = new Timestamp(fecha2.getTime()+86400000);
		String strQuery = "FROM TempRecord tr where tr.tempEquip.equipCode =:equipCode";
		if (fecha1 != null) strQuery = strQuery + " and tr.tempDate >= :fec1";
		if (fecha2 != null) strQuery = strQuery + " and tr.tempDate <= :fec2";
		Query query = session.createQuery(strQuery);
		query.setParameter("equipCode",equipCode);
		if (fecha1 != null) query.setTimestamp("fec1", timeStampInicio);
		if (fecha2 != null) query.setTimestamp("fec2", timeStampFinal);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TempRecord> getActiveTempRecords(String equipCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM TempRecord tr where tr.tempEquip.equipCode =:equipCode and tr.pasive ='0'");
		query.setParameter("equipCode",equipCode);
		return query.list();
	}
	
	
	/**
	 * Regresa un TempRecord
	 * 
	 * @return un <code>TempRecord</code>
	 */

	public TempRecord getTempRecord(String tempCode) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM TempRecord tr where " +
				"tr.tempCode =:tempCode");
		query.setParameter("tempCode",tempCode);
		TempRecord tr = (TempRecord) query.uniqueResult();
		return tr;
	}
	
	/**
	 * Regresa un TempRecord
	 * 
	 * @return un <code>TempRecord</code>
	 */

	public TempRecord getTempRecord(String tempCode,String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM TempRecord tr where " +
				"tr.tempCode =:tempCode");
		query.setParameter("tempCode",tempCode);
		TempRecord tr = (TempRecord) query.uniqueResult();
		if (tr!=null){
			String centerCode = tr.getTempEquip().getEquipRoom().getRoomCenter().getCenterCode();
			query = session.createQuery("FROM UserCenter uc where " +
					"uc.userCenterId.center =:centerCode and uc.userCenterId.username =:username and uc.pasive ='0'");
			query.setParameter("centerCode",centerCode);
			query.setParameter("username",username);
			UserCenter usercentro = (UserCenter) query.uniqueResult();
			if (usercentro!=null){
				return tr;
			}
		}
		return tr;
	}
	
	/**
	 * Guarda un TempRecord
	 * 
	 * 
	 */
	public void saveTempRecord(TempRecord tr) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(tr);
	}
	
}
