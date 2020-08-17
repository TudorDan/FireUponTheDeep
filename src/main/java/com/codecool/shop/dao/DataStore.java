package com.codecool.shop.dao;

import com.codecool.shop.dao.memory.*;

/**
 * Abstraction layer for easy selection of DAO implementation
 */
public class DataStore {
    private DaoImplementations daoImplementation;

    //DAOs
    public CategoryDao categoryDao;
    public SupplierDao supplierDao;
    public ProductDao productDao;
    public UserDao userDao;
    public OrderDao orderDao;

    private static DataStore instance = null;

    /**
     * Private constructor prevents others from instantiating
     */
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
                categoryDao = null;
                supplierDao = null;
                productDao = null;
                userDao = null;
                orderDao = null;
                break;
        }
    }

    public static DataStore getInstance() {
        if(instance == null) {
            instance = new DataStore();
        }
        return instance;
    }
}
