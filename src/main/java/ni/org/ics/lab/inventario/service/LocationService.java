package ni.org.ics.lab.inventario.service;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("locationService")
@Transactional
public class LocationService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;


}
