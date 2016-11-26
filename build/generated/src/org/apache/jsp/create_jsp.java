package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class create_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>Generar PQRS</title>\r\n");
      out.write("        <meta charset=\"utf-8\">\r\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("\r\n");
      out.write("        <!-- Mobile support -->\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("\r\n");
      out.write("        <!-- Material Design fonts -->\r\n");
      out.write("\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"http://fonts.googleapis.com/css?family=Roboto:300,400,500,700\" type=\"text/css\">\r\n");
      out.write("        <link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <!-- Bootstrap -->\r\n");
      out.write("        <link href=\"//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("        <!-- Bootstrap Material Design -->\r\n");
      out.write("        <link href=\"dist/css/bootstrap-material-design.css\" rel=\"stylesheet\">\r\n");
      out.write("        <link href=\"dist/css/ripples.min.css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("        <!-- Dropdown.js -->\r\n");
      out.write("        <link href=\"//cdn.rawgit.com/FezVrasta/dropdown.js/master/jquery.dropdown.css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("        <!-- Page style -->\r\n");
      out.write("        <link href=\"index.css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("        <!-- jQuery -->\r\n");
      out.write("        <script src=\"//code.jquery.com/jquery-1.10.2.min.js\"></script>\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"custom.css\">\r\n");
      out.write("        <script src=\"https://use.fontawesome.com/7c2ce1fd9f.js\"></script>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <br><br>\r\n");
      out.write("        <div class=\"container\">\r\n");
      out.write("            <div class=\"bs-docs-section\">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                    <div class=\"col-md-12\">\r\n");
      out.write("                        <div class=\"well bs-component\">\r\n");
      out.write("                            <form class=\"form-horizontal\" data-dpmaxz-eid=\"7\" action=\"./createPQRS\" method=\"POST\" enctype=\"multipart/form-data\">\r\n");
      out.write("                                <fieldset>\r\n");
      out.write("                                    <legend>Generar PQRS</legend>\r\n");
      out.write("                                    <div class=\"form-group\">\r\n");
      out.write("                                        <label for=\"tipo\" class=\"control-label\">Seleccione el tipo:</label>\r\n");
      out.write("                                        <select name=\"tipo\" id=\"tipo\" class=\"form-control\">\r\n");
      out.write("                                            <option value=\"Peticion\">Peticion</option>\r\n");
      out.write("                                            <option value=\"Queja\">Queja</option>\r\n");
      out.write("                                            <option value=\"Reclamo\">Reclamo</option>\r\n");
      out.write("                                            <option value=\"Solicitud\">Solicitud</option>\r\n");
      out.write("                                        </select>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    <br>\r\n");
      out.write("                                    <div class=\"form-froup\">\r\n");
      out.write("                                        <label for=\"fecha\" class=\"control-label\">Fecha de los hechos: </label>\r\n");
      out.write("                                        <script src=\"http://cdn.jsdelivr.net/webshim/1.12.4/extras/modernizr-custom.js\"></script>\r\n");
      out.write("                                        <script src=\"http://cdn.jsdelivr.net/webshim/1.12.4/polyfiller.js\"></script>\r\n");
      out.write("                                        <script>\r\n");
      out.write("                                            webshims.setOptions('waitReady', false);\r\n");
      out.write("                                            webshims.setOptions('forms-ext', {types: 'date'});\r\n");
      out.write("                                            webshims.polyfill('forms forms-ext');\r\n");
      out.write("                                        </script>\r\n");
      out.write("\r\n");
      out.write("                                        <input type=\"date\" name=\"fechaHechos\" id=\"fecha\" min=\"2016-01-02\"/>\r\n");
      out.write("                                    </div>\r\n");
      out.write("\r\n");
      out.write("                                    <div class=\"form-group\">\r\n");
      out.write("                                        <label for=\"aeropuerto\" class=\"control-label\">Seleccione el aeropuerto:</label>\r\n");
      out.write("                                        <select name=\"aeropuerto\" id=\"aeropuerto\" class=\"form-control\">\r\n");
      out.write("                                           \r\n");
      out.write("                                            ");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                                        </select>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    <br>\r\n");
      out.write("                                    <div class=\"form-froup label-floating is-empty\">\r\n");
      out.write("                                        <label for=\"descripcion\" class=\"control-label\">Descripcion: </label>\r\n");
      out.write("                                        <textarea class=\"form-control\" name=\"descripcion\" id=\"descripcion\" required=\"true\"></textarea>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    <div class=\"form-group is-empty is-fileinput\" style=\"float:left;\">\r\n");
      out.write("                                        <label for=\"inputFile\" class=\"col-md-2 control-label\">Archivo</label>\r\n");
      out.write("\r\n");
      out.write("                                        <div class=\"col-md-10\">\r\n");
      out.write("                                            <input type=\"text\" readonly=\"\" class=\"form-control\" placeholder=\"Adjuntar Archivo\" data-dpmaxz-eid=\"13\">\r\n");
      out.write("                                            <input type=\"file\" id=\"inputFile\" name=\"file\" multiple=\"\">\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </fieldset>\r\n");
      out.write("                                <br>\r\n");
      out.write("                                <button type=\"submit\" class=\"btn btn-primary\" data-dpmaxz-eid=\"15\">Generar</button>\r\n");
      out.write("                            </form>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <script src=\"//code.jquery.com/jquery-1.10.2.min.js\"></script>\r\n");
      out.write("        <script src=\"//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>\r\n");
      out.write("        <script src=\"//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>\r\n");
      out.write("        <script src=\"dist/js/ripples.min.js\"></script>\r\n");
      out.write("        <script src=\"dist/js/material.min.js\"></script>\r\n");
      out.write("        <script src=\"//fezvrasta.github.io/snackbarjs/dist/snackbar.min.js\"></script>\r\n");
      out.write("        <script src=\"//cdnjs.cloudflare.com/ajax/libs/noUiSlider/6.2.0/jquery.nouislider.min.js\"></script>\r\n");
      out.write("        <script>\r\n");
      out.write("                                            $(function () {\r\n");
      out.write("                                                $.material.init();\r\n");
      out.write("                                                $(\".shor\").noUiSlider({\r\n");
      out.write("                                                    start: 40,\r\n");
      out.write("                                                    connect: \"lower\",\r\n");
      out.write("                                                    range: {\r\n");
      out.write("                                                        min: 0,\r\n");
      out.write("                                                        max: 100\r\n");
      out.write("                                                    }\r\n");
      out.write("                                                });\r\n");
      out.write("\r\n");
      out.write("                                                $(\".svert\").noUiSlider({\r\n");
      out.write("                                                    orientation: \"vertical\",\r\n");
      out.write("                                                    start: 40,\r\n");
      out.write("                                                    connect: \"lower\",\r\n");
      out.write("                                                    range: {\r\n");
      out.write("                                                        min: 0,\r\n");
      out.write("                                                        max: 100\r\n");
      out.write("                                                    }\r\n");
      out.write("                                                });\r\n");
      out.write("                                            });\r\n");
      out.write("        </script>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
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

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${aeropuertos}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_0.setVar("aero");
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("                                                <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${aero.codigo}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write('"');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${aero.nombre}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</option>\r\n");
          out.write("                                            ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }
}
