package model.application;

import model.domain.Ator;
import model.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CadastrarAtorAP {
    public Ator cadastrar(String nomeAtor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            Ator ator = new Ator(nomeAtor);
            session.save(ator);
            transaction.commit();
            return ator;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erro ao criar Ator: " + e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }
}
