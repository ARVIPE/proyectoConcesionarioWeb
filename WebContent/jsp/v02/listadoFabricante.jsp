<%@ page
	import="java.util.List,
	model.Fabricante,
	model.controladores.FabricanteControlador"%>

<jsp:include page="cabecera.jsp" flush="true">
	<jsp:param name="tituloDePagina" value="Listado de concesionarios" />
</jsp:include>

	<%!
			public int getOffset(String param){
				int offset = Integer.parseInt(param);
				if(offset > 1){
					return 5 * offset;
				}
				else{
					return 0;
				}
				
			}
		%>
		<%! private int offset, paginationIndex; %>
		<% offset = getOffset(request.getParameter("idPag"));
			paginationIndex = Integer.parseInt(request.getParameter("idPag"));
		%>

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
				// Hasta la fila anterior ha llegado la primera fila de t�tulos de la tabla de fabricantes
			// En las siguientes l�neas se crea una fila "elemento <tr>" por cada fila de la tabla de BBDD "fabricante"
			List<Fabricante> fabricantes = FabricanteControlador.getControlador().findDe5en5(5, offset);
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
		
			<ul class="pagination justify-content-center">
	   <li class="page-item"><a class="page-link" href="?idPag=1">First</a></li>
	  
	  <%
//	  List<Venta> c = VentaControlador.getControlador().findAll();
	  int num = FabricanteControlador.getControlador().numRegistros();
	   double size = Math.ceil(num / 5);
	  
	   if(paginationIndex > 1){
	   
		  %> 
		     <li class="page-item"><a class="page-link" href="?idPag=<%= paginationIndex-1 %>" ><%= paginationIndex-1 %></a></li>
			 
		  <%
		  }
		  %>
		  <li class="page-item active"><a class="page-link" href="?idPag=<%= paginationIndex %>" ><%= paginationIndex %></a></li>
		<%
		if (paginationIndex < size){
		%>
		<li class="page-item"><a class="page-link" href="?idPag=<%= paginationIndex+1 %>" ><%= paginationIndex+1 %></a></li>  
		<%
		  }
		  %>
		     
		  <li class="page-item"><a class="page-link" href="?idPag=<%=Math.round(size)%>">Last</a></li>
	 </ul> 
</div>
<%@ include file="pie.jsp"%>