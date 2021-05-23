/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhkt.mvc.webmvcmaven.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kieu Trong Khanh
 */
public class DispatchServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_CONTROLLER = "DeleteAccountServlet";
    private final String START_UP_CONTROLLER = "StartUpServlet";
    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemsToCartServlet";
    private final String VIEW_CART = "viewCart.jsp";
    private final String REMOVE_ITEM_FROM_CART_CONTROLLER = "RemoveItemsFromCartServlet";
    private final String CREATE_NEW_ACCOUNT_CONTROLLER = "CreateNewAccountServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        String button = request.getParameter("btAction");
        
        try  {
            if (button == null) {
                //url = START_UP_CONTROLLER;
            } else if (button.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (button.equals("Search")) {
                url = SEARCH_CONTROLLER;
            } else if (button.equals("delete")) {
                url = DELETE_CONTROLLER;
            } else if ("Add Book to your Cart".equals(button)) {
                url = ADD_ITEM_TO_CART_CONTROLLER;
            } else if ("View Your Cart".equals(button)) {
                url = VIEW_CART;
            } else if ("Remove Selected Books".equals(button)) {
                url = REMOVE_ITEM_FROM_CART_CONTROLLER;
            } else if ("Create New Account".equals(button)) {
                url = CREATE_NEW_ACCOUNT_CONTROLLER;
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
