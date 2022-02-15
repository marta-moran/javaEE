<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.Cliente, java.util.ArrayList"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Clientes</title>
</head>
<body>
	<%
ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) request.getAttribute("tablaClientes");
//int contador;

if (listaClientes.size() == 0) {
	out.println("No hay ning�n cliente con m�s de " + request.getParameter("puntos") + " puntos");
} else if (Integer.parseInt(request.getParameter("puntos")) < 0){
	out.println("Debe introducir un n�mero mayor");
} else {
//print the information about every category of the list
int contador = 0;
out.println("PUNTOS INTRODUCIDOS: " + request.getParameter("puntos") + "<br>");
for(Cliente cliente : listaClientes) {
 out.println("Nombre: " + cliente.getNombre());
 out.println("Tel�fono: " + cliente.getTelefono());
 out.println("Puntos: " + cliente.getPuntos() + "<br>");
}
out.println("<br>Tama�o de la lista " + listaClientes.size());
}
%>
</body>
</html>