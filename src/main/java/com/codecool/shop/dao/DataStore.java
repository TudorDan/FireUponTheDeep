package com.codecool.shop.dao;

import com.codecool.shop.dao.database.CategoryDaoJdbc;
import com.codecool.shop.dao.database.DatabaseManager;
import com.codecool.shop.dao.database.ProductDaoJdbc;
import com.codecool.shop.dao.database.SupplierDaoJdbc;
import com.codecool.shop.dao.memory.*;

import java.io.IOException;

/**
 * Abstraction layer for easy selection of DAO implementation
 */
public class DataStore {
    private DaoImplementations daoImplementation;
    private DatabaseManager databaseManager = null;

    //DAOs
    public CategoryDao categoryDao;
    public SupplierDao supplierDao;
    public ProductDao productDao;
    public UserDao userDao;
    public OrderDao orderDao;

    private static DataStore instance = null;

    private DataStore() {}

    public void SetDaoImplementation(DaoImplementations daoImplementation) {
        this.daoImplementation = daoImplementation;
        switch (daoImplementation) {
            case MEMORY:
                categoryDao = CategoryDaoMem.getInstance();
                supplierDao = SupplierDaoMem.getInstance();
                productDao = ProductDaoMem.getInstance();
                userDao = UserDaoMem.getInstance();
                orderDao = OrderDaoMem.getInstance();
                break;
            case DATABASE:
                categoryDao = CategoryDaoJdbc.getInstance();
                supplierDao = SupplierDaoJdbc.getInstance();
                productDao = ProductDaoJdbc.getInstance();
                userDao = null;
                orderDao = null;
                break;
        }
    }

    public void SetDatabase(String propFile) {
        try {
            databaseManager = DatabaseManager.getInstance(propFile);
        } catch (IOException e) {
            System.err.println("ERROR: DatabaseManager initialization failed = " + e.getMessage());
            e.printStackTrace();
        }
    }

    public DaoImplementations getDaoImplementation() {
        return daoImplementation;
    }

    public static DataStore getInstance() {
        if(instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
