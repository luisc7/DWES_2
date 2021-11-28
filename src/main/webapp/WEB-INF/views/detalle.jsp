<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" media="screen" href="/css/style.css"/>
<title>Detalles del evento</title>
</head>
<body>
	
	<p>Hola ${usuarioActivo.nombre}</p>
	<h1>Detalles del evento</h1>
	<p class="boton"><a href="/clientes">Volver a lista de eventos destacados</a></p>
	<table class="modif">
		<tr>
			<th>Identificador</th>
			<th>Tipo de evento</th>
			<th>Nombre</th>
			<th>Descripción</th>
			<th>Inicio</th>
			<th>Duración (h)</th>
			<th>Dirección</th>
			<th>Estado</th>
			<th>¿Destacado?</th>
			<th>Máximo asistentes</th>
			<th>Mínimo asistentes</th>
			<th>Precio (Euros)</th>
		</tr>

		<tr>
			<td>${evento.idEvento}</td>
			<td>${evento.idTipo.nombre }</td>
			<td>${evento.nombre }</td>
			<td>${evento.descripcion }</td>
			<td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${evento.fechaInicio}"/></td>
			<td>${evento.duracion }</td>
			<td>${evento.direccion }</td>
			<td>${evento.estado }</td>
			<td>${evento.destacado }</td>
			<td>${evento.aforoMaximo }</td>
			<td>${evento.minimoAsistencia }</td>
			<td>${evento.precio }</td>	
		</tr>	
	</table>
	<img src="../../img/${evento.idTipo.idTipo}.jpg"/>
	<p>Cantidad disponible: ${cantidadDisponible}</p>
	<form action="/clientes/reservar/${evento.idEvento}">
		<label for="cantidad">Elige la cantidad de entradas </label>
		<input type="number" id="cantidad" name="cantidad" min="1" max="10" step="1"/><span class="aviso-rojo"> * Máximo 10 entradas</span>
		<input type="submit" value="Reservar"/>
	</form>
	<p>${mensajeReserva}</p>
	
</body>
</html>