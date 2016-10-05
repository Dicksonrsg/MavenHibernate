
package br.senac.rn.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class GenericDAO<T> {
    
    private EntityManager em;

    private EntityManager getEm() {
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
    
}
