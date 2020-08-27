package com.codecool.shop.dao.database;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    private static UserDaoJdbc instance;
    private DatabaseManager databaseManager;

    private UserDaoJdbc() {
    }

    public static UserDaoJdbc getInstance() {
        if (instance == null) {
            instance = new UserDaoJdbc();
        }
        return instance;
    }

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public void add(User user) {
        /*String addressQuery = "INSERT INTO addresses (country, city, zipcode, home_address) " +
                "VALUES (?, ?, ?, ?) " +
                "RETURNING id";

        //insert billing if exists
        Address billing = user.getBilling();
        if(billing != null) {
            try (Connection conn = databaseManager.getConnection()) {
                // set all the prepared statement parameters
                PreparedStatement st = conn.prepareStatement(addressQuery);
                st.setString(1, billing.getCountry());
                st.setString(2, billing.getCity());
                st.setString(3, billing.getZipcode());
                st.setString(4, billing.getHomeAddress());

                // execute the prepared statement insert,
                // get id of inserted addres, update billing
                st.execute();
                ResultSet rs = st.getResultSet();
                if (rs.next()) {
                    billing.setId(rs.getInt(1));
                }
                st.close();
            } catch (SQLException exception) {
                System.err.println("ERROR: Billing address add error => " + exception.getMessage());
            }
        }

        //insert shipping if exists
        //if shipping missing but billing present assume shipping = billing
        Address shipping = user.getShipping();
        if(shipping == null) {
            shipping = billing;
        }
        if(shipping != null) {
            try (Connection conn = databaseManager.getConnection()) {
                // set all the prepared statement parameters
                PreparedStatement st = conn.prepareStatement(addressQuery);
                st.setString(1, shipping.getCountry());
                st.setString(2, shipping.getCity());
                st.setString(3, shipping.getZipcode());
                st.setString(4, shipping.getHomeAddress());

                // execute the prepared statement insert,
                // get id of inserted address, update billing
                st.execute();
                ResultSet rs = st.getResultSet();
                if (rs.next()) {
                    shipping.setId(rs.getInt(1));
                }
                st.close();
            } catch (SQLException exception) {
                System.err.println("ERROR: Shipping address add error => " + exception.getMessage());
            }
        }
*/
        String usersQuery = "INSERT INTO users (name, email, password, " +
                "phone_number, user_status) " +
                "VALUES (?, ?, ?, ?, ?::UserStatus) " +
                "RETURNING id";

        String ordersQuery = "INSERT INTO orders (name, user_id, is_my_cart, " +
                "date, order_status) " +
                "VALUES (?, ?, ?, ?, ?::OrderStatus) ";

        try (Connection conn = databaseManager.getConnection()) {
            //insert new user
            PreparedStatement st = conn.prepareStatement(usersQuery);
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());
            st.setString(4, user.getPhoneNumber());
            st.setString(5, user.getUserStatus().toString());

            // execute the prepared statement insert, get id of inserted user,
            // update parameter
            st.execute();
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }

            //insert empty "my cart" order for the user
            st = conn.prepareStatement(ordersQuery);
            st.setString(1, "MyCart");
            st.setInt(2, user.getId());
            st.setBoolean(3, true);
            st.setDate(4, new Date(new java.util.Date().getTime()));
            st.setString(5, OrderStatus.CHECKED.toString());

            // execute the prepared statement insert
            st.executeUpdate();

            st.close();
        } catch (SQLException exception) {
            System.err.println("ERROR: User add error => " + exception.getMessage());
        }
    }

    @Override
    public boolean isSignedUp(String email) {
        String query = "SELECT id FROM users WHERE email = ?";

        try (Connection conn = databaseManager.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, email);

            // execute the prepared statement select
            ResultSet result = st.executeQuery();
            if (result.next()) {
                st.close();
                return true;
            }
            st.close();
        } catch (SQLException exception) {
            System.err.println("ERROR: User isSignedUp error => " + exception.getMessage());
        }
        return false;
    }

    @Override
    public User getAuthenticatedUser(String email, String password) {
        String query = "SELECT id, name, email, password, phone_number, " +
                "billing_id, shipping_id, user_status " +
                "FROM users " +
                "WHERE email = ? and password = ?";

        try (Connection conn = databaseManager.getConnection()) {
            // set all the prepared statement parameters
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, email);
            st.setString(2, password);

            // execute the prepared statement select
            ResultSet result = st.executeQuery();
            if (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String phone = result.getString("phone_number");
                int shipId = result.getInt("shipping_id");
                int billId = result.getInt("billing_id");
                UserStatus status = UserStatus.valueOf(result.getString("user_status"));

                Address shipping = getAddressById(shipId);
                Address billing = getAddressById(billId);
                Cart myCart = new Cart(getMyCartItemsByUserId(id));

                return new User(id, name, email, password, phone, billing, shipping, status, myCart);
            }
            st.close();
        } catch (SQLException exception) {
            System.err.println("ERROR: Product find error => " + exception.getMessage());
        }
        return null;
    }

    private Address getAddressById(int id) {
        String query = "SELECT id, country, city, zipcode, home_address" +
                " FROM addresses" +
                " WHERE id = ?";

        try (Connection conn = databaseManager.getConnection()) {
            // set all the prepared statement parameters
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id);

            // execute the prepared statement select
            ResultSet result = st.executeQuery();
            if (result.next()) {
                String country = result.getString("country");
                String city = result.getString("city");
                String zipcode = result.getString("zipcode");
                String home = result.getString("home_address");
                return new Address(id, country, city, zipcode, home);
            }
            st.close();
        } catch (SQLException exception) {
            System.err.println("ERROR: Get address by id error => " + exception.getMessage());
        }
        return null;
    }

    private List<Item> getMyCartItemsByUserId(int id) {
        List<Item> items = new ArrayList<>();
        return items;
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
}
