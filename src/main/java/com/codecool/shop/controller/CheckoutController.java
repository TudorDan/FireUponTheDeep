package com.codecool.shop.controller;

import com.codecool.shop.config.Template;
import com.codecool.shop.dao.DataStore;
import com.codecool.shop.model.Cart;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout.html"})
public class CheckoutController extends HttpServlet {
    DataStore dataStore;
    TemplateEngine engine;
    WebContext context;
    HttpSession session;
    Cart cart;

    private void setData(HttpServletRequest req, HttpServletResponse resp) {
        dataStore = DataStore.getInstance();
        session = req.getSession();
        engine = Template.getTemplateEngine(req.getServletContext());
        context = new WebContext(req, resp, req.getServletContext());

        //create shopping cart if not present
        cart = (Cart) session.getAttribute("cart");
        if(cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setData(req, resp);
        engine.process("checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setData(req, resp);
/*
        //get new data
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String BillCountry = req.getParameter("BillCountry");
        String BillCity = req.getParameter("BillCity");
        String BillZipcode = req.getParameter("BillZipcode");
        String BillStreetAddress = req.getParameter("BillStreetAddress");
        String ShipCountry = req.getParameter("ShipCountry");
        String ShipCity = req.getParameter("ShipCity");
        String ShipZipcode = req.getParameter("ShipZipcode");
        String ShipStreetAddress = req.getParameter("ShipStreetAddress");

        //create new user
        Address billing = new Address(BillCountry, BillCity, BillZipcode, BillStreetAddress);
        Address shipping = new Address(ShipCountry, ShipCity, ShipZipcode, ShipStreetAddress);
        User newUser = new User(name, email, password, phone, billing, shipping);

        //replace the old
        User oldUser = (User) session.getAttribute("user");
        dataStore.userDao.replace(oldUser, newUser);
        session.setAttribute("user", newUser);
*/
        //send context to template
        engine.process("checkout.html", context, resp.getWriter());
    }
}
