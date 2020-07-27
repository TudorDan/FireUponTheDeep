package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout.html"})
public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        OrderDao orderDataStore = OrderDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("category", productCategoryDataStore.find(1));
        context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        context.setVariable("order", orderDataStore);
        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        context.setVariable("formError", false);
        engine.process("product/checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserDao userDataStore = UserDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        //get user data from post
        String name = req.getParameter("userName");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String billingAddress = req.getParameter("billingAddress");
        String shippingAddress = req.getParameter("shippingAddress");

        //set up first user
        if(name != null && !name.trim().isEmpty() && email != null && !email.trim().isEmpty()
                && phoneNumber != null && !phoneNumber.isEmpty() && billingAddress != null && !billingAddress.isEmpty()
                && shippingAddress != null && !shippingAddress.isEmpty()) {
            context.setVariable("formError", false);
            userDataStore.add(new User(name, email, phoneNumber, billingAddress, shippingAddress));
            //resp.sendRedirect("payment-page");
            engine.process("product/payment-page.html", context, resp.getWriter());
        } else {
            context.setVariable("formError", true);
            engine.process("product/checkout.html", context, resp.getWriter());
        }
    }
}
