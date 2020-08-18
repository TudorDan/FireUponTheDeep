package com.codecool.shop.dao.database;

import com.codecool.shop.dao.DataStore;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        String query = "INSERT INTO suppliers ("
                + " name,"
                + " description ) VALUES ("
                + "?, ?)";

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
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
