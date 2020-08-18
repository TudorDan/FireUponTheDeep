package com.codecool.shop.dao.database;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.User;

public class UserDaoJdbc implements UserDao {
    @Override
    public void add(User user) {

    }

    @Override
    public boolean isSignedUp(String email) {
        return false;
    }

    @Override
    public User getAuthenticatedUser(String email, String password) {
        return null;
    }

    @Override
    public void remove(User user) {

    }

    @Override
    public void updateUser(User user, String name, String email, String password, String phone, Address billing, Address shipping) {

    }

    @Override
    public void updateUserCart(User user, Cart cart) {

    }

    @Override
    public Cart getUserCart(User user) {
        return null;
    }
}
