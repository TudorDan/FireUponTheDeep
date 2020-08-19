package com.codecool.shop.dao.database;

import com.codecool.shop.dao.DataStore;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Category;
import com.codecool.shop.model.Price;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {
    private static ProductDaoJdbc instance;
    private final DatabaseManager databaseManager;

    private ProductDaoJdbc() {
        DataStore dataStore = DataStore.getInstance();
        this.databaseManager = dataStore.getDatabaseManager();
    }

    public static ProductDaoJdbc getInstance() {
        if(instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        String query = "INSERT INTO products ("
                + " name,"
                + " description,"
                + " image_file_name,"
                + " supplier_id,"
                + " category_id ) VALUES ("
                + "?, ?, ?, ?, ?)";

        try {
            // set all the prepared statement parameters
            Connection conn = databaseManager.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, product.getName());
            st.setString(2, product.getDescription());
            st.setString(3, product.getImageFileName());
            st.setInt(4, product.getSupplier().getId());
            st.setInt(5, product.getCategory().getId());

            // execute the prepared statement insert
            st.executeUpdate();
            st.close();
        }
        catch (SQLException exception) {
            System.err.println("ERROR: Product add error => " + exception.getMessage());
        }
    }

    @Override
    public Product find(int id) {
        String query = "SELECT id, name, description, image_file_name, supplier_id, category_id" +
                " FROM products" +
                " WHERE id = ?";

        try {
            // set all the prepared statement parameters
            Connection conn = databaseManager.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id);

            // execute the prepared statement select
            ResultSet result = st.executeQuery();
            if(result.next()) {
                DataStore dataStore = DataStore.getInstance();
                String name = result.getString("name");
                String desc = result.getString("description");
                String image = result.getString("image_file_name");
                int supId = result.getInt("supplier_id");
                int catId = result.getInt("category_id");

                List<Price> prices = getPricesById(id);
                Supplier supplier = dataStore.supplierDao.find(supId);
                Category category = dataStore.categoryDao.find(catId);

                return new Product(id, name, desc, image, prices, category, supplier);
            }
            st.close();
        } catch (SQLException exception) {
            System.err.println("ERROR: Product find error => " + exception.getMessage());
        }
        return null;
    }

    private List<Price> getPricesById(int id) {
        String query = "SELECT sum, currency, date" +
                " FROM prices" +
                " WHERE product_id = ?";

        List<Price> prices = new ArrayList<>();
        try {
            // set all the prepared statement parameters
            Connection conn = databaseManager.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id);

            // execute the prepared statement select
            ResultSet result = st.executeQuery();
            while(result.next()) {
                double sum = result.getDouble("sum");
                String currency = result.getString("currency");
                Date date = result.getDate("date");
                prices.add(new Price(sum, currency, date));
            }
            st.close();
        } catch (SQLException exception) {
            System.err.println("ERROR: Product get prices error => " + exception.getMessage());
        }
        return prices;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM products WHERE id = ?";

        try {
            // set all the prepared statement parameters
            Connection conn = databaseManager.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id);

            // execute the prepared statement delete
            st.executeUpdate();
            st.close();
        } catch (SQLException exception) {
            System.err.println("ERROR: Product remove error => " + exception.getMessage());
        }
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT id, name, description, image_file_name, supplier_id, category_id" +
                " FROM products";

        List<Product> products = new ArrayList<>();
        try {
            // set all the prepared statement parameters
            Connection conn = databaseManager.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            // execute the prepared statement select
            ResultSet result = st.executeQuery();
            DataStore dataStore = DataStore.getInstance();
            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String desc = result.getString("description");
                String image = result.getString("image_file_name");
                int supId = result.getInt("supplier_id");
                int catId = result.getInt("category_id");

                List<Price> prices = getPricesById(id);
                Supplier supplier = dataStore.supplierDao.find(supId);
                Category category = dataStore.categoryDao.find(catId);

                products.add(new Product(id, name, desc, image, prices, category, supplier));
            }
            st.close();
        } catch (SQLException exception) {
            System.err.println("ERROR: Product find error => " + exception.getMessage());
        }
        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = "SELECT id, name, description, image_file_name, supplier_id, category_id" +
                " FROM products" +
                " WHERE supplier_id = ?";

        List<Product> products = new ArrayList<>();
        try {
            // set all the prepared statement parameters
            Connection conn = databaseManager.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, supplier.getId());

            // execute the prepared statement select
            ResultSet result = st.executeQuery();
            DataStore dataStore = DataStore.getInstance();
            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String desc = result.getString("description");
                String image = result.getString("image_file_name");
                int supId = result.getInt("supplier_id");
                int catId = result.getInt("category_id");

                List<Price> prices = getPricesById(id);
                Category category = dataStore.categoryDao.find(catId);

                products.add(new Product(id, name, desc, image, prices, category, supplier));
            }
            st.close();
        } catch (SQLException exception) {
            System.err.println("ERROR: Product find error => " + exception.getMessage());
        }
        return products;
    }

    @Override
    public List<Product> getBy(Category category) {
        String query = "SELECT id, name, description, image_file_name, supplier_id, category_id" +
                " FROM products" +
                " WHERE category_id = ?";

        List<Product> products = new ArrayList<>();
        try {
            // set all the prepared statement parameters
            Connection conn = databaseManager.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, category.getId());

            // execute the prepared statement select
            ResultSet result = st.executeQuery();
            DataStore dataStore = DataStore.getInstance();
            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String desc = result.getString("description");
                String image = result.getString("image_file_name");
                int supId = result.getInt("supplier_id");
                int catId = result.getInt("category_id");

                List<Price> prices = getPricesById(id);
                Supplier supplier = dataStore.supplierDao.find(supId);

                products.add(new Product(id, name, desc, image, prices, category, supplier));
            }
            st.close();
        } catch (SQLException exception) {
            System.err.println("ERROR: Product find error => " + exception.getMessage());
        }
        return products;
    }
}
