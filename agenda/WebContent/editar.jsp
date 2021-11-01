<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="images/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar Contato</h1>
	<form name="frmContato" action="update">
	<table>
		<tr>
			<td><input type="text" name="idcontato" id="CaixaId" readonly value="<%out.print(request.getAttribute("idcontato"));%>"></td>
		</tr>
		<tr>
			<td><input type="text" name="nome" class="CaixaTexto" value="<%out.print(request.getAttribute("nome"));%>"></td>
		</tr>
		<tr>
			<td><input type="text" name="telefone" class="CaixaTelefone" value="<%out.print(request.getAttribute("telefone"));%>"></td>
		</tr>
		<tr>
			<td><input type="text" name="email" class="CaixaTexto" value="<%out.print(request.getAttribute("email"));%>"></td>
		</tr>
	</table>
	<input type="button" value="Salvar" class="BotaoPrincipal" onclick="validar()">
	</form> 
<script type="text/javascript" src="scripts/validador.js"></script>
</body>
</html>