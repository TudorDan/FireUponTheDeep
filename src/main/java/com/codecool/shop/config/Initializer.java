package com.codecool.shop.config;

import com.codecool.shop.dao.DaoImplementations;
import com.codecool.shop.dao.DataStore;
import com.codecool.shop.model.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataStore dataStore = DataStore.getInstance();

        //use parameter to select data container:
        // "DaoImplementations.DATABASE" for database storage
        // "DaoImplementations.MEMORY" for memory storage
        dataStore.SetDaoImplementation(DaoImplementations.MEMORY);

        if(dataStore.getDaoImplementation() == DaoImplementations.DATABASE) {
            //use parameter to select database:
            // "main_connection.properties" to use codecoolshop database
            // "test_connection.properties" to use test database
            dataStore.SetDatabase("main_connection.properties");
        }

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
        Date currentDate = new Date();
        Product product1 = new Product("Amazon Fire", 49.9f, "USD", currentDate, "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", "product_1.jpg", tablet, amazon);
        Product product2 = new Product("Lenovo IdeaPad Miix 700", 479, "USD", currentDate, "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", "product_2.jpg", smartphone, lenovo);
        Product product3 = new Product("Amazon Fire HD 8", 89, "USD", currentDate, "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", "product_3.jpg", smartphone, amazon);
        dataStore.productDao.add(product1);
        dataStore.productDao.add(product2);
        dataStore.productDao.add(product3);

        //setting up users
        Address address1 = new Address("Romania", "Bistrita", "420070", "str. Fericirii, nr. 14");
        Address address2 = new Address("Poland", "Warsav", "430070", "Happiness str., no. 15");
        User user1 = new User("Tudor Dan", "tudor_ist@gmail.com", "1234", "1234567890", address1, null, UserStatus.SIGNED);
        User user2 = new User("Pop Ion", "pop_ion@gmail.com", "2345", "0123456789", address2, address2, UserStatus.SIGNED);
        dataStore.userDao.add(user1);
        dataStore.userDao.add(user2);
    }
}
