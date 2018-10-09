package ni.org.ics.lab.inventario.service;

import ni.org.ics.lab.inventario.domain.AliquotTransfers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author ics
 */
@Service("alicTransferService")
@Transactional
public class AliquotTransferService {


    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;


    /**
     * Guarda un AliquotOutput
     *
     *
     */
    public void saveAliquotTransfer(AliquotTransfers alicTransfer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(alicTransfer);
    }




}
