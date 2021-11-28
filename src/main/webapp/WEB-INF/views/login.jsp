<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" href="/css/style.css"/>
<title>Inicio de sesión</title>
</head>
<body>
	<form action="login" method="post" name="formAlta">
	<fieldset>
		<legend>Inicio de sesión</legend>
		<div class="grid">
		
		<label for="username">Usuario </label>
		<input type="text" name="username" id="username" placeholder="Nombre de usuario"/>
		<label for="password">Contraseña </label>
		<input type="password" name="password" id="password"/>
		
		</div>
		
	<input class="send-button" type="submit" value="Iniciar sesión" />
		
	</fieldset>
	
	</form>
	<p>${mensajeLogin}</p>
	<p>Usuario1: <b>cliente1</b>   Contraseña: <b>pass1</b></p>
	<p>Usuario2: <b>cliente2</b>   Contraseña: <b>pass2</b></p>
</body>
</html>