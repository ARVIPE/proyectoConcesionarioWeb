package Servlet;

import java.io.IOException;					
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.FormularioIncorrectoRecibidoException;
import Utils.RequestUtils;
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
		// Obtengo una HashMap con todos los par�metros del request, sea este del tipo que sea;
		HashMap<String, Object> hashMap = RequestUtils.requestToHashMap(request);
		// Para plasmar la informaci�n de un profesor determinado utilizaremos un
		// par�metro, que debe llegar a este Servlet obligatoriamente
		// El par�metro se llama "idConcesionario" y gracias a �l podremos obtener la
		// informaci�n del profesor y mostrar sus datos en pantalla
		Concesionario concesionario = null;
		// Obtengo el concesionario a editar, en el caso de que el concesionario exista se
		// cargar�n sus datos, en el caso de que no exista quedar� a null
		try {
			int idConcesionario = RequestUtils.getIntParameterFromHashMap(hashMap, "idConcesionario"); // Necesito obtener el id del profesor que se quiere editar. En caso de un alta
			// de profesor obtendr�amos el valor 0 como idProfesor
			if (idConcesionario != 0) {
				concesionario = (Concesionario) ConcesionarioControlador.getControlador().find(idConcesionario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Inicializo unos valores correctos para la presentaci�n del concesionario
		if (concesionario == null) {
			concesionario = new Concesionario();
		}
		if (concesionario.getCif() == null) concesionario.setCif("");
		if (concesionario.getNombre() == null) concesionario.setNombre("");
		if (concesionario.getLocalidad() == null) concesionario.setLocalidad("");

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
		if (RequestUtils.getStringParameterFromHashMap(hashMap, "eliminar") != null) {
			// Intento eliminar el registro, si el borrado es correcto redirijo la petici�n hacia el listado de profesores
			try {
				ConcesionarioControlador.getControlador().remove(concesionario);
				response.sendRedirect(request.getContextPath() + "/ListadoConcesionario"); // Redirecci�n del response hacia el listado
			}
			catch (Exception ex) {
				mensajeAlUsuario = "Imposible eliminar. Es posible que existan restricciones.";
			}
		}

		// Segunda acci�n posible: guardar
		if (RequestUtils.getStringParameterFromHashMap(hashMap, "guardar") != null) {
			// Obtengo todos los datos del profesor y los almaceno en BBDD
			try {
				concesionario.setCif(RequestUtils.getStringParameterFromHashMap(hashMap, "cif"));
				concesionario.setNombre(RequestUtils.getStringParameterFromHashMap(hashMap, "nombre"));
				concesionario.setLocalidad(RequestUtils.getStringParameterFromHashMap(hashMap, "localidad"));
				byte[] posibleImagen = RequestUtils.getByteArrayFromHashMap(hashMap, "ficheroImagen");
				if (posibleImagen != null && posibleImagen.length > 0) {
					concesionario.setImagen(posibleImagen);
				}

				// Finalmente guardo el objeto de tipo concesionario
				ConcesionarioControlador.getControlador().save(concesionario);
				mensajeAlUsuario = "Guardado correctamente";
			} catch (Exception e) {
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
						+ "<a href=\"ListadoConcesionario\">Ir al listado de concesionario</a>"
						+ "<form id=\"form1\" name=\"form1\" method=\"post\" action=\"FichaConcesionario\" enctype=\"multipart/form-data\" onsubmit=\"return validateForm()\">\r\n"
						+ " <img src=\"Utils/DownloadImagenConcesionario?idConcesionario=" + concesionario.getId() + "\" width='100px' height='100px'/>" 
						+ " <input type=\"hidden\" name=\"idConcesionario\" value=\"" + concesionario.getId() + "\"\\>"
						+ "  <p>\r\n"  
						+ "    <label for=\"ficheroImagen\">Imagen:</label>\r\n"  
						+ "    <input name=\"ficheroImagen\" type=\"file\" id=\"ficheroImagen\" />\r\n" 
						+ "  </p>\r\n"  
						+ "    <label for=\"cif\">Cif:</label>\r\n"
						+ "    <input name=\"cif\" type=\"text\" id=\"cif\"  value=\""
						+ ((concesionario != null) ? concesionario.getCif() : "") + "\" />\r\n" + "  </p>\r\n"
						+ "  <p>\r\n" + "    <label for=\"nombre\">Nombre: </label>\r\n"
						+ "    <input name=\"nombre\" type=\"text\" id=\"nombre\" value=\""
						+ ((concesionario != null) ? concesionario.getNombre() : "") + "\" />\r\n" + "  </p>\r\n"
						+ "  <p>\r\n" + "    <label for=\"localidad\">Localidad: </label>\r\n"
						+ "    <input name=\"localidad\" type=\"text\" id=\"localidad\" value=\""
						+ ((concesionario != null) ? concesionario.getLocalidad() : "") + "\" />\r\n" + "  </p>\r\n"
						+ "  <p>\r\n" + "<input type=\"submit\" name=\"guardar\" value=\"Guardar\" />\r\n"
						+ "  <input type=\"submit\" name=\"eliminar\" value=\"Eliminar\" />\r\n" + "  </p>\r\n"
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
	public String getStringParameter(HttpServletRequest request, String nombreParametro)
			throws FormularioIncorrectoRecibidoException {
		if (request.getParameter(nombreParametro) != null) {
			return request.getParameter(nombreParametro);
		}
		throw new FormularioIncorrectoRecibidoException("No se ha recibido valor para el par�metro " + nombreParametro);
	}

}
