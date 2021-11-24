<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" media="screen" href="style.css"/>
<title>Eventos</title>
</head>
<body>
	<p>${mensajeLogin}</p>
	<nav>
		<ul>
			<li class="active"><a href=/clientes/destacados>Eventos destacados</a></li>
			<li><a href=/clientes/activos>Eventos activos</a></li>
			<li><a href=/clientes/reservas>Reservas</a></li>
			<li><a>Login</a></li>
			<li><a>Registro</a></li>
			<li><a href="/clientes/cerrarSesion">Salir</a></li>
		</ul>
	</nav>
	<h1>Listado de Eventos Destacados</h1>
	<div class="boton"><a href=form.jsp>Nuevo evento</a></div>
	<p>Hola ${usuarioActivo.nombre}</p>
	<table>
		 
		 <tr>
			<th class="col1">Nombre</th>
			<th class="col2">Descripción</th>
			<th class="col3">Fecha inicio</th>
						
			<th class="col5">Opciones</th>
		</tr>
		<c:forEach var="eleEvento" items="${eventosDestacados}">
				<tr>
					<td class="col1 filled-col">${eleEvento.nombre}</td>
					<td class="col3 filled-col">${eleEvento.descripcion}</td>
					<td class="col3 filled-col">${eleEvento.fechaInicio}</td>
					<td class="col5 filled-col"><a href="detalle/${eleEvento.idEvento}">Detalles</a></td>
				</tr>
		</c:forEach>
		
	</table>

</body>
</html>