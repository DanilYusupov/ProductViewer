package com.gdc.apps.service;

import com.gdc.apps.dao.DaoException;
import com.gdc.apps.dao.ProductDao;
import com.gdc.apps.model.Product;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ProductService {

    private final ProductDao dao;

    public ProductService() {
        this.dao = new ProductDao(getDataSource(), getTableName());
    }

    public List<Product> getSortedList(int page, int size, String sortBy, String sortDir) {
        int offset = (page - 1) * size;
        return dao.getSortedList("ORDER BY " + sortBy + " " + sortDir.toUpperCase() + " LIMIT " + size + " OFFSET " + offset);
    }

    public String updateItem(Long id, String name, String category, short rating, int price) {
        Product old = dao.get(id);
        if (!name.equals("")) {
            old.setName(name);
        }
        if (!category.equals("")) {
            old.setCategory(category);
        }
        if (rating != -1) {
            if (rating != old.getRating()) {
                old.setRating(rating);
            }
        }
        if (price != -1) {
            if (price != old.getPrice()) {
                old.setPrice(price);
            }
        }
        return (dao.save(old) != null) ? "Item with id: " + id + " updated." : "Error updating item with id: " + id + ".";
    }

    public ProductDao getDao() {
        return dao;
    }

    private DataSource getDataSource() {
        HikariConfig config = new HikariConfig("/hikari.properties");
        return new HikariDataSource(config);
    }

    private String getTableName() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/db.properties"));
            return properties.getProperty("dao.engineer.tablename");
        } catch (IOException e) {
            throw new DaoException("Cannot read table name from 'dao.project.tablename " +
                    "from file hikari.properties", e);
        }
    }
}
