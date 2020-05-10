<%@ page
	import="java.util.List,
	model.Concesionario,
	model.controladores.ConcesionarioControlador"%>

<jsp:include page="cabecera.jsp" flush="true">
	<jsp:param name="tituloDePagina" value="Listado de concesionarios" />
</jsp:include>

<div class="container">
	<h1>Listado de concesionarios</h1>
	<table class="table table-hover">
		<thead class="thead-dark">
			<tr>
				<th>CIF</th>
				<th>Nombre</th>
				<th>Localidad</th>
			</tr>
		</thead>
		<tbody>
			<%
				// Hasta la fila anterior ha llegado la primera fila de títulos de la tabla de profesores del centro educativo
			// En las siguietnes líneas se crea una fila "elemento <tr>" por cada fila de la tabla de BBDD "profesor"
			List<Concesionario> concesionarios = ConcesionarioControlador.getControlador().findAll();
			for (Concesionario concesionario : concesionarios) {
			%>
			<tr>
				<td><a
					href="fichaConcesionario.jsp?idConcesionario=<%=concesionario.getId()%>"> <%=concesionario.getCif()%>
				</a></td>
				<td><%=concesionario.getNombre()%></td>
				<td><%=concesionario.getLocalidad()%></td>
			</tr>
			<%
				}
			// Al finalizar de exponer la lista de profesores termino la tabla y cierro el fichero HTML
			%>
		</tbody>
	</table>
	<p />
	<input type="submit" class="btn btn-primary" name="nuevo" value="Nuevo"
		onclick="window.location='fichaConcesionario.jsp?idConcesionario=0'" />
</div>
<%@ include file="pie.jsp"%>