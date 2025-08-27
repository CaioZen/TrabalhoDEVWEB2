package controller;

import java.io.*;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.application.CadastrarAtorAP;
import model.application.CarregarAtoresAP;
import model.domain.Ator;


@WebServlet("/CadastrarAtorSV")
public class CadastrarAtorSV extends HttpServlet {
    private CadastrarAtorAP cadastrarAtorAP = new CadastrarAtorAP();
    private CarregarAtoresAP carregarAtoresAP = new CarregarAtoresAP();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Pegando o nome do ator do formulário e mandando para a aplicação
        String nomeAtor = request.getParameter("nomeAtor");
        Ator ator = cadastrarAtorAP.cadastrar(nomeAtor);
        System.out.println("Nome do ator: "+ator.getNomeAtor());
        List<Ator> atores = carregarAtoresAP.listarAtores();
        HttpSession session = request.getSession();
        session.setAttribute("atores", atores);
        response.sendRedirect("index.jsp");
    }
}