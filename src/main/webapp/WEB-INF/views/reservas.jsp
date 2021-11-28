<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" media="screen" href="/css/style.css"/>
<title>Reservas</title>
</head>
<body>

	<nav>
		<ul>
			<li><a href=/clientes/destacados>Eventos destacados</a></li>
			<li><a href=/clientes/activos>Eventos activos</a></li>
			<li class="active"><a href=/clientes/reservas>Reservas</a></li>
			<li><a>Registro</a></li>
			<li><a href="/clientes/cerrarSesion">Salir</a></li>
		</ul>
	</nav>
	<h1>Listado de Reservas</h1>	
	<p>Hola ${usuarioActivo.nombre}</p>
	<table>		 
		 <tr>
			<th class="col1">id Reserva</th>
			<th class="col2">id Evento</th>
			<th class="col3">Precio</th>
						
			<th class="col5">Cantidad</th>
		</tr>
		<c:forEach var="eleReservas" items="${reservasUsuario}">
			<tr>
				<td class="col1 filled-col">${eleReservas.idReserva}</td>
				<td class="col3 filled-col">${eleReservas.idEvento}</td>
				<td class="col3 filled-col">${eleReservas.precioVenta}</td>
				<td class="col5 filled-col">${eleReservas.cantidad}</td>
			</tr>
		</c:forEach>
		
	</table>

</body>
</html>