package model.application;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.domain.Ator;
import model.domain.HibernateUtil;

public class EditarAtorAP {
    
    public Ator buscarPorId(int idAtor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.beginTransaction();
            Ator ator = session.get(Ator.class, idAtor);
            session.getTransaction().commit();
            return ator;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("Erro ao buscar Ator: " + e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }
    
    public boolean editar(int idAtor, String novoNome) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Ator ator = session.get(Ator.class, idAtor);
            
            if (ator != null) {
                ator.setNomeAtor(novoNome);
                session.update(ator);
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
            System.out.println("Erro ao editar Ator: " + e.getMessage());
            return false;
        } finally {
            session.close();
        }
    }
}
