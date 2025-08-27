<%@ page import="model.domain.Ator" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Frame por Frame - Cadastro de ator</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
</head>

<body>
<div class="container">
    <h1>Cadastro de Ator</h1>

    <form id="formularioAtor" action="${pageContext.request.contextPath}/CadastrarAtorSV" method="POST">
        <div class="form-group">
            <label for="nomeAtor">Nome do Ator:</label>
            <input type="text" id="nomeAtor" name="nomeAtor" required placeholder="Digite o nome do ator">
        </div>

        <button type="submit" class="btn-submit">Enviar</button>
    </form>
</div>
<div class="container">
    <h2>Atores Cadastrados</h2>

    <table class="actors-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nome do Ator</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Ator> atores = (List<Ator>) session.getAttribute("atores");
            if(atores != null) {
                for(Ator ator : atores) {
        %>
        <tr>
            <td><%= ator.getIdAtor() %></td>
            <td><%= ator.getNomeAtor() %></td>
            <td>
                <button class="btn-edit" onclick="editarAtor(<%= ator.getIdAtor() %>)">Editar</button>
                <button class="btn-delete" onclick="excluirAtor(<%= ator.getIdAtor() %>)">Excluir</button>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>

    <div class="no-actors" style="display: none;">
        <p>Nenhum ator cadastrado ainda.</p>
    </div>
</div>
</body>
</html>