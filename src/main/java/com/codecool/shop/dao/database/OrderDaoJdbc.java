package com.codecool.shop.dao.database;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Item;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.OrderStatus;
import com.codecool.shop.model.User;

import java.sql.*;
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
        try(Connection conn = databaseManager.getConnection()) {
            PreparedStatement st;

            //insert new order, get the id, and update parameter
            String insertOrder = "INSERT INTO orders(name, user_id, is_my_cart, date, order_status) " +
                    "VALUES (?, ?, ?, ?, ?::OrderStatus) " +
                    "RETURNING id";
            int orderId = 0;
            st = conn.prepareStatement(insertOrder);
            st.setString(1, "Order");
            st.setInt(2, order.getUser().getId());
            st.setBoolean(3, false);
            st.setDate(4, new Date(order.getDate().getTime()));
            st.setString(5, order.getStatus().toString());
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                orderId = rs.getInt(1);
                order.setId(orderId);
            }

            //add new items
            String insertItems = "INSERT INTO items(price_id, order_id, quantity) " +
                    "VALUES (?, ?, ?)";
            st = conn.prepareStatement(insertItems);
            List<Item> items = order.getCart().getItems();
            DatabaseManager.insertOrderItems(st, orderId, items);

            //add event
            String insertEvent = "INSERT INTO events(date, description, order_id) " +
                    "VALUES (?, ?, ?)";
            st = conn.prepareStatement(insertEvent);
            st.setDate(1, new Date(order.getDate().getTime()));
            st.setString(2, "Order created. Status = " + order.getStatus());
            st.setInt(3, orderId);

            //close statement
            st.close();
        } catch (SQLException exception) {
            System.err.println("ERROR: Add order error => " + exception.getMessage());
        }
    }

    @Override
    public void setPayed(Order order) {
        try(Connection conn = databaseManager.getConnection()) {
            String updateOrderStatus = "UPDATE orders " +
                    "SET order_status = CAST(? AS OrderStatus) " +
                    "WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(updateOrderStatus);
            st.setString(1, OrderStatus.PAID.toString());
            st.setInt(2, order.getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException exception) {
            System.err.println("ERROR: Set payed order error => " + exception.getMessage());
        }
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
