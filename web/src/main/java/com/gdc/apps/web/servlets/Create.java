package com.gdc.apps.web.servlets;

import com.gdc.apps.model.Product;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Create extends HttpServlet implements Service {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        int price = Integer.parseInt(request.getParameter("price"));
        Long id = service.getDao().save(new Product(category, name, price));
        response.getWriter().write("Item '" + name + "' saved with id: " + id + ".");
    }

}
