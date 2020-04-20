package Servlet;

import java.io.IOException;	
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Concesionario;
import model.controladores.ConcesionarioControlador;


/**
 * Servlet implementation class PrimerServlet
 */
@WebServlet(urlPatterns = { "/ListadoConcesionario" })
public class ListadoConcesionario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoConcesionario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"<head>\r\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />\r\n" + 
				"<title>Documento sin título</title>\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body>\r\n" + 
				"<h1>Listado de concesionarios</h1>\r\n" + 
				"<table width=\"95%\" border=\"1\">\r\n" + 
				"  <tr>\r\n" + 
				"    <th scope=\"col\">Cif</th>\r\n" + 
				"    <th scope=\"col\">Nombre</th>\r\n" + 
				"    <th scope=\"col\">Localidad</th>\r\n" + 
				"  </tr>\r\n");
		// Hasta la fila anterior ha llegado la primera fila de títulos de la tabla de coches
		// En las siguietnes líneas se crea una fila "elemento <tr>" por cada fila de la tabla de BBDD "concesionario"
		List<Concesionario> concesionarios = ConcesionarioControlador.getControlador().findAll();
		for (Concesionario concesionario : concesionarios) {
			response.getWriter().append("" +
				"  <tr>\r\n" + 
				"    <td><a href=\"FichaConcesionario?idConcesionario=" + concesionario.getId() + "\">" + concesionario.getCif() + "</a></td>\r\n" + 
				"    <td>" + concesionario.getNombre() + "</td>\r\n" + 
				"    <td>" + concesionario.getLocalidad() + "</td>\r\n" + 
				"  </tr>\r\n");
		}
		// Al finalizar de exponer la lista de concesionarios termino la tabla y cierro el fichero HTML
		response.getWriter().append("" + 
		"</table>\r\n" + 
		"<p/><input type=\"submit\"  name=\"nuevo\" value=\"Nuevo\"  onclick=\"window.location='FichaConcesionario?idConcesionario=0'\"/>" + 
		"</body>\r\n" + 
		"</html>\r\n" + 
		"");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

