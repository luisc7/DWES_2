<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" href="css/style.css"/>
<title>Alta evento</title>
</head>
<body>
	<form action="eventos?opcion=alta" method="post" name="formAlta">
	<fieldset>
		<legend>Cumplimenta los datos del nuevo evento:</legend>
		<div class="grid">
		<label for="tipo">Tipo de evento</label>
            <select name="tipo">
                <option value="1">Boda</option>
                <option value="2">Bautizo</option>
                <option value="3">Comuni�n</option>
                <option value="4">Despedida</option>
                <option value="5">Cumplea�os</option>
                <option value="6">Concierto</option>
                <option value="7">Teatro</option>
            </select>
		<label for="nombre">Nombre </label>
		<input type="text" name="nombre" id="nombre" />
		<label for="descripcion">Descripci�n </label>
		<input type="text" name="descripcion" id="descripcion" placeholder="Detalles del evento" />
		<label for="fecha">Fecha (yyyy-mm-dd) </label>
		<input type="date" name="fecha" id="fecha" />
		<label for="duracion">Duracion </label>
		<input type="number" name="duracion" id="duracion" />
		<label for="direccion">Direccion </label>
		<input type="text" name="direccion" id="direccion" />
		
		<input type="hidden" name="estado" value="Activo" />
		
		<label for="destacado">Destacado </label>
            <select name="destacado">
                <option value="" selected>No</option>
                <option value="s">S�</option>
            </select>
        
		
		<label for="max">Aforo m�ximo </label>
		<input type="number" name="max" id="max" step="1"/>
		<label for="min">M�nimo de asistentes </label>
		<input type="number" name="min" id="min" step="1" />
		<label for="precio">Precio </label>
		<input type="text" name="precio" id="precio" /></div>
		
	</fieldset>
	
	<input class="send-button" type="submit" value="A�adir evento" />
	
	</form>
</body>
</html>