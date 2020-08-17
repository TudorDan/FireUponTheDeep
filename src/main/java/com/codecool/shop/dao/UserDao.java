package com.codecool.shop.dao;

import com.codecool.shop.model.User;

public interface UserDao {
    void add(User user);
    User find(String email);
    void remove(User user);
    void replace(User oldUser, User newUser);
}
