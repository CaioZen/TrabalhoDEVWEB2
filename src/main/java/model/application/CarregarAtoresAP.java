package model.application;

import model.domain.Ator;
import model.domain.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class CarregarAtoresAP {
    private List<Ator> listaAtores = null;
    private Session sessao = null;
    public List<Ator> listarAtores(){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            CriteriaQuery<Ator> criteria = sessao.getCriteriaBuilder().createQuery(Ator.class);
            criteria.from(Ator.class);
            listaAtores = sessao.createQuery(criteria).getResultList();
            sessao.getTransaction().commit();
            sessao.close();
        } catch (HibernateException e) {
            if (sessao != null) {
                sessao.getTransaction().rollback();
                sessao.close();
            }
            throw new HibernateException("Erro ao carregar atores: "+e);
        }
        return listaAtores;
    }
}
