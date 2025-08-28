package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.application.CarregarAtoresAP;
import model.application.EditarAtorAP;
import model.domain.Ator;

@WebServlet("/EditarAtorSV")
public class EditarAtorSV extends HttpServlet {
    private EditarAtorAP editarAtorAP = new EditarAtorAP();
    private CarregarAtoresAP carregarAtoresAP = new CarregarAtoresAP();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Buscar o ator para edição
        String idAtorParam = request.getParameter("idAtor");
        
        if (idAtorParam != null && !idAtorParam.isEmpty()) {
            try {
                int idAtor = Integer.parseInt(idAtorParam);
                Ator ator = editarAtorAP.buscarPorId(idAtor);
                
                if (ator != null) {
                    // Carregar a lista de atores também
                    List<Ator> atores = carregarAtoresAP.listarAtores();
                    HttpSession session = request.getSession();
                    session.setAttribute("atores", atores);
                    
                    // Passar o ator para edição
                    request.setAttribute("atorEditando", ator);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect("index.jsp");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro ao converter ID do ator: " + e.getMessage());
                response.sendRedirect("index.jsp");
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Salvar as alterações
        String idAtorParam = request.getParameter("idAtor");
        String novoNome = request.getParameter("nomeAtor");
        
        if (idAtorParam != null && !idAtorParam.isEmpty() && novoNome != null && !novoNome.trim().isEmpty()) {
            try {
                int idAtor = Integer.parseInt(idAtorParam);
                boolean editado = editarAtorAP.editar(idAtor, novoNome.trim());
                
                if (editado) {
                    // Atualizando a lista de atores após edição
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
