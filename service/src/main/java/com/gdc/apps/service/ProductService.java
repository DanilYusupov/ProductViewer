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

    public List<Product> getSortedByNameDesc(int offset) {
        return getPattern("name", "DESC", offset);
    }

    public List<Product> getSortedByNameAsc(int offset) {
        return getPattern("name", "ASC", offset);
    }

    public List<Product> getSortedByCategoryDesc(int offset) {
        return getPattern("category", "DESC", offset);
    }

    public List<Product> getSortedByCategoryAsc(int offset) {
        return getPattern("category", "ASC", offset);
    }

    public List<Product> getSortedByRatingDesc(int offset) {
        return getPattern("rating", "DESC", offset);
    }

    public List<Product> getSortedByRatingAsc(int offset) {
        return getPattern("rating", "ASC", offset);
    }

    public List<Product> getSortedByPriceDesc(int offset) {
        return getPattern("price", "DESC", offset);
    }

    public List<Product> getSortedByPriceAsc(int offset) {
        return getPattern("price", "ASC", offset);
    }

    private List<Product> getPattern(String field, String acsOrDesc, int offset) {
        return dao.getSortedList("ORDER BY " + field + " " + acsOrDesc + " LIMIT 10 OFFSET " + offset);
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
