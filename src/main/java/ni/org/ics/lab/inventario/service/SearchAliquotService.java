package ni.org.ics.lab.inventario.service;
import ni.org.ics.lab.inventario.domain.RegAlic;
import ni.org.ics.lab.inventario.domain.RegSalida;
import ni.org.ics.lab.inventario.domain.RegUso;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ics
 */
@Service("searchAliquotService")
@Transactional
public class SearchAliquotService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * Regresa un Aliquot
     *
     * @return un <code>Aliquot</code>
     */

    @SuppressWarnings("unchecked")
	public List<RegAlic> getAliquot(String aliCode) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM RegAlic a where " +
                "a.id like:aliCode");
        query.setParameter("aliCode",aliCode+'%');
        List<RegAlic> alic = query.list();
        return alic;
    }

    @SuppressWarnings("unchecked")
    public List<RegUso> getAllUses(String aliCode) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM RegUso u where u.codigoAlic =:aliCode order by u.fechaUso");
        query.setParameter("aliCode",aliCode);
        return query.list();
    }

    /**
     * Regresa un RegSalida
     *
     * @return un <code>Aliquot</code>
     */

    public RegSalida getExitRecord(String aliCode) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM RegSalida r where " +
                "r.codigoAlic =:aliCode");
        query.setParameter("aliCode",aliCode);
        RegSalida alic = (RegSalida) query.uniqueResult();
        return alic;
    }


}
