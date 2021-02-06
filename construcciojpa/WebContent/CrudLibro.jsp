<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alta, modificacion consulta de libros administrador</title>
</head>
<body>
	<form action="GestionCRUD" method="post">
	<input type="text" maxlength="20" size="20" name="isbn" value="${sessionScope.libro.isbn}" placeholder="isbn">
   <br/><br/><input type="text" name="titulo" value="${sessionScope.libro.titulo}" placeholder="Titulo">
   <br/><br/><input type="text" name="autor" value="${sessionScope.libro.autor}" placeholder="Autor">
   <br/><br/><input type="text" name="paginas" value="${sessionScope.libro.paginas}" placeholder="Paginas">
   <br/><br/><input type="text" name="precio" value="${sessionScope.libro.precio}" placeholder="Precio">
   <br/><br/><input type="text" name="idTema" value="${sessionScope.libro.tema.idTema}" placeholder="Tema">
   
	
   <br/><br/><input type="submit"  name="crud" value="Alta">
    <input type="submit"  name="crud" value="Modificar">
    <input type="submit"  name="crud" value="Eliminar">
    <input type="submit"  name="crud" value="Consultar">
   
</form>

	<p><c:out  value="${requestScope.mensajeCRUD }"/></p>
   <p><a href="index.jsp">Volver Inicio</a></p>
</body>
</html>