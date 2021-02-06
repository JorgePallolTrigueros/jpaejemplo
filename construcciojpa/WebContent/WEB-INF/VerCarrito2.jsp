<%@page import="modelo.beans.Cliente"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="modelo.beans.LineasPedido"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%List<LineasPedido> lista = (List<LineasPedido>)session.getAttribute("listaLibros"); 
	double producto = 0;
	double acumuladorTotal=0; %>
	<h2>Carrito de <%=((Cliente)session.getAttribute("cliente")).getApellidos() %></h2>
	
	<table border='1'>
	<tr>
	<th>Libro</th><th>Cantidad</th><th>Precio Venta</th><th>Importe</th><th>Eliminar Libro</th>
	</tr>
	<%for (LineasPedido ele: lista){%>
	 	<% producto = ele.getPrecioVenta().multiply(BigDecimal.valueOf(ele.getCantidad())).doubleValue(); %>
		<tr>
			<td><%=ele.getLibro().getTitulo() %></td>
			<td><%=ele.getCantidad() %></td>
			<td><%=ele.getPrecioVenta() %></td>
			<td><%= producto %></td>
			<td><a href="GestionCarrito?opcion=eliminar&isbn=<%=lista.indexOf(ele) %>">Eliminar</a></td>
			
		</tr>
		<%acumuladorTotal+=producto; %>
	<%} %>
	</table>
	<h3>Total : <%=acumuladorTotal %></h3>
	
	<a href="GestionCarrito?opcion=comprar">Comprar</a></br>
	<a href="GestionCarrito?opcion=vaciar">Vaciar Carrito</a></br>
	<a href="MenuClientes.jsp">Volver menu Clientes</a></br>
	<a href="GestionCarrito?opcion=dexconn">Cerrar Sesion</a></br>
	
	
</body>
</html>