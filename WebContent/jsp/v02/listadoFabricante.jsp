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
				// Hasta la fila anterior ha llegado la primera fila de títulos de la tabla de fabricantes
			// En las siguientes líneas se crea una fila "elemento <tr>" por cada fila de la tabla de BBDD "fabricante"
			List<Fabricante> fabricantes = FabricanteControlador.getControlador().findAll();
			for (Fabricante fabricante : fabricantes) {
			%>
			<tr>
				<td><a
					href="fichaFabricante.jsp?idFabricante=<%=fabricante.getId()%>"> <%=fabricante.getCif()%>
				</a></td>
				<td><%=fabricante.getNombre()%></td>
			</tr>
			<%
				}
			// Al finalizar de exponer la lista de fabricantes termino la tabla y cierro el fichero HTML
			%>
		</tbody>
	</table>
	<p />
	<input type="submit" class="btn btn-primary" name="nuevo" value="Nuevo"
		onclick="window.location='fichaFabricante.jsp?idFabricante=0'" />
</div>
<%@ include file="pie.jsp"%>