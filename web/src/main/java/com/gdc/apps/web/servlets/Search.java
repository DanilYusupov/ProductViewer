package com.gdc.apps.web.servlets;

import com.gdc.apps.model.Product;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Search extends HttpServlet implements Service {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        int offset = Integer.parseInt(request.getParameter("offset"));
        List<Product> list = service.getDao().search(name, offset);
        String json = new Gson().toJson(list);
        response.getWriter().write(json);
    }
}
