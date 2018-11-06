package ni.org.ics.lab.inventario.service;

import ni.org.ics.lab.inventario.domain.AliquotOutput;
import ni.org.ics.lab.inventario.domain.SampleRequest;
import ni.org.ics.lab.inventario.domain.SampleRequestDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author ics
 */

@Service("sampleRequestService")
@Transactional
public class SampleRequestService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * Guarda un SampleRequest
     *
     *
     */
    public void saveSampleRequest(SampleRequest sampleReq) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(sampleReq);
    }

    /**
     * Guarda un SampleRequest
     *
     *
     */
    public void saveSampleReqDetail(SampleRequestDetail detail) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(detail);
    }

}
