package co.edu.udea.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.edu.udea.dao.ClienteDAO;
import co.edu.udea.dao.DepartamentoDAO;
import co.edu.udea.dao.PersonalDAO;
import co.edu.udea.model.Cliente;
import co.edu.udea.model.ClientePK;
import co.edu.udea.model.Departamento;
import co.edu.udea.model.Personal;
import co.edu.udea.model.PersonalPK;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author corre
 */
@WebServlet(name = "login", urlPatterns = {"/"})
public class login extends HttpServlet {

    @EJB(name = "ClienteDAOImpl")
    private ClienteDAO clienteDao;
 
    private PersonalDAO personalDao;
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            // Get an array of Cookies associated with this domain
            
            String method = request.getMethod();
            //If POST
            if (method.equals("POST")) {
                String tipodoc = request.getParameter("tipodoc");
                String numdoc = request.getParameter("numdoc");
                String contrasena = request.getParameter("pwd");
                ClientePK cpk= new ClientePK(tipodoc,numdoc);
                PersonalPK ppk= new PersonalPK(tipodoc,numdoc);
                Cliente client=clienteDao.get(cpk);
                Personal per=personalDao.get(ppk);
                Cookie nombre;
                Cookie rol;
                if(client!=null && contrasena.equals(client.getContraseña())){
                    nombre=new Cookie("nombre",client.getNombres());
                    rol=new Cookie("rol","cliente");
                    nombre.setMaxAge(60*60*24*30);
                    rol.setMaxAge(60*60*24*30);
                    response.addCookie(nombre);
                    response.addCookie(rol);
                    request.getRequestDispatcher("./generar").forward(request, response);
                }else if(per!=null && contrasena.equals(per.getContraseña())){
                    nombre=new Cookie("nombre",per.getNombres());
                    rol=new Cookie("rol",per.getCodigoDepartamento().getNombre());
                    nombre.setMaxAge(60*60*24*30);
                    rol.setMaxAge(60*60*24*30);
                    response.addCookie(nombre);
                    response.addCookie(rol);
                    request.getRequestDispatcher("./ListPQRS").forward(request, response);
                }
                else{
                    request.getRequestDispatcher("./generar").forward(request, response);
                }
            }else{
                request.getRequestDispatcher("./ListPQRS").forward(request, response);
            }
            
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
