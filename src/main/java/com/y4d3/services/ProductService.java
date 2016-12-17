package com.y4d3.services;

import com.y4d3.domain.Product;

import java.util.List;

/**
 * Created by root on 15.12.16.
 */
public interface ProductService {

    List<Product> listAllProducts();

    Product getProductById(Integer id);

    Product saveOrUpdateProduct(Product product);

    void deleteProduct(Integer id);
}
