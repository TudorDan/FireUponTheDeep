package com.codecool.shop.controller;

import com.codecool.shop.config.Template;
import com.codecool.shop.dao.CategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
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
        //access data stores
        ProductDao productDataStore = ProductDaoMem.getInstance();
        CategoryDao categoryDataStore = CategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //get and add categories and suppliers to context
        TemplateEngine engine = Template.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("categories", categoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());

        //get filtered products
        String categoryId = req.getParameter("category");
        String supplierId = req.getParameter("supplier");
        if (categoryId != null) {
            //category filter applied
            Category category = categoryDataStore.find(Integer.parseInt(categoryId));
            context.setVariable("products", productDataStore.getBy(category));
        } else if (supplierId != null) {
            //supplier filter applied
            Supplier supplier = supplierDataStore.find(Integer.parseInt(supplierId));
            context.setVariable("products", productDataStore.getBy(supplier));
        } else
            //no filter
            context.setVariable("products", productDataStore.getAll());

        //send context to template
        engine.process("index.html", context, resp.getWriter());
    }

}
