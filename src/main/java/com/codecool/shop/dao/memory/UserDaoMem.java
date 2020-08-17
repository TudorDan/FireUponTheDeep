package com.codecool.shop.dao.memory;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoMem implements UserDao {
    private final List<User> data = new ArrayList<>();
    private static UserDaoMem instance = null;

    private UserDaoMem() {}
    public static UserDaoMem getInstance() {
        if (instance == null) {
            instance = new UserDaoMem();
        }
        return instance;
    }

    @Override
    public void add(User user) {
        data.add(user);
    }

    @Override
    public User find(String email) {
        return data.stream().filter(t -> t.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public void remove(User user) {
        data.remove(user);
    }

    @Override
    public void replace(User oldUser, User newUser) {
        data.remove(oldUser);
        data.add(newUser);
    }
}
