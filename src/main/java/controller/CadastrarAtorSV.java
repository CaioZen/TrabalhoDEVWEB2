package controller;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.domain.Ator;
import model.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@WebServlet("/CadastrarAtorSV")
public class CadastrarAtorSV extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nomeAtor = request.getParameter("nomeAtor");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            Ator ator = new Ator(nomeAtor);
            session.save(ator);
            transaction.commit();
            System.out.println("Ator cadastrado com sucesso!");
        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
                System.out.println("Erro ao cadastrar Ator!");
            }
        } finally{
            session.close();
            response.sendRedirect("index.jsp");
        }

    }
}