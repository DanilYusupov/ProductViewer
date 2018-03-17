package com.gdc.apps.dao;

import com.gdc.apps.model.Product;

public abstract class ProductGenericDao implements GenericDao<Product>{

    @Override
    public Long save(Product entity) {
        return (entity.getId() == null) ? insert(entity) : update(entity);
    }

    abstract Long insert(Product entity);

    abstract Long update(Product entity);
}
