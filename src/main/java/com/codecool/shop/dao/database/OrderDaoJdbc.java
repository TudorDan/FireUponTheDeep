package com.codecool.shop.dao.database;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Item;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

import java.util.List;

public class OrderDaoJdbc implements OrderDao {
    private static OrderDaoJdbc instance;
    private DatabaseManager databaseManager;

    private OrderDaoJdbc() { }

    public static OrderDaoJdbc getInstance() {
        if(instance == null) {
            instance = new OrderDaoJdbc();
        }
        return instance;
    }

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

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
    
    public List<Item> getItemsByOrderId() {
        // TODO: 23.08.2020 getItemsByOrderId
        return null;
    }
}
