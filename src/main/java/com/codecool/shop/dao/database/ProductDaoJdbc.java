package com.codecool.shop.dao.database;

import com.codecool.shop.dao.DataStore;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Category;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(Category category) {
        return null;
    }
}
