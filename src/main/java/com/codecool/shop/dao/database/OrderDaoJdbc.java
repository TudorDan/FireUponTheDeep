package com.codecool.shop.dao.database;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

import java.util.List;

public class OrderDaoJdbc implements OrderDao {
    @Override
    public void add(Order order) {
        // TODO: 18.08.2020 add(order)
    }

    @Override
    public void setPayed(Order order) {
        // TODO: 18.08.2020 setPayed(order)
    }

    @Override
    public List<Order> getOrdersOf(User user) {
        // TODO: 18.08.2020 Order getOrdersOf(user)
        return null;
    }
}
