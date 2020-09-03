package com.codecool.shop.dao;

import com.codecool.shop.Tester;
import com.codecool.shop.model.User;
import com.codecool.shop.model.UserStatus;
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
    void testAddUser() {
        //add new user
        User user = new User("testname", "testemail@test.com", "testpass", "1234", null, null, UserStatus.SIGNED);
        dataStore.userDao.add(user);

        //check if id is set
        Assertions.assertTrue(user.getId() > 0);
    }

    @Test
    void testIsSignedUpUser() {
        //add new user
        User user = new User("testname", "testemail@test.com", "testpass", "1234", null, null, UserStatus.SIGNED);
        dataStore.userDao.add(user);

        Assertions.assertTrue(dataStore.userDao.isSignedUp("testemail@test.com"));
        Assertions.assertFalse(dataStore.userDao.isSignedUp("wrongemail"));
    }

    @Test
    void testGetAuthenticatedUser() {
        //add new user
        User user = new User("testname", "testemail@test.com", "testpass", "1234", null, null, UserStatus.SIGNED);
        dataStore.userDao.add(user);

        //get users
        User user1Good = dataStore.userDao.getAuthenticatedUser("testemail@test.com", "testpass");
        User user2WrongPass = dataStore.userDao.getAuthenticatedUser("testemail@test.com", "wrongpass");
        User user3WrongMail = dataStore.userDao.getAuthenticatedUser("wrongmail@test.com", "testpass");

        Assertions.assertEquals(user.getId(), user1Good.getId());
        Assertions.assertNull(user2WrongPass);
        Assertions.assertNull(user3WrongMail);
    }

    @Test
    void testRemoveUser() {
        //add new user
        User user = new User("testname", "testemail@test.com", "testpass", "1234", null, null, UserStatus.SIGNED);
        dataStore.userDao.add(user);

        Assertions.assertTrue(dataStore.userDao.isSignedUp("testemail@test.com"));
        dataStore.userDao.remove(user);
        Assertions.assertFalse(dataStore.userDao.isSignedUp("testemail@test.com"));
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