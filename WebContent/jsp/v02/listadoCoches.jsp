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
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<a class="navbar-brand" href="#">Navbar</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item active"><a class="nav-link" href="listadoFabricante.jsp">Fabricante
								<span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item active"><a class="nav-link" href="listadoConcesionarios.jsp">Concesionario
								<span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item active"><a class="nav-link" href="listadoCoches.jsp">Coches
								<span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item active"><a class="nav-link" href="listadoCliente.jsp">Clientes
								<span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item active"><a class="nav-link" href="listadoVentas.jsp">Ventas
								<span class="sr-only">(current)</span>
						</a></li>
					</ul>
				</div>
			</nav>
			<%
				// Hasta la fila anterior ha llegado la primera fila de títulos de la tabla de profesores del centro educativo
			// En las siguietnes líneas se crea una fila "elemento <tr>" por cada fila de la tabla de BBDD "profesor"
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
		onclick="window.location='fichaCoches.jsp?idCoche=0'" />
</div>
<%@ include file="pie.jsp"%>