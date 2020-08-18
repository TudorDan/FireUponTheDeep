package com.codecool.shop.dao.database;

import com.codecool.shop.dao.DataStore;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {
    private static SupplierDaoJdbc instance;
    private final DatabaseManager databaseManager;

    private SupplierDaoJdbc() {
        DataStore dataStore = DataStore.getInstance();
        this.databaseManager = dataStore.getDatabaseManager();
    }

    public static SupplierDaoJdbc getInstance() {
        if(instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        String query = "INSERT INTO suppliers (" +
                " name," +
                " description ) VALUES (" +
                "?, ?)";

        try {
            // set all the prepared statement parameters
            Connection conn = databaseManager.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, supplier.getName());
            st.setString(2, supplier.getDescription());

            // execute the prepared statement insert
            st.executeUpdate();
            st.close();
        }
        catch (SQLException exception) {
            System.err.println("ERROR: Supplier add error => " + exception.getMessage());
        }
    }

    @Override
    public Supplier find(int id) {
        String query = "SELECT" +
                " id," +
                " name," +
                " description" +
                " FROM suppliers" +
                " WHERE id = ?";
        try {
            // set all the prepared statement parameters
            Connection conn = databaseManager.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id);

            // execute the prepared statement select
            ResultSet result = st.executeQuery();
            if(result.next()) {
                id = result.getInt("id");
                String name = result.getString("name");
                String description = result.getString("description");
                return new Supplier(id, name, description);
            }
            st.close();
        } catch (SQLException exception) {
            System.err.println("ERROR: Supplier find error => " + exception.getMessage());
        }
        return null;
    }

    @Override
    public void remove(int id) {
        // TODO: 18.08.2020 supplier remove(id)
    }

    @Override
    public List<Supplier> getAll() {
        String query = "SELECT" +
                " id," +
                " name," +
                " description" +
                " FROM suppliers";
        List<Supplier> suppliers = new ArrayList<>();

        try {
            // set all the prepared statement parameters
            Connection conn = databaseManager.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            // execute the prepared statement select
            ResultSet result = st.executeQuery();
            if(result.next()) {
                Integer id = result.getInt("id");
                String name = result.getString("name");
                String description = result.getString("description");
                suppliers.add(new Supplier(id, name, description));
            }
            st.close();
        } catch (SQLException exception) {
            System.err.println("ERROR: Supplier find error => " + exception.getMessage());
        }
        return suppliers;
    }
}
