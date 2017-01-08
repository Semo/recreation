package com.y4d3.services.jpa;

import com.y4d3.domain.roles.Role;
import com.y4d3.services.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by semo on 04.01.17.
 */
@Service
@Profile("jpadao")
public class RoleSrvcJpaDaoImpl extends AEntityManagerFactory implements RoleService {


    @Override
    public List<Role> listAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Role getById(Integer id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.find(Role.class, id);
    }

    @Override
    public Role saveOrUpdate(Role domainObject) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        Role saveRole = em.merge(domainObject);
        em.getTransaction().commit();

        return saveRole;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Role.class, id));
        em.getTransaction().commit();
    }
}
