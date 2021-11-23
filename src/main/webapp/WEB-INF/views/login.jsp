<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" href="css/style.css"/>
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
		
	</fieldset>
	
	<input class="send-button" type="submit" value="Iniciar sesión" />
	
	</form>
	<p>${mensajeLogin}</p>
</body>
</html>