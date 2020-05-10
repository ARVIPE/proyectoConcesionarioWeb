<%@ page
	import="java.util.List,
	model.Fabricante,
	model.controladores.FabricanteControlador"%>

<jsp:include page="cabecera.jsp" flush="true">
	<jsp:param name="tituloDePagina" value="Listado de concesionarios" />
</jsp:include>

<div class="container">
	<h1>Listado de fabricantes</h1>
	<table class="table table-hover">
		<thead class="thead-dark">
			<tr>
				<th>CIF</th>
				<th>Nombre</th>
			</tr>
		</thead>
		<tbody>
			<%
				// Hasta la fila anterior ha llegado la primera fila de títulos de la tabla de profesores del centro educativo
			// En las siguietnes líneas se crea una fila "elemento <tr>" por cada fila de la tabla de BBDD "profesor"
			List<Fabricante> fabricantes = FabricanteControlador.getControlador().findAll();
			for (Fabricante fabricante : fabricantes) {
			%>
			<tr>
				<td><a
					href="fichaFabricante.jsp?idConcesionario=<%=fabricante.getId()%>"> <%=fabricante.getCif()%>
				</a></td>
				<td><%=fabricante.getNombre()%></td>
			</tr>
			<%
				}
			// Al finalizar de exponer la lista de profesores termino la tabla y cierro el fichero HTML
			%>
		</tbody>
	</table>
	<p />
	<input type="submit" class="btn btn-primary" name="nuevo" value="Nuevo"
		onclick="window.location='fichaFabricante.jsp?idFabricante=0'" />
</div>
<%@ include file="pie.jsp"%>