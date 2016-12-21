package com.y4d3.bootstrap;

import com.y4d3.domain.Product;
import com.y4d3.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by semo on 21.12.16.
 */
@Component
public class Bootup implements ApplicationListener<ContextRefreshedEvent> {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadProducts();
    }

    private void loadProducts() {
        Product p1 = new Product();
        p1.setId(1);
        p1.setDescription("This is product 1.");
        p1.setPrice(new BigDecimal(1.99));
        p1.setImageUrl("/images/1");
        productService.saveOrUpdate(p1);


        Product p2 = new Product();
        p2.setId(2);
        p2.setDescription("This is product 2.");
        p2.setPrice(new BigDecimal(199.99));
        p2.setImageUrl("/images/2");
        productService.saveOrUpdate(p2);


        Product p3 = new Product();
        p3.setId(3);
        p3.setDescription("This is product 3.");
        p3.setPrice(new BigDecimal(22.11));
        p3.setImageUrl("/images/3");
        productService.saveOrUpdate(p3);

        Product p4 = new Product();
        p4.setId(4);
        p4.setDescription("This is product 4.");
        p4.setPrice(new BigDecimal(21.95));
        p4.setImageUrl("/images/4");
        productService.saveOrUpdate(p4);

        Product p5 = new Product();
        p5.setId(5);
        p5.setDescription("This is product 5.");
        p5.setPrice(new BigDecimal(24.95));
        p5.setImageUrl("/images/5");
        productService.saveOrUpdate(p5);

    }
}
