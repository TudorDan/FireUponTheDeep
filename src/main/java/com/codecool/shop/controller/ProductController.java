package com.codecool.shop.controller;

import com.codecool.shop.config.Template;
import com.codecool.shop.dao.DataStore;
import com.codecool.shop.model.Category;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DataStore dataStore = DataStore.getInstance();

        //get and add categories and suppliers to context
        TemplateEngine engine = Template.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("categories", dataStore.categoryDao.getAll());
        context.setVariable("suppliers", dataStore.supplierDao.getAll());

        //get filtered products
        String categoryId = req.getParameter("category");
        String supplierId = req.getParameter("supplier");
        if (categoryId != null) {
            //category filter applied
            Category category = dataStore.categoryDao.find(Integer.parseInt(categoryId));
            context.setVariable("products", dataStore.productDao.getBy(category));
        } else if (supplierId != null) {
            //supplier filter applied
            Supplier supplier = dataStore.supplierDao.find(Integer.parseInt(supplierId));
            context.setVariable("products", dataStore.productDao.getBy(supplier));
        } else
            //no filter
            context.setVariable("products", dataStore.productDao.getAll());

        //send context to template
        engine.process("index.html", context, resp.getWriter());
    }

}
