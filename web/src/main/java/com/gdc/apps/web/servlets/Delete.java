package com.gdc.apps.web.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Delete extends HttpServlet implements Service {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        Long id = Long.valueOf(request.getParameter("id"));
        boolean deleted = service.getDao().delete(id);
//        response.sendRedirect("/home");
    }
}
