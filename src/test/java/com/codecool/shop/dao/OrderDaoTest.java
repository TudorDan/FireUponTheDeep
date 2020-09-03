package com.codecool.shop.dao;

import com.codecool.shop.Tester;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;
import com.codecool.shop.model.UserStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class OrderDaoTest {
    private static DataStore dataStore;

    @BeforeAll
    static void setUp() {
        Tester.getInstance();
        dataStore = DataStore.getInstance();
    }

    @Test
    void testAddOrder() {
        //add new user
        User user = new User("testname", "ordermail@test.com", "testpass", "1234", null, null, UserStatus.SIGNED);
        dataStore.userDao.add(user);

        //add order for new user
        Order order = new Order(new Cart(), user);
        dataStore.orderDao.add(order);

        //check id
        Assertions.assertTrue(order.getId() > 0);
    }

    @Test
    void setPayed() {
        Assertions.fail();
    }

    @Test
    void getOrdersOf() {
        Assertions.fail();
    }
}