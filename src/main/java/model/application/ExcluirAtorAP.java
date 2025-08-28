package model.application;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.domain.Ator;
import model.domain.HibernateUtil;

public class ExcluirAtorAP {
    public boolean excluir(int idAtor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Ator ator = session.get(Ator.class, idAtor);
            
            if (ator != null) {
                session.delete(ator);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erro ao excluir Ator: " + e.getMessage());
            return false;
        } finally {
            session.close();
        }
    }
}
