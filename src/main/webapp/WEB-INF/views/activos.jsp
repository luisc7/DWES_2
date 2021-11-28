<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" media="screen" href="/css/style.css"/>
<title>Eventos</title>
</head>
<body>

	<nav>
		<ul>
			<li><a href=/clientes/destacados>Eventos destacados</a></li>
			<li class="active"><a href=/clientes/activos>Eventos activos</a></li>
			<li><a href=/clientes/reservas>Reservas</a></li>
			<li><a>Registro</a></li>
			<li><a href="/clientes/cerrarSesion">Salir</a></li>
		</ul>
	</nav>
	<h1>Listado de Eventos Activos</h1>
	<p>Hola ${usuarioActivo.nombre}</p>
	<table>
		 
		 <tr>
			<th class="col1">Nombre</th>
			<th class="col2">Descripción</th>
			<th class="col3">Fecha inicio</th>
						
			<th class="col5">Opciones</th>
		</tr>
		<c:forEach var="eleEvento" items="${eventosActivos}">
			<tr>
				<td class="col1 filled-col">${eleEvento.nombre}</td>
				<td class="col3 filled-col">${eleEvento.descripcion}</td>
				<td class="col3 filled-col"><fmt:formatDate pattern = "dd-MM-yyyy" value = "${eleEvento.fechaInicio}"/></td>
				<td class="col5 filled-col"><a href="detalle/${eleEvento.idEvento}">Detalles</a></td>
			</tr>
		</c:forEach>
		
	</table>

</body>
</html>