package com.gdc.apps.dao.test;

import com.gdc.apps.dao.DaoException;
import com.gdc.apps.dao.ProductDao;
import com.gdc.apps.model.Product;
import com.opentable.db.postgres.embedded.FlywayPreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ProductDaoTest {

    private String category = "Computers";
    private String name = "Laptop Acer Aspire V5-571G";
    private int price = 49990;
    private short rating = 4;
    private Product product = new Product(category, name, price, rating);
    private String tableName;

    public ProductDaoTest() {
        setTableName();
    }

    @Rule
    public PreparedDbRule db = EmbeddedPostgresRules.preparedDatabase(FlywayPreparer.forClasspathLocation("db"));

    @Test
    public void testCrateUser(){
        ProductDao dao = new ProductDao(db.getTestDatabase(), tableName);
        Long id = dao.save(product);
        assertNotNull(id);
        Product saved = dao.get(id);
        assertEquals(category, saved.getCategory());
        assertEquals(name, saved.getName());
        assertEquals(price, saved.getPrice());

        dao.delete(id);
    }

    @Test
    public void testUpdateUser() {
        ProductDao dao = new ProductDao(db.getTestDatabase(), tableName);
        String updateName = "MacBook";
        long id = dao.save(product);
        Product toUpdate = dao.get(id);
        toUpdate.setName(updateName);
        dao.save(toUpdate);
        assertEquals(updateName, dao.get(id).getName());

        dao.delete(id);
    }

    @Test
    public void testCreatingExistentUser() {
        ProductDao dao = new ProductDao(db.getTestDatabase(), tableName);
        long id = dao.save(product);
        dao.save(product);

        dao.delete(id);
        assertNull(dao.get(id));
    }

    @Test
    public void testGettingNonexistentUser() {
        ProductDao dao = new ProductDao(db.getTestDatabase(), tableName);
        try {
            dao.get(0L);
        } catch (DaoException e) {
            assertEquals("Error receiving product from database:", e.getMessage());
        }

    }

    @Test
    public void testDeletingNonexistentUser() {
        ProductDao dao = new ProductDao(db.getTestDatabase(), tableName);
        try {
            dao.delete(0L);
        } catch (DaoException e) {
            assertEquals("Error deleting product with id = 0 :", e.getMessage());
        }
    }

    @Test
    public void testGetTenOffsetCount(){
        ProductDao dao  = new ProductDao(db.getTestDatabase(), tableName);
        int count = 30;
        for (int i = 0; i < count; i++) {
            product.setName(name + " : " + String.valueOf(i + 1));
            dao.save(product);
        }
        int offset = 10;
        List<Product> result = dao.getTenOffset(offset);
        assertEquals(result.get(0).getName(), name + " : " + String.valueOf(offset + 1));
        assertEquals(result.get(4).getName(), name + " : " + String.valueOf(offset + 5));
        assertEquals(result.get(9).getName(), name + " : " + String.valueOf(offset + 10));

        assertEquals(count, dao.getFullCount());
    }

    private void setTableName() {
        Properties properties = new Properties();
        try {
            properties.load(ProductDaoTest.class.getResourceAsStream("/db.properties"));
            this.tableName = properties.getProperty("table.name");
        } catch (IOException e) {
            throw new DaoException("Cannot get name for tests table: ", e);
        }
    }

}
