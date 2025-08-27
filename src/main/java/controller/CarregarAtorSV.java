package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.application.CarregarAtoresAP;
import model.domain.Ator;

import java.io.IOException;
import java.util.List;

@WebServlet("/CarregarAtorSV")
public class CarregarAtorSV extends HttpServlet {
    private CarregarAtoresAP carregarAtoresAP = new CarregarAtoresAP();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Carregando a lista de atores e mandando para a página index.jsp
        List<Ator> atores = carregarAtoresAP.listarAtores();
        HttpSession session = request.getSession();
        session.setAttribute("atores", atores);
        response.sendRedirect("index.jsp");
    }
}
