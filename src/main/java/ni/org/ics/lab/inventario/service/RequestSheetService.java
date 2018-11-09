package ni.org.ics.lab.inventario.service;

import ni.org.ics.lab.inventario.domain.RegUso;
import ni.org.ics.lab.inventario.domain.SampleRequest;
import ni.org.ics.lab.inventario.domain.SampleRequestDetail;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ics
 */
@Service("requestSheetService")
@Transactional
public class RequestSheetService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<SampleRequest> getAllRequestByDates(Date fromDate, Date toDate) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM SampleRequest req where req.requestDate between :fromDate and :toDate order by req.recordDate");
        query.setParameter("fromDate",fromDate);
        query.setParameter("toDate",toDate);
        return query.list();
    }

    public List<SampleRequestDetail> getRequestByCode(String code) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM SampleRequestDetail req where req.idRequest.idRequest =:code ");
        query.setParameter("code",code);
        return query.list();
    }


}
