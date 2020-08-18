package com.codecool.shop.dao.database;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

import java.util.List;

public class OrderDaoJdbc implements OrderDao {
    @Override
    public void add(Order order) {

    }

    @Override
    public void setPayed(Order order) {

    }

    @Override
    public List<Order> getOrdersOf(User user) {
        return null;
    }
}
