/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.servlets;

import co.edu.udea.dao.AeropuertoDAO;
import co.edu.udea.dao.PqrsJpaController;
import co.edu.udea.dao.PQRSDAO;
import co.edu.udea.model.Aeropuerto;
import co.edu.udea.model.Pqrs;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wondercode
 */
@WebServlet(name = "ListPQRS", urlPatterns = {"/ListPQRS"})
public class ListPQRS extends HttpServlet {
    
    @EJB(name = "PQRSDAOImpl")
    private PQRSDAO pqrsDAO;
    @EJB(name="AeropuertoDAOImpl")
    private AeropuertoDAO aeropuertoDAO;
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
        Cookie[] cookies = null;
            cookies = request.getCookies();
            if(cookies.length<=1){
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Get a list of all pqrs
            List<Pqrs> list = pqrsDAO.getAllPqrs();           
            
            // Send all the pqrs in the attributes
            request.setAttribute("pqrss", list);
            // Redirect request to pqrslist.jsp
            request.getRequestDispatcher("/pqrslist.jsp").forward(request, response);
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
