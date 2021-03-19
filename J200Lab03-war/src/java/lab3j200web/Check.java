/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3j200web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lab3j200ejb.EJBDemo;

@WebServlet(name = "Check", urlPatterns = {"/Check"})
public class Check extends HttpServlet {

    EJBDemo registrator = lookupRegistratorLocal();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("getmsg") != null) {
            //запрос сообщения
            try (PrintWriter out = response.getWriter()) {
          
                String login = request.getParameter("login");
                String message = registrator.getMessage(login);
                String psw = request.getParameter("psw");   
                request.setAttribute("message", message);
                request.setAttribute("loginName", login);

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Check</title>");
                out.println("</head>");
                out.println("<body>");
 
                out.println("<h1>Получено сообщение " + message + "</h1>");
                out.println("</body>");
                out.println("</html>");

            //request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {

            String login = request.getParameter("login");
            String psw = request.getParameter("psw");

            boolean registered = registrator.login(login, psw);

            request.setAttribute("registered", registered ? "t" : "f");
            request.setAttribute("loginName", login);
            
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private EJBDemo lookupRegistratorLocal() {
        try {
            Context c = new InitialContext();
            return (EJBDemo) c.lookup("java:global/J200Lab03/J200Lab03-ejb/Registrator!lab3j200ejb.EJBDemo");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
