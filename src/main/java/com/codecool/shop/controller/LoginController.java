package com.codecool.shop.controller;

import com.codecool.shop.config.Template;
import com.codecool.shop.dao.DataStore;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login.html"})
public class LoginController extends HttpServlet {
    DataStore dataStore;
    TemplateEngine engine;
    WebContext context;

    private void setData(HttpServletRequest req, HttpServletResponse resp) {
        dataStore = DataStore.getInstance();

        //get and add categories and suppliers to context (for sidebar)
        engine = Template.getTemplateEngine(req.getServletContext());
        context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("categories", dataStore.categoryDao.getAll());
        context.setVariable("suppliers", dataStore.supplierDao.getAll());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setData(req, resp);

        //send context to template
        engine.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setData(req, resp);

        //get post parameters
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        //check login data and store user in session if ok
        User user = dataStore.userDao.find(email);
        HttpSession session = req.getSession();
        if (user != null && user.getPassword().equals(password)) { // login successful
            session.setAttribute("user", user);
            session.removeAttribute("error");
        } else { // login failed
            session.removeAttribute("user");
            session.setAttribute("error", true);
        }

        //send context to template
        engine.process("login.html", context, resp.getWriter());
    }
}
