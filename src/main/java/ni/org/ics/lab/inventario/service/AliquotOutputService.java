package ni.org.ics.lab.inventario.service;

import ni.org.ics.lab.inventario.domain.AliquotOutput;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import javax.annotation.Resource;

/**
 * @author ics
 */
@Service("alicOutputService")
@Transactional
public class AliquotOutputService {


    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;


    /**
     * Guarda un AliquotOutput
     *
     *
     */
    public void saveAliquotOutput(AliquotOutput alicOut) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(alicOut);
    }




}
