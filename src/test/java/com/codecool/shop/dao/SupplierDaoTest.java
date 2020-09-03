package com.codecool.shop.dao;

import com.codecool.shop.Tester;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SupplierDaoTest {
    private static DataStore dataStore;

    @BeforeAll
    static void setUp() {
        Tester.getInstance();
        dataStore = DataStore.getInstance();
    }

    @Test
    void testAddSupplier() {
        Supplier prada = new Supplier("Prada", "Luxury fashion house, specializing in leather handbags, travel " +
                "accessories, shoes, ready-to-wear, perfumes ");
        dataStore.supplierDao.add(prada);
        Assertions.assertEquals(1, prada.getId());
    }

    @Test
    void testFindSupplier() {
        Supplier prada = new Supplier("Prada", "Luxury fashion house, specializing in leather handbags, travel " +
                "accessories, shoes, ready-to-wear, perfumes ");
        dataStore.supplierDao.add(prada);
        Supplier dbPrada = dataStore.supplierDao.find(prada.getId());
        Assertions.assertAll("Supplier:",
                () -> Assertions.assertEquals(prada.getId(), dbPrada.getId()),
                () -> Assertions.assertEquals(prada.getName(), dbPrada.getName()),
                () -> Assertions.assertEquals(prada.getDescription(), dbPrada.getDescription())
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
}