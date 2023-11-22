/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.41
 * Generated at: 2023-11-20 15:05:39 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.demo.webapps.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class AlimentListForm_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


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

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Liste des Aliments</title>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/style.css\">\r\n");
      out.write("    <!-- Inclure les fichiers CSS/JS si nécessaire -->\r\n");
      out.write("    <!-- http://localhost:8080/demo/webapps/jsp/AlimentListForm.jsp -->\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <!-- Inclure le menu -->\r\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "menu.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <h1>Liste des Aliments</h1>\r\n");
      out.write("    <form id=\"deleteAlimentForm\">\r\n");
      out.write("        <table>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <th>ID</th>\r\n");
      out.write("                <th>Nom</th>\r\n");
      out.write("                <th>Poids Moyen</th>\r\n");
      out.write("                <th>Calories</th>\r\n");
      out.write("                <th>Vitamines C</th>\r\n");
      out.write("                <th>Type ID</th>\r\n");
      out.write("                <th>Couleur ID</th>\r\n");
      out.write("                <th>Supprimer</th>\r\n");
      out.write("                <th>Modifier</th>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <!-- Les lignes de la table seront remplis par un script JavaScript -->\r\n");
      out.write("        </table>\r\n");
      out.write("        <input type=\"submit\" value=\"Supprimer les aliments sélectionnés\">\r\n");
      out.write("    </form>\r\n");
      out.write("\r\n");
      out.write("    <script>\r\n");
      out.write("        window.onload = function() {\r\n");
      out.write("            fetch('http://localhost:8080/aliment')\r\n");
      out.write("                .then(response => response.json())\r\n");
      out.write("                .then(data => {\r\n");
      out.write("                    var table = document.querySelector('#deleteAlimentForm table');\r\n");
      out.write("                    data.forEach(function(aliment) {\r\n");
      out.write("                        var row = table.insertRow();\r\n");
      out.write("                        row.insertCell().textContent = aliment.id;\r\n");
      out.write("                        row.insertCell().textContent = aliment.nom;\r\n");
      out.write("                        row.insertCell().textContent = aliment.poids_moyen;\r\n");
      out.write("                        row.insertCell().textContent = aliment.calories;\r\n");
      out.write("                        row.insertCell().textContent = aliment.vitamines_C;\r\n");
      out.write("                        row.insertCell().textContent = aliment.type_id;\r\n");
      out.write("                        row.insertCell().textContent = aliment.couleur_id;\r\n");
      out.write("\r\n");
      out.write("                        // Ajouter une checkbox pour la suppression\r\n");
      out.write("                        var deleteCell = row.insertCell();\r\n");
      out.write("                        var checkbox = document.createElement('input');\r\n");
      out.write("                        checkbox.type = 'checkbox';\r\n");
      out.write("                        checkbox.name = 'delete';\r\n");
      out.write("                        checkbox.value = aliment.id;\r\n");
      out.write("                        deleteCell.appendChild(checkbox);\r\n");
      out.write("\r\n");
      out.write("                        // Ajouter un bouton pour la modification\r\n");
      out.write("                        var modifyCell = row.insertCell();\r\n");
      out.write("                        var modifyButton = document.createElement('button');\r\n");
      out.write("                        modifyButton.textContent = 'Modifier';\r\n");
      out.write("                        modifyButton.onclick = function() {\r\n");
      out.write("                            event.preventDefault(); // Empêcher la soumission du formulaire\r\n");
      out.write("                            window.location.href = 'AlimentUpdateForm.jsp?id=' + aliment.id;\r\n");
      out.write("                        };\r\n");
      out.write("                        modifyCell.appendChild(modifyButton);\r\n");
      out.write("                    });\r\n");
      out.write("                })\r\n");
      out.write("                .catch(error => console.error('Error:', error));\r\n");
      out.write("        };\r\n");
      out.write("\r\n");
      out.write("        document.getElementById('deleteAlimentForm').addEventListener('submit', function(event) {\r\n");
      out.write("            event.preventDefault();\r\n");
      out.write("\r\n");
      out.write("            var checkboxes = document.querySelectorAll('input[name=\"delete\"]:checked');\r\n");
      out.write("            if (checkboxes.length === 0) {\r\n");
      out.write("                alert(\"Aucun aliment n'est sélectionné pour la suppression.\");\r\n");
      out.write("                return;\r\n");
      out.write("            }\r\n");
      out.write("            // Afficher un popup de confirmation\r\n");
      out.write("            var userConfirmed = confirm(\"Êtes-vous sûr de vouloir supprimer les aliments sélectionnés ?\");\r\n");
      out.write("            if (!userConfirmed) {\r\n");
      out.write("                return; // Arrêter si l'utilisateur n'a pas confirmé\r\n");
      out.write("            }\r\n");
      out.write("            \r\n");
      out.write("            var idsToDelete = Array.from(checkboxes).map(cb => cb.value);\r\n");
      out.write("\r\n");
      out.write("            idsToDelete.forEach(function(id) {\r\n");
      out.write("                fetch('http://localhost:8080/aliment', {\r\n");
      out.write("                    method: 'DELETE',\r\n");
      out.write("                    headers: {\r\n");
      out.write("                        'Content-Type': 'application/json',\r\n");
      out.write("                    },\r\n");
      out.write("                    body: JSON.stringify({ id: parseInt(id) })\r\n");
      out.write("                })\r\n");
      out.write("                .then(response => {\r\n");
      out.write("                    if (!response.ok) {\r\n");
      out.write("                        throw new Error('Network response was not ok: ' + response.statusText);\r\n");
      out.write("                    }\r\n");
      out.write("                    return response.json();\r\n");
      out.write("                })\r\n");
      out.write("                .then(data => {\r\n");
      out.write("                    console.log('Aliment supprimé:', data);\r\n");
      out.write("                    // Vous pouvez ici actualiser la page ou supprimer la ligne correspondante du tableau\r\n");
      out.write("                     window.location.reload(); // Recharger la page\r\n");
      out.write("                })\r\n");
      out.write("                .catch(error => {\r\n");
      out.write("                    console.error('Error:', error);\r\n");
      out.write("                });\r\n");
      out.write("            });\r\n");
      out.write("        });\r\n");
      out.write("    </script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
