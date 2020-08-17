package com.codecool.shop.config;

import com.codecool.shop.dao.DaoImplementations;
import com.codecool.shop.dao.DataStore;
import com.codecool.shop.model.Category;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataStore dataStore = DataStore.getInstance();
        dataStore.SetDaoImplementation(DaoImplementations.MEMORY); //change parameter to switch DAOs

        //setting up suppliers
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        dataStore.supplierDao.add(amazon);
        dataStore.supplierDao.add(lenovo);

        //setting up categories
        Category tablet = new Category("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        Category smartphone = new Category("SmartPhone", "Hardware", "Smart, multifunctional mobile device.");
        dataStore.categoryDao.add(tablet);
        dataStore.categoryDao.add(smartphone);

        //setting up products
        dataStore.productDao.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", "product_1.jpg", tablet, amazon));
        dataStore.productDao.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", "product_2.jpg", smartphone, lenovo));
        dataStore.productDao.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", "product_3.jpg", tablet, amazon));
    }
}
