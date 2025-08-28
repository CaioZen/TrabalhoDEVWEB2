package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.application.CarregarAtoresAP;
import model.application.ExcluirAtorAP;
import model.domain.Ator;

@WebServlet("/ExcluirAtorSV")
public class ExcluirAtorSV extends HttpServlet {
    private ExcluirAtorAP excluirAtorAP = new ExcluirAtorAP();
    private CarregarAtoresAP carregarAtoresAP = new CarregarAtoresAP();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Pegando o ID do ator a ser excluído
        String idAtorParam = request.getParameter("idAtor");
        
        if (idAtorParam != null && !idAtorParam.isEmpty()) {
            try {
                int idAtor = Integer.parseInt(idAtorParam);
                boolean excluido = excluirAtorAP.excluir(idAtor);
                
                if (excluido) {
                    // Atualizando a lista de atores após exclusão
                    List<Ator> atores = carregarAtoresAP.listarAtores();
                    HttpSession session = request.getSession();
                    session.setAttribute("atores", atores);
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro ao converter ID do ator: " + e.getMessage());
            }
        }
        
        response.sendRedirect("index.jsp");
    }
}
