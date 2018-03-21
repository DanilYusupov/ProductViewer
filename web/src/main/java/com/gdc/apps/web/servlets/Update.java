package com.gdc.apps.web.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Update extends HttpServlet implements Service {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String ratingStr = request.getParameter("rating");
        String priceStr = request.getParameter("price");

        short rating = -1;
        int price = -1;
        if (!ratingStr.equals("")) {
            rating = Short.parseShort(ratingStr);
        }
        if (!priceStr.equals("")) {
            price = Integer.parseInt(priceStr);
        }
        service.updateItem(id, name, category, rating, price);
//        response.sendRedirect("/home");
    }

}
