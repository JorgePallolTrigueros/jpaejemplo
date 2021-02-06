<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:useBean id="micliente" class="modelo.beans.Cliente" scope="request"/>
<jsp:setProperty name="micliente" property="*"/>
<%
	if (request.getParameter("usuario") != null){
%>

	<jsp:forward page="Login?opcion=registrarBean"/>
<%}%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>


<body>
 

<form action="RegistroUsebean.jsp" method="post">
	<input type="text" maxlength="20" size="20" name="usuario" placeholder="Usuario">
   <br/><br/><input type="password" maxlength="20" size="20" name="password" placeholder="Contraseña">
   <br/><br/><input type="text" maxlength="20" size="20" name="nombre" placeholder="Nombre">
   <br/><br/><input type="text" maxlength="20" size="20" name="apellidos" placeholder="Apellidos">
   <br/><br/><input type="text" maxlength="20" size="20" name="direccion" placeholder="Direccion">
   
	<br/><br/> Sexo<br/>
  <input type="Radio" name="sexo" value="h" checked> Hombre<br/>
  <input type="Radio" name="sexo" value="m"> Mujer<br/>   
   <br/><br/><input type="submit"  value="enviar"><br/><br/><br/>
</form>
   <a href="index.jsp">Volver Inicio</a></br>
</body>
</html>