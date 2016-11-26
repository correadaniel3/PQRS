package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("         <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Login</title>\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("\n");
      out.write("        <!-- Mobile support -->\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("\n");
      out.write("        <!-- Material Design fonts -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"http://fonts.googleapis.com/css?family=Roboto:300,400,500,700\" type=\"text/css\">\n");
      out.write("        <link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <!-- Bootstrap -->\n");
      out.write("        <link href=\"//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <!-- Bootstrap Material Design -->\n");
      out.write("        <link href=\"dist/css/bootstrap-material-design.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"dist/css/ripples.min.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <!-- Dropdown.js -->\n");
      out.write("        <link href=\"//cdn.rawgit.com/FezVrasta/dropdown.js/master/jquery.dropdown.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <!-- Page style -->\n");
      out.write("        <link href=\"index.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <!-- jQuery -->\n");
      out.write("        <script src=\"//code.jquery.com/jquery-1.10.2.min.js\"></script>\n");
      out.write("        <link rel=\"stylesheet\" href=\"custom.css\">\n");
      out.write("        <script src=\"https://use.fontawesome.com/7c2ce1fd9f.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"bs-docs-section\">\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"col-md-12\">\n");
      out.write("                        <div class=\"well bs-component\">\n");
      out.write("                            <form class=\"form-horizontal\" action=\"./login\" method=\"POST\">\n");
      out.write("                                 <fieldset>\n");
      out.write("                                    <legend>Inicio de Sesion</legend>\n");
      out.write("                                    <div class=\"form-froup label-floating is-empty\">\n");
      out.write("                                        <label for=\"usuario\" class=\"control-label\">Usuario: </label>\n");
      out.write("                                        <input class=\"form-control\" type=\"text\" name=\"usuario\" id=\"usuario\" required=\"true\">\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"form-froup label-floating is-empty\">\n");
      out.write("                                        <label for=\"pwd\" class=\"control-label\">Contraseña: </label>\n");
      out.write("                                        <input class=\"form-control\" type=\"password\" name=\"pwd\" id=\"pwd\" required=\"true\">\n");
      out.write("                                    </div>\n");
      out.write("                                 </fieldset>\n");
      out.write("                                <br>\n");
      out.write("                                <button type=\"submit\" class=\"btn btn-primary\" data-dpmaxz-eid=\"15\">Iniciar Sesion</button>\n");
      out.write("                            </form>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
