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
	
	
	<form action="GestionLibros?opcion=libros" method="post">
		<p>Escoja un tema y pulse enviar</p>
		
		<c:forEach items="${requestScope.temas}" var="tema">
		
			
			<input type="Radio" name="idTema" value="${tema.idTema }" checked> ${tema.descripcion}<br/>
		
		 
	</c:forEach>
		<input type="submit" name="boton" value="Enviar"><br/><br/>
	</form>
	<a href="MenuClientes.jsp">Volver menu Clientes</a></br>
</body>
</html>