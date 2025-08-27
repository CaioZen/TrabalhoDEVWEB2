package controller;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.application.CadastrarAtorAP;
import model.domain.Ator;


@WebServlet("/CadastrarAtorSV")
public class CadastrarAtorSV extends HttpServlet {
    private CadastrarAtorAP cadastrarAtorAP = new CadastrarAtorAP();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nomeAtor = request.getParameter("nomeAtor");
        Ator ator = cadastrarAtorAP.cadastrar(nomeAtor);
        System.out.println("Nome do ator: "+ator.getNomeAtor());
    }
}