package controller;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.domain.Ator;

@WebServlet("/CadastrarAtorSV")
public class CadastrarAtorSV extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nomeAtor = request.getParameter("nomeAtor");
        Ator ator = new Ator(nomeAtor);
        System.out.println("Ator recebido: " + ator.getNomeAtor());
    }
}