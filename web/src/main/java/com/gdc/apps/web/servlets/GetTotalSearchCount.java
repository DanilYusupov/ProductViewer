package com.gdc.apps.web.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetTotalSearchCount extends HttpServlet implements Service {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        response.getWriter().write("{ \"count\" : \"" + service.getDao().getSearchSize(name) + "\"}");
    }

}
