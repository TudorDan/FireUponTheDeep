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
        dataStore.SetDaoImplementation(DaoImplementations.MEMORY); //change parameter to switch DAOs

        //setting up suppliers
        Supplier prada = new Supplier("Prada", "The most beautifull jewelries");
        Supplier bvlgari = new Supplier("Bvlgari", "The most beautifull jewelries");
        dataStore.supplierDao.add(prada);
        dataStore.supplierDao.add(bvlgari);

        //setting up categories
        Category ring = new Category("Ring", "Jewelry", "Best accesories in the wolrd");
        Category necklace = new Category("Necklace", "Jewelry", "Best accesories in the wolr");
        Category earrings = new Category("Earring", "Jewelry", "Best accesories in the wolr");
        dataStore.categoryDao.add(ring);
        dataStore.categoryDao.add(necklace);
        dataStore.categoryDao.add(earrings);

        //setting up products
        Date currentDate = new Date();
        Product product1 = new Product("Rings with saphire and diamonds", 250 , "USD", currentDate, "24 karate white gold rings with saphire and diamonds", "pic1.jpg", ring, prada);
        Product product2 = new Product("Earrings with rubin", 500, "USD", currentDate, "Ruby inlaid earrings in genuine 925 silver. Red tourmaline jewelry. The earrings are plated with 18 carat white gold.", "pic2.jpg", earrings, bvlgari);
        Product product3 = new Product("Necklace with blue crystal", 350, "USD", currentDate, "Heart of the Ocean high quality blue pendant necklace from the movie Titanic. Simple and fashionable design. Material: crystal, resin and metal alloy. Pendant dimensions: 2x2cm. Chain length: 38 cm.", "pic3.jpg", necklace, prada);
        Product product4 = new Product("Earrings with emerald and white gold", 1000, "USD", currentDate, "18 karat gold plated earrings. Contains 4 emeralds and white zirconium 292.", "pic4.jpg", earrings, prada);
        Product product5 = new Product("Rings with tourmaline and diamond", 1780, "USD", currentDate, "18 karat white gold rings with tourmaline in pink and green shades, plated with diamonds.", "pic5.jpg", ring, prada);
        Product product6 = new Product("Necklace with rubin and white saphire", 500 , "USD", currentDate, "Silver necklace with Chatham ruby and white sapphire; modern jewelry with silver chain included and Argentium silver.", "pic6.jpg", necklace, bvlgari);
        Product product7 = new Product("Silver necklace with citrine", 600, "USD", currentDate, "Silver necklace with citrine amber; modern jewelry with silver chain included and Argentium silver.", "pic7.jpg", necklace, bvlgari);
        Product product8 = new Product("Silver earrings with amethyst", 900, "USD", currentDate, "Amethyst silver earrings; Modern jewelry with purple gemstone from 2001, with Argentium silver.", "pic8.jpg", earrings, bvlgari);
        Product product9 = new Product("Silver earrings with topaz", 600, "USD", currentDate, "Silver earrings with turquoise blue topaz; modern jewelry with ecological stone, with Argentium silver.", "pic9.jpg", earrings, prada);
        // Product product10 = new Product("Silver necklace with zirconium", 300, "USD ", currentDate, "Silver necklace with zirconium; Modern jewelry with silver chain included, diamond substitute and Argentium silver.", "pic10.jpg", necklace, bvlgari);
        Product product11 = new Product("Amber necklace", 550, "USD", currentDate, "Authentic pendant with natural citrine (not created in the laboratory), cubic zirconia. Genuine 925 silver necklace plated with gold. Fashionable gemstone jewelry. Dimensions: approx. 10x14mm. Lead-free, nickel-free, does not cause allergies.", "pic12.jpg", necklace, bvlgari);
        dataStore.productDao.add(product1);
        dataStore.productDao.add(product2);
        dataStore.productDao.add(product3);
        dataStore.productDao.add(product4);
        dataStore.productDao.add(product5);
        dataStore.productDao.add(product6);
        dataStore.productDao.add(product7);
        dataStore.productDao.add(product8);
        dataStore.productDao.add(product9);
        // dataStore.productDao.add(product10);
        dataStore.productDao.add(product11);

        //setting up users
        Address address1 = new Address("Romania", "Bistrita", "420010", "str. Alecsandri, nr. 6");
        Address address2 = new Address("Poland", "Warsaw", "430070", "Happiness str., no. 15");
        User user1 = new User("Tudor Dan", "tudor_ist@gmail.com", "1234", "1234567890", address1, null, UserStatus.SIGNED);
        User user2 = new User("Pop Ion", "pop_ion@gmail.com", "2345", "0123456789", address2, address2, UserStatus.SIGNED);
        dataStore.userDao.add(user1);
        dataStore.userDao.add(user2);
    }
}
