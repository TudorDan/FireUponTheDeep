package com.codecool.shop.dao.database;

import com.codecool.shop.dao.CategoryDao;
import com.codecool.shop.dao.DataStore;
import com.codecool.shop.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CategoryDaoJdbc implements CategoryDao {
    private static CategoryDaoJdbc instance;
    private final DatabaseManager databaseManager;

    private CategoryDaoJdbc() {
        DataStore dataStore = DataStore.getInstance();
        this.databaseManager = dataStore.getDatabaseManager();
    }

    public static CategoryDaoJdbc getInstance() {
        if(instance == null) {
            instance = new CategoryDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Category category) {
        String query = "INSERT INTO categories (name, department, description) VALUES (?, ?, ?)";

        try {
            // set all the prepared statement parameters
            Connection conn = databaseManager.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, category.getName());
            st.setString(2, category.getDepartment());
            st.setString(3, category.getDescription());

            // execute the prepared statement insert
            st.executeUpdate();
            st.close();
        }
        catch (SQLException exception) {
            System.err.println("ERROR: Category add error => " + exception.getMessage());
        }
    }

    @Override
    public Category find(int id) {
        // TODO: 18.08.2020 Category find(id)
        return null;
    }

    @Override
    public void remove(int id) {
        // TODO: 18.08.2020 Category remove(id)
    }

    @Override
    public List<Category> getAll() {
        // TODO: 18.08.2020 Category getAll()
        return null;
    }
}
