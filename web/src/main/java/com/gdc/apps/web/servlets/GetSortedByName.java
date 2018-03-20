package com.gdc.apps.web.servlets;

import com.gdc.apps.model.Product;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetSortedByName extends HttpServlet implements Service {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        //-1 is DESC, 1 is ASC
        int nameSort = Integer.valueOf(request.getParameter("nameSort"));
        int offset = Integer.valueOf(request.getParameter("offset"));
        List<Product> list;
        if (nameSort == 1) {
            list = service.getSortedByNameAsc(offset);
        } else if (nameSort == -1){
            list = service.getSortedByNameDesc(offset);
        } else {
            throw new ServletException("Invalid 'ratingSort' parameter. Must be 1 or -1");
        }
        String json =  new Gson().toJson(list);
        response.getWriter().write(json);
    }

}
