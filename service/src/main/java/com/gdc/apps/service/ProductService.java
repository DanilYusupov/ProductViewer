package com.gdc.apps.service;

import com.gdc.apps.dao.DaoException;
import com.gdc.apps.dao.ProductDao;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class ProductService {

    private final ProductDao dao;

    public ProductService() {
        this.dao = new ProductDao(getDataSource(), getTableName());
    }



    private DataSource getDataSource(){
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
