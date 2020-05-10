<%@ page
	import="java.util.List,
	model.Venta,
	model.controladores.VentaControlador"%>

<jsp:include page="cabecera.jsp" flush="true">
	<jsp:param name="tituloDePagina" value="Listado de concesionarios" />
</jsp:include>

<div class="container">
	<h1>Listado de ventas</h1>
	<table class="table table-hover">
		<thead class="thead-dark">
			<tr>
				<th>idCliente</th>
				<th>idConcesionario</th>
				<th>idCoche</th>
				<th>fecha</th>
				<th>precioVenta</th>
			</tr>
		</thead>
		<tbody>
			<%
				// Hasta la fila anterior ha llegado la primera fila de títulos de la tabla de profesores del centro educativo
			// En las siguietnes líneas se crea una fila "elemento <tr>" por cada fila de la tabla de BBDD "profesor"
			List<Venta> ventas = VentaControlador.getControlador().findAll();
			for (Venta venta : ventas) {
			%>
			<tr>
				<td><a
					href="fichaVenta.jsp?idVenta=<%=venta.getId()%>"> <%=venta.getCliente()%>
				</a></td>
				<td><%=venta.getConcesionario()%></td>
				<td><%=venta.getCoche()%></td>
				<td><%=venta.getFecha()%></td>
				<td><%=venta.getPrecioVenta()%></td>
			</tr>
			<%
				}
			// Al finalizar de exponer la lista de profesores termino la tabla y cierro el fichero HTML
			%>
		</tbody>
	</table>
	<p />
	<input type="submit" class="btn btn-primary" name="nuevo" value="Nuevo"
		onclick="window.location='fichaVenta.jsp?idVenta=0'" />
</div>
<%@ include file="pie.jsp"%>