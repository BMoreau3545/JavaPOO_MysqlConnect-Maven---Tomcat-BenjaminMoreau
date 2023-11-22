package com.example.servlet;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.example.dao.TypeAlimentDAO;
import com.example.dao.DatabaseConnection;
import com.example.util.Routeur;

import java.io.IOException;

@WebServlet("/type_aliment/*")
public class TypeAlimentServlet extends HttpServlet {

    private TypeAlimentDAO typeAlimentDAO;
    private Routeur routeur;

    public void init() {
        ServletContext context = getServletContext();
        DatabaseConnection dbConnection = (DatabaseConnection) context.getAttribute("DB_CONNECTION");
        typeAlimentDAO = new TypeAlimentDAO(dbConnection);
        routeur = new Routeur();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        routeur.routeRequest(request, response, typeAlimentDAO);
    }
    
}
