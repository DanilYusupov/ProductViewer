package com.gdc.apps.web.servlets;

import com.gdc.apps.model.Product;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetSortedTable extends HttpServlet implements Service {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));
        String sortBy = request.getParameter("sortBy");
        String sortDir = request.getParameter("sortDir");
        List<Product> list = service.getSortedList(page, size, sortBy, sortDir);
        String json = new Gson().toJson(list);
        response.getWriter().write(json);
    }

}
