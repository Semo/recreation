package com.y4d3.services.jpa;

import com.y4d3.domain.User;
import com.y4d3.services.UserService;
import com.y4d3.services.encryption.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by semo on 02.01.17.
 */
@Service
@Profile("jpadao")
public class UserSrvcJpaDaoImpl extends AEntityManagerFactory implements UserService {

    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public List<?> listAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getById(Integer id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.find(User.class, id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        if (domainObject.getPassword() != null) {
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
        }

        User saveduser = em.merge(domainObject);
        em.getTransaction().commit();

        return saveduser;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(User.class, id));
        em.getTransaction().commit();
    }
}
