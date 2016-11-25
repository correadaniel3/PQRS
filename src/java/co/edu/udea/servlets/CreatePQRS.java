/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.servlets;

import co.edu.udea.dao.AeropuertoDAO;
import co.edu.udea.dao.AnexoDAO;
import co.edu.udea.dao.ClienteDAO;
import co.edu.udea.dao.PQRSDAO;
import co.edu.udea.model.Aeropuerto;
import co.edu.udea.model.Anexo;
import co.edu.udea.model.AnexoPK;
import co.edu.udea.model.Cliente;
import co.edu.udea.model.ClientePK;
import co.edu.udea.model.Pqrs;
import co.edu.udea.util.DateUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author wondercode
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100, // 100 MB
        location = "/")
public class CreatePQRS extends HttpServlet {

    @EJB(name = "PQRSDAOImpl")
    private PQRSDAO pqrsDAO;
    @EJB(name = "AeropuertoDAOImpl")
    private AeropuertoDAO aeropuertoDAO;
    @EJB(name = "AnexoDAOImpl")
    private AnexoDAO anexoDAO;
    @EJB(name = "ClienteDAOImpl")
    private ClienteDAO clienteDao;
    
    
    
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
                List<Aeropuerto> aeropuertos = aeropuertoDAO.getAll();
                request.setAttribute("aeropuertos", aeropuertos);
        try (PrintWriter out = response.getWriter()) {
            //Get the http method
            String method = request.getMethod();
            //If POST
            if (method.equals("POST")) {
                //Get the input parameters
                byte[] _file = null;
                String codigo = new Date().getTime() + "";
                String descripcion = request.getParameter("descripcion");
                String tipo = request.getParameter("tipo");
                String fechaHechos = request.getParameter("fechaHechos");
                Date fechap = new Date();
                String aeropuerto = request.getParameter("aeropuerto");
                Pqrs pqrs = new Pqrs(codigo, descripcion, tipo, fechap);
                pqrs.setCodigoAeropuerto(aeropuerto);
                ClientePK cpk = new ClientePK("CC", "10289495");
                Cliente cliente = clienteDao.get(cpk);
                pqrs.setCliente(cliente);
                System.out.println(codigo);
                System.out.println(descripcion);
                System.out.println(tipo);
                System.out.println(aeropuerto);
                System.out.println(cliente);
                Part file = request.getPart("file");
                if (fechaHechos != null && !fechaHechos.isEmpty()) {
                    pqrs.setFechaHechos(DateUtil.getDate(fechaHechos));
                }
                try {
                    pqrsDAO.addPqrs(pqrs);
                } catch (EJBException e) {
                    @SuppressWarnings("ThrowableResultIgnored")
                    Exception cause = e.getCausedByException();
                    if (cause instanceof ConstraintViolationException) {
                        @SuppressWarnings("ThrowableResultIgnored")
                        ConstraintViolationException cve = (ConstraintViolationException) e.getCausedByException();
                        for (Iterator<ConstraintViolation<?>> it = cve.getConstraintViolations().iterator(); it.hasNext();) {
                            ConstraintViolation<? extends Object> v = it.next();
                            System.err.println(v);
                            System.err.println("==>>" + v.getMessage());
                        }
                    }
                }
                // Verify file is not null
                if (file.getSize() != 0) {
                    // Read bytes from file
                    try (InputStream is = file.getInputStream()) {
                        _file = new byte[is.available()];
                        is.read(_file);
                        is.close();
                    }
                    AnexoPK pk = new AnexoPK("1", codigo);
                    Anexo anexo = new Anexo(pk, _file, "Document");
                    anexoDAO.save(anexo);
                }
                request.getRequestDispatcher("./Detail?id="+codigo).forward(request, response);
            } else {
                
                

                // Redirect request to vehiculos.jsp
                request.getRequestDispatcher("/create.jsp").forward(request, response);
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
