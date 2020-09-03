package com.codecool.shop.dao;

import com.codecool.shop.Tester;
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
    void add() {
        Assertions.fail();
    }

    @Test
    void find() {
        Assertions.fail();
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