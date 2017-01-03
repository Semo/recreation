package com.y4d3.services.jpa;

import com.y4d3.domain.Order;
import com.y4d3.services.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by semo on 02.01.17.
 */
@Service
@Profile("jpadao")
public class OrderSrvcJpaDaoImpl extends AEntityManagerFactory implements OrderService {


    @Override
    public List<Order> listAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery("from Order", Order.class).getResultList();
    }

    @Override
    public Order getById(Integer id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.find(Order.class, id);
    }

    @Override
    public Order saveOrUpdate(Order domainObject) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        Order savedOrder = em.merge(domainObject);
        em.getTransaction().commit();
        return savedOrder;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Order.class, id));
        em.getTransaction().commit();
    }
}
