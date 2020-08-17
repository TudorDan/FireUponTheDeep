package com.codecool.shop.dao;

import com.codecool.shop.dao.memory.CategoryDaoMem;
import com.codecool.shop.dao.memory.ProductDaoMem;
import com.codecool.shop.dao.memory.SupplierDaoMem;

/**
 * Abstraction layer for easy selection of DAO implementation
 */
public class DataStore {
    private DaoImplementations daoImplementation = null;

    //DAOs
    public CategoryDao categoryDao;
    public SupplierDao supplierDao;
    public ProductDao productDao;

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
                break;
            case DATABASE:
                categoryDao = null;
                supplierDao = null;
                productDao = null;
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
