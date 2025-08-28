<%@ page import="model.domain.Ator" %>
<%@ page import="java.util.List" %>
<%@ page import="model.application.CarregarAtoresAP" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // Carregar atores se não estiverem na sessão
    List<Ator> atores = (List<Ator>) session.getAttribute("atores");
    if (atores == null) {
        CarregarAtoresAP carregarAtoresAP = new CarregarAtoresAP();
        atores = carregarAtoresAP.listarAtores();
        session.setAttribute("atores", atores);
    }
    
    // Verificar se está editando um ator
    Ator atorEditando = (Ator) request.getAttribute("atorEditando");
    boolean isEdicao = atorEditando != null;
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Frame por Frame - <%= isEdicao ? "Editar" : "Cadastro de" %> Ator</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
</head>

<body>
<div class="container">
    <h1><%= isEdicao ? "Editar" : "Cadastrar" %> Ator</h1>

    <form id="formularioAtor" action="${pageContext.request.contextPath}/<%= isEdicao ? "EditarAtorSV" : "CadastrarAtorSV" %>" method="POST">
        <% if (isEdicao) { %>
            <input type="hidden" name="idAtor" value="<%= atorEditando.getIdAtor() %>">
        <% } %>
        
        <div class="form-group">
            <label for="nomeAtor">Nome do Ator:</label>
            <input type="text" id="nomeAtor" name="nomeAtor" 
                   value="<%= isEdicao ? atorEditando.getNomeAtor() : "" %>" 
                   required placeholder="Digite o nome do ator">
        </div>

        <div class="form-actions">
            <button type="submit" class="btn-submit"><%= isEdicao ? "Salvar Alterações" : "Cadastrar" %></button>
            <% if (isEdicao) { %>
                <a href="${pageContext.request.contextPath}/index.jsp" class="btn-cancel">Cancelar</a>
            <% } %>
        </div>
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
            if(atores != null && !atores.isEmpty()) {
                for(Ator ator : atores) {
        %>
        <tr>
            <td><%= ator.getIdAtor() %></td>
            <td><%= ator.getNomeAtor() %></td>
            <td>
                <a href="${pageContext.request.contextPath}/EditarAtorSV?idAtor=<%= ator.getIdAtor() %>" class="btn-edit">Editar</a>
                <form action="${pageContext.request.contextPath}/ExcluirAtorSV" method="POST" style="display: inline;">
                    <input type="hidden" name="idAtor" value="<%= ator.getIdAtor() %>">
                    <button type="submit" class="btn-delete" onclick="return confirm('Tem certeza que deseja excluir este ator?')">Excluir</button>
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="3" style="text-align: center; color: #666; font-style: italic;">Nenhum ator cadastrado ainda.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>