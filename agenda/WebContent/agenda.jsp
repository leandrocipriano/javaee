<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
	@ SuppressWarnings ("unchecked")
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos"); 
	/* for(int i = 0; i < lista.size(); i++){
		out.println(lista.get(i).getIdcontato());
		out.println(lista.get(i).getNome());
		out.println(lista.get(i).getTelefone());
		out.println(lista.get(i).getEmail());
	} */
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="images/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de Contatos</h1>
	<a href="novo.html" class="BotaoPrincipal">Novo Contato</a>
	<a href="report" class="BotaoSecundario">Relatório</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Telefone</th>
				<th>E-mail</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<% for (int i = 0; i < lista.size(); i++) { %>
				<tr>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getTelefone()%></td>
					<td><%=lista.get(i).getEmail()%></td>
					<td><a href="select?idcontato=<%=lista.get(i).getIdContato()%>" class="BotaoPrincipal">Editar</a> 
					<a href="javascript: confirmar(<%=lista.get(i).getIdContato()%>)" class="BotaoSecundario">Excluir</a></td>
				</tr>
			<% } %>
		</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>