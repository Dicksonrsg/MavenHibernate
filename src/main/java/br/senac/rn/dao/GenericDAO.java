
package br.senac.rn.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.JOptionPane;

public abstract class GenericDAO<T extends PersistDB> {
    
    protected EntityManager em;

    protected EntityManager getEm() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexaoDB");
        return emf.createEntityManager();
    }
    
    public boolean insert(T t){
        try{
            em = getEm();
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            return true;
        }catch(Exception error){
            System.out.println("Erro: " + error.toString());
        }
        return false;
    }
 
    public boolean delete(T t){
        try{
            em = getEm();
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
            return true;
        }catch(Exception error){
            System.out.println("Erro: " + error.toString());
        }
        return false;
    } 
    
    public boolean update(T t){
        try{
            em = getEm();
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
            return true;
        }catch(Exception error){
            System.out.println("Erro: " + error.toString());
        }
        return false;
    }    
    
    public List<T> selectAll(){
        try{
            em = getEm();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(getClassType());
            TypedQuery<T> typedQuery = em.createQuery(query.select(query.from(getClassType())));
            return typedQuery.getResultList();
        }catch(Exception error){
            JOptionPane.showMessageDialog(null, error.toString());
        }
        return null;
    }
    
    public T selectById(int id){
        try{
        em = getEm();
        return em.find(getClassType(), id);
        }catch(Exception error){
            System.out.println("Erro: " + error.toString());
        }
        return null;
    }
    
    public abstract Class<T> getClassType();
}
