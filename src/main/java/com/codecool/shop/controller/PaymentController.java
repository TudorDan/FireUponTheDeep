package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.CreditCard;
import com.codecool.shop.utility.Mailer;
import com.codecool.shop.utility.OrderToJSON;
import com.codecool.shop.utility.StripePayment;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment-page.html"})
public class PaymentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    TemplateEngine engine;
    WebContext context;
    OrderDao orderDataStore;
    UserDao userDataStore;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        orderDataStore = OrderDaoMem.getInstance();
        engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("order", orderDataStore.find(1));
        engine.process("product/payment-page.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        orderDataStore = OrderDaoMem.getInstance();
        userDataStore = UserDaoMem.getInstance();
        engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("order", orderDataStore.find(1));
        engine.process("product/payment-page.html", context, resp.getWriter());
        String cardHolder = req.getParameter("card-holder");
        String cardNumber = req.getParameter("card-number");
        String expMonth = req.getParameter("exp-month");
        String expYear = req.getParameter("exp-year");
        String cvc = req.getParameter("cvc");

        CreditCard creditCard = new CreditCard(cardHolder, cardNumber, expMonth, expYear, cvc);
        StripePayment stripePayment = new StripePayment(creditCard);
        boolean success = stripePayment.executePayment();

        if (success) {
            OrderToJSON.convert(orderDataStore.find(1));
            Mailer mailer = new Mailer("pythonsendmailtest75@gmail.com", "lpiiamlxlfsnzwxs",
                    userDataStore.find(1).getEmail(), "[CodeCoolShop]" +
                    " Order " +
                    "Confirmation",
                    "Your order was processed!");
            mailer.start();
            resp.sendRedirect("order-confirmation.html");
        } else {
            context.setVariable("paymentError", true);
            engine.process("product/payment-page.html", context, resp.getWriter());
        }
    }
}
