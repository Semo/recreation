package com.y4d3.services;

import java.util.List;

/**
 * Created by semo on 19.12.16.
 */
public interface CrudService<T> {

    List<?> listAll();

    T getById(Integer id);

    T saveOrUpdate(T domainObject);

    void delete(Integer id);

}
