package com.y4d3.services.jpa;

import com.y4d3.domain.Product;
import com.y4d3.services.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;


/**
 * Created by jt on 12/9/15.
 */
@Service
@Profile("jpadao")
public class ProductSrvcJpaDaoImpl extends AEntityManagerFactory implements ProductService {

    @Override
    public List<Product> listAll() {
        EntityManager em = entityManagerFactory.createEntityManager();

        return em.createQuery("FROM Product", Product.class).getResultList();
    }

    @Override
    public Product getById(Integer id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        return em.find(Product.class, id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        Product savedProduct = em.merge(domainObject);
        em.getTransaction().commit();

        return savedProduct;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Product.class, id));
        em.getTransaction().commit();

    }
}