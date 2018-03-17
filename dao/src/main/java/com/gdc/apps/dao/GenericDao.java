package com.gdc.apps.dao;

import java.util.List;

public interface GenericDao<T> {
    T get(Long id);

    List<T> getTenOffset(int offset);

    Long save(T entity);

    boolean delete(Long id);
}
