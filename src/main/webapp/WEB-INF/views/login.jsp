<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" href="/css/style.css"/>
<title>Inicio de sesi�n</title>
</head>
<body>
	<form action="login" method="post" name="formAlta">
	<fieldset>
		<legend>Inicio de sesi�n</legend>
		<div class="grid">
		
		<label for="username">Usuario </label>
		<input type="text" name="username" id="username" placeholder="Nombre de usuario"/>
		<label for="password">Contrase�a </label>
		<input type="password" name="password" id="password"/>
		
		</div>
		
	<input class="send-button" type="submit" value="Iniciar sesi�n" />
		
	</fieldset>
	
	</form>
	<p>${mensajeLogin}</p>
	<p>Usuario1: <b>cliente1</b>   Contrase�a: <b>pass1</b></p>
	<p>Usuario2: <b>cliente2</b>   Contrase�a: <b>pass2</b></p>
</body>
</html>