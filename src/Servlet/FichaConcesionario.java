package Servlet;

import java.io.IOException;		
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Servlet.Utils.FormularioIncorrectoRecibidoException;
import model.Concesionario;
import model.controladores.ConcesionarioControlador;



/**
 * Servlet implementation class PrimerServlet
 */
@WebServlet(description = "Primer Servlet", urlPatterns = { "/FichaConcesionario" })
public class FichaConcesionario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FichaConcesionario() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Para plasmar la informaci�n de un profesor determinado utilizaremos un
		// par�metro, que debe llegar a este Servlet obligatoriamente
		// El par�metro se llama "idProfesor" y gracias a �l podremos obtener la
		// informaci�n del profesor y mostrar sus datos en pantalla
		Concesionario concesionario = new Concesionario();
		// Obtengo el profesor a editar, en el caso de que el profesor exista se
		// cargar�n sus datos, en el caso de que no exista quedar� a null
		try {
			int idConcesionario = getIntParameter(request, "idConcesionario"); // Necesito obtener el id del profesor
																				// que se quiere editar. En caso de un
																				// alta
			// de profesor obtendr�amos el valor 0 como idProfesor
			concesionario = (Concesionario) ConcesionarioControlador.getControlador().find(idConcesionario);
		} catch (Exception e) {
		}

		// Ahora debo determinar cu�l es la acci�n que este p�gina deber�a llevar a
		// cabo, en funci�n de los par�metros de entrada al Servlet.
		// Las acciones que se pueden querer llevar a cabo son tres:
		// - "eliminar". S� que est� es la acci�n porque recibir� un un par�metro con el
		// nombre "eliminar" en el request
		// - "guardar". S� que est� es la acci�n elegida porque recibir� un par�metro en
		// el request con el nombre "guardar"
		// - Sin acci�n. En este caso simplemente se quiere editar la ficha

		// Variable con mensaje de informaci�n al usuario sobre alguna acci�n requerida
		String mensajeAlUsuario = "";

		// Acci�n eliminar
		if (request.getParameter("eliminar") != null) {
			// intento eliminar el registro, si el borrado es correcto redirijo la petici�n
			// hacia el listado de concesionarios
			try {
				ConcesionarioControlador.getControlador().remove(concesionario);
				response.sendRedirect(request.getContextPath() + "/ListadoConcesionario"); // hay que hacer la lista
			} catch (Exception e2) {
				mensajeAlUsuario = "No se puedo eliminar. Quiz�s haya restricciones asociadas a este registro";
			}
		}

		// Segunda acci�n posible: guardar
		if (request.getParameter("guardar") != null) {
			// Obtengo todos los datos del profesor y los almaceno en BBDD
			try {
				concesionario.setCif(getStringParameter(request, "cif"));
				concesionario.setNombre(getStringParameter(request, "nombre"));
				concesionario.setLocalidad(getStringParameter(request, "localidad"));

				ConcesionarioControlador.getControlador().save(concesionario);
				mensajeAlUsuario = "Guardado correctamente";
			} catch (FormularioIncorrectoRecibidoException e) {
				throw new ServletException(e);
			}
		}

		// Ahora mostramos la pantalla de respuesta al usuario
		response.getWriter().append(
				"<!DOCTYPE html PUBLIC \\\"-//W3C//DTD XHTML 1.0 Transitional//EN\\\" \\\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\\\">\r\n"
						+ "<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + "<head>\r\n"
						+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />\r\n"
						+ "<title>Documento sin t�tulo</title>\r\n" + "</head>\r\n" + "<script> \r\n"
						+ "function validateForm() {" + "var x = document.forms[\"form1\"][\"cif\"].value;"
						+ "var y = document.forms[\"form1\"][\"nombre\"].value;"
						+ "var z = document.forms[\"form1\"][\"localidad\"].value;" + "if (x == \"\"){"
						+ "alert (\"No has introducido el cif\");" + "return false;" + "}" + "if (y == \"\"){"
						+ "alert (\"No has introducido el nombre\");" + "return false;" + "}" + "if (z == \"\"){"
						+ "alert (\"No has introducido el localidad\");" + "return false;" + "}" + "}" + "</script>\r\n"
						+ "\r\n" + "<body "
						+ ((mensajeAlUsuario != null && mensajeAlUsuario != "")
								? "onLoad=\"alert('" + mensajeAlUsuario + "');\""
								: "")
						+ " >\r\n" + "<h1>Ficha de concesionario</h1>\r\n"
						+ "<a href=\"ListadoConcesionarios\">Ir al listado de concesionario</a>"
						+ "<form id=\"form1\" name=\"form1\" method=\"post\" action=\"FichaConcesionario\" onsubmit=\"return validateForm()\">\r\n"
						+ " <input type=\"hidden\" name=\"idConcesionario\" value=\""
						+ ((concesionario != null) ? concesionario.getId() : "") + "\"\\>" + "  <p>\r\n"
						+ "    <label for=\"cif\">Cif:</label>\r\n"
						+ "    <input name=\"cif\" type=\"text\" id=\"cif\"  value=\""
						+ ((concesionario != null) ? concesionario.getCif() : "") + "\" />\r\n" + "  </p>\r\n"
						+ "  <p>\r\n" + "    <label for=\"nombre\">Nombre: </label>\r\n"
						+ "    <input name=\"nombre\" type=\"text\" id=\"nombre\" value=\""
						+ ((concesionario != null) ? concesionario.getNombre() : "") + "\" />\r\n" + "  </p>\r\n"
						+ "  <p>\r\n" + "    <label for=\"localidad\">Localidad: </label>\r\n"
						+ "    <input name=\"localidad\" type=\"text\" id=\"localidad\" value=\""
						+ ((concesionario != null) ? concesionario.getLocalidad() : "") + "\" />\r\n" + "  </p>\r\n"
						+ "  <p>\r\n" + "    <input type=\"submit\" name=\"guardar\" value=\"Guardar\" />\r\n"
						+ "    <input type=\"submit\" name=\"eliminar\" value=\"Eliminar\" />\r\n" + "  </p>\r\n"
						+ "</form>" + "</body>\r\n" + "</html>\r\n" + "");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 
	 * @param request
	 * @param nombreParametro
	 * @return
	 * @throws FormularioIncorrectoRecibidoException
	 */
	public int getIntParameter(HttpServletRequest request, String nombreParametro)
			throws FormularioIncorrectoRecibidoException {
		try {
			return Integer.parseInt(request.getParameter(nombreParametro));
		} catch (Exception e) {
			throw new FormularioIncorrectoRecibidoException(
					"No se ha recibido valor entero para el par�metro " + nombreParametro);
		}
	}

	/**
	 * 
	 * @param request
	 * @param nombreParametro
	 * @return
	 * @throws FormularioIncorrectoRecibidoException
	 */
	public String getStringParameter(HttpServletRequest request, String nombreParametro)
			throws FormularioIncorrectoRecibidoException {
		if (request.getParameter(nombreParametro) != null) {
			return request.getParameter(nombreParametro);
		}
		throw new FormularioIncorrectoRecibidoException("No se ha recibido valor para el par�metro " + nombreParametro);
	}

}
