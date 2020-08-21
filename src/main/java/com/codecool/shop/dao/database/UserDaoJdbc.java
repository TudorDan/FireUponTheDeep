package com.codecool.shop.dao.database;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.User;

public class UserDaoJdbc implements UserDao {
    private static UserDaoJdbc instance;
    private DatabaseManager databaseManager;

    private UserDaoJdbc() { }

    public static UserDaoJdbc getInstance() {
        if(instance == null) {
            instance = new UserDaoJdbc();
        }
        return instance;
    }

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public void add(User user) {
        // TODO: 18.08.2020 add(user)
    }

    @Override
    public boolean isSignedUp(String email) {
        // TODO: 18.08.2020 user isSignedUp(email)
        return false;
    }

    @Override
    public User getAuthenticatedUser(String email, String password) {
        // TODO: 18.08.2020 user getAuthenticatedUser(email, pass)
        return null;
    }

    @Override
    public void remove(User user) {
        // TODO: 18.08.2020 remove(user)
    }

    @Override
    public void updateUser(User user, String name, String email, String password, String phone, Address billing, Address shipping) {
        // TODO: 18.08.2020 updateUser(user, ...)
    }

    @Override
    public void updateUserCart(User user, Cart cart) {
        // TODO: 18.08.2020 user updateUserCart(user, cart)
    }

    @Override
    public Cart getUserCart(User user) {
        // TODO: 18.08.2020 user getUserCart(user)
        return null;
    }
}
