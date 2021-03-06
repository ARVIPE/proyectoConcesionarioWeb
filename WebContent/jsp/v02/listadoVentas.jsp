<%@ page
	import="java.util.List,
	model.Venta,
	model.controladores.VentaControlador"%>

<jsp:include page="cabecera.jsp" flush="true">
	<jsp:param name="tituloDePagina" value="Listado de ventas" />
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
				// Hasta la fila anterior ha llegado la primera fila de t�tulos de la tabla de profesores del centro educativo
			// En las siguietnes l�neas se crea una fila "elemento <tr>" por cada fila de la tabla de BBDD "profesor"
			List<Venta> ventas = VentaControlador.getControlador().findDe5en5(5, offset);
			for (Venta venta : ventas) {
			%>
			<tr>
				<td><a
					href="fichaVentas.jsp?idVenta=<%=venta.getId()%>"> <%=venta.getCliente()%>
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
		onclick="window.location='fichaVentas.jsp?idVenta=0'" />
		
	<ul class="pagination justify-content-center">
	   <li class="page-item"><a class="page-link" href="?idPag=1">First</a></li>
	  
	  <%
//	  List<Venta> c = VentaControlador.getControlador().findAll();
	  int num = VentaControlador.getControlador().numRegistros();
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