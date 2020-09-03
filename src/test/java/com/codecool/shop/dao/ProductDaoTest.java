package com.codecool.shop.dao;

import com.codecool.shop.Tester;
import com.codecool.shop.model.Category;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

class ProductDaoTest {
    private static DataStore dataStore;

    @BeforeAll
    static void setUp() {
        Tester.getInstance();
        dataStore = DataStore.getInstance();
    }

    @Test
    void testAddProduct() {
        Date currentDate = new Date();
        Supplier prada = new Supplier("Prada", "Luxury fashion house, specializing in leather handbags, travel " +
                "accessories, shoes, ready-to-wear, perfumes ");
        dataStore.supplierDao.add(prada);
        Category ring = new Category("Ring", "Jewelry", "Circular band, often set with gems, for wearing as an " +
                "ornament");
        dataStore.categoryDao.add(ring);
        Product product1 = new Product("Prada Sapphire and diamonds Rings", 250 , "USD", currentDate, "24 karate " +
                "white gold rings with sapphire and diamonds", "pic1.jpg", ring, prada);
        dataStore.productDao.add(product1);
        Assertions.assertTrue(product1.getId() > 0);
    }

    @Test
    void testFindProduct() {
        Date currentDate = new Date();
        Supplier prada = new Supplier("Prada", "Luxury fashion house, specializing in leather handbags, travel " +
                "accessories, shoes, ready-to-wear, perfumes ");
        dataStore.supplierDao.add(prada);
        Category ring = new Category("Ring", "Jewelry", "Circular band, often set with gems, for wearing as an " +
                "ornament");
        dataStore.categoryDao.add(ring);
        Product product1 = new Product("Prada Sapphire and diamonds Rings", 250 , "USD", currentDate, "24 karate " +
                "white gold rings with sapphire and diamonds", "pic1.jpg", ring, prada);
        dataStore.productDao.add(product1);
        Product dbProduct = dataStore.productDao.find(product1.getId());
        Assertions.assertAll("Product:",
                () -> Assertions.assertEquals(product1.getId(), dbProduct.getId()),
                () -> Assertions.assertEquals(product1.getSupplier().getId(), dbProduct.getSupplier().getId()),
                () -> Assertions.assertEquals(product1.getName(), dbProduct.getName()),
                () -> Assertions.assertEquals(product1.getDescription(), dbProduct.getDescription())
        );
    }

    @Test
    void remove() {
        Assertions.fail();
    }

    @Test
    void getAll() {
        Assertions.fail();
    }

    @Test
    void getBy() {
        Assertions.fail();
    }

    @Test
    void testGetBy() {
        Assertions.fail();
    }

    @Test
    void getCurrentPriceIdByProduct() {
        Assertions.fail();
    }
}