package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/example")
public class ExampleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/db2ds")
    DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request,
                        HttpServletResponse response)
                        throws ServletException, IOException {
        
        response.setContentType("text/html"); 
        PrintWriter pw = response.getWriter(); 


        pw.println("<h2>Example Servlet</h2>"); 

        try {
            DatabaseMetaData dbmd = ds.getConnection().getMetaData();
            pw.println("URL:" + dbmd.getURL());
        } catch (SQLException e) {
            pw.println("SQLState: " + e.getSQLState() + "\n");
            pw.println("SQLCode: " + e.getErrorCode() + "\n");
            e.printStackTrace(pw);
        }
        


    
   }
} 