<%@ page
	import="java.util.List,
	model.Coche,
	model.Fabricante,
	model.controladores.CocheControlador"%>

<jsp:include page="cabecera.jsp" flush="true">
	<jsp:param name="tituloDePagina" value="Listado de coches" />
</jsp:include>

<div class="container">
	<h1>Listado de coches</h1>
	<table class="table table-hover">
		<thead class="thead-dark">
			<tr>
				<th>Bastidor</th>
				<th>Fabricante</th>
				<th>Color</th>
				<th>Modelo</th>
			</tr>
		</thead>
		<tbody>
			<%
				// Hasta la fila anterior ha llegado la primera fila de t�tulos de la tabla de profesores del centro educativo
			// En las siguietnes l�neas se crea una fila "elemento <tr>" por cada fila de la tabla de BBDD "profesor"
			List<Coche> coches = CocheControlador.getControlador().findAll();
			for (Coche coche : coches) {
			%>
			<tr>
				<td><a
					href="fichaCoches.jsp?idCoche=<%=coche.getId()%>"> <%=coche.getBastidor()%>
				</a></td>
				<td><%=coche.getFabricante()%></td>
				<td><%=coche.getColor()%></td>
				<td><%=coche.getModelo()%></td>
			</tr>
			<%
				}
			// Al finalizar de exponer la lista de profesores termino la tabla y cierro el fichero HTML
			%>
		</tbody>
	</table>
	<p />
	<input type="submit" class="btn btn-primary" name="nuevo" value="Nuevo"
		onclick="window.location='fichaCoche.jsp?idCoche=0'" />
</div>
<%@ include file="pie.jsp"%>