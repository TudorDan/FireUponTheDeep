package com.codecool.shop.dao;

import com.codecool.shop.Tester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UserDaoTest {
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
    void isSignedUp() {
        Assertions.fail();
    }

    @Test
    void getAuthenticatedUser() {
        Assertions.fail();
    }

    @Test
    void remove() {
        Assertions.fail();
    }

    @Test
    void updateUser() {
        Assertions.fail();
    }

    @Test
    void updateUserCart() {
        Assertions.fail();
    }
}