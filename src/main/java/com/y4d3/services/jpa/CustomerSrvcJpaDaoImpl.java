package com.y4d3.services.jpa;

import com.y4d3.domain.Customer;
import com.y4d3.services.CustomerService;
import com.y4d3.services.encryption.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by semo on 22.12.16.
 */
@Service
@Profile("jpadao")
public class CustomerSrvcJpaDaoImpl extends AEntityManagerFactory implements CustomerService {

    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }


    @Override
    public List<Customer> listAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery("from Customer", Customer.class).getResultList();
    }

    @Override
    public Customer getById(Integer id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.find(Customer.class, id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        if (domainObject.getUser() != null && domainObject.getUser().getPassword() != null) {
            domainObject.getUser().setEncryptedPassword(encryptionService.encryptString(domainObject.getUser().getPassword()));
        }

        Customer customer = em.merge(domainObject);
        em.getTransaction().commit();
        return customer;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Customer.class, id));
        em.getTransaction().commit();
    }
}
