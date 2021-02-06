<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	 
	<c:set var="producto" value = "${0.0}"/>
	<c:set var ="acumuladorTotal" value = "${0.0}"/>
	
	<h2>Carrito de ${sessionScope.cliente.nombre}</h2>
	
	<table border='1'>
	<tr>
	<th>Libro</th><th>Cantidad</th><th>Precio Venta</th><th>Importe</th><th>Eliminar Libro</th>
	</tr>
	<c:forEach items="${sessionScope.carrito }" var="ele">
		<c:set var="producto" value="${ele.precioVenta * ele.cantidad }"/>
	 	
		<tr>
			<td>${ele.libro.titulo }</td>
			<td>${ele.cantidad }</td>
			<td>${ele.precioVenta }</td>
			<td>${producto}</td>
			<td><a href="GestionCarrito?opcion=eliminar&isbn=${ele.libro.isbn }">Eliminar</a></td>
			
		</tr>
		<c:set var="acumuladorTotal" value="${acumuladorTotal+producto }"/>
		
	</c:forEach>
	</table>
	<h3>Total : ${acumuladorTotal}</h3>
	
	<a href="GestionCarrito?opcion=comprar">Comprar</a></br>
	<a href="GestionCarrito?opcion=comprar2">Comprar2</a></br>
	<a href="GestionCarrito?opcion=vaciar">Vaciar Carrito</a></br>
	<a href="MenuClientes.jsp">Volver menu Clientes</a></br>
	<a href="GestionLibros?opcion=cerrarSesion">Cerrar Sesion</a></br>
	
	
</body>
</html>