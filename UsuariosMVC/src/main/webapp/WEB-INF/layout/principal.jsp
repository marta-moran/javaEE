<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CRUD DE USUARIOS JAVAEE</title>
<link href="web/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="web/js/funciones.js"></script>
</head>
<body>
<div id="container" >
<div id="header">
<h1>GESTIÓN DE USUARIOS JAVAEE</h1>
</div>
<div id="content">
<form>
${contenido} 
<button name="orden" value="Nuevo"> Nuevo </button>
<button name="orden" value="Terminar"> Terminar </button>
<button name="orden" value="Saldo"> Incrementar saldo </button>
<button name="orden" value="Bloqueo"> Bloqueo </button>
</form>
</div>
</div>
</body>
    