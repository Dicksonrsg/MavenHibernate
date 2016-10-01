
package br.senac.rn.dao;

import br.senac.rn.model.Categoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CategoriaDAO {

        private EntityManager em;
    
    public CategoriaDAO(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexaoDB");
        em = emf.createEntityManager();
    }
    
    public boolean insert(Categoria categoria){
        try{
            em.getTransaction().begin();
            em.persist(categoria);
            em.getTransaction().commit();
            return true;
        }catch(Exception error){
            System.out.println("ERRO: " + error.toString());
        }
        return false;
    }
    
    public boolean delete(Categoria categoria){
        try{
            em.getTransaction().begin();
            em.remove(categoria);
            em.getTransaction().commit();
            return true;
        }catch(Exception error){
            System.out.println("ERRO: " + error.toString());
        }
        return false;
    }    
    
    public boolean update(Categoria categoria){
        try{
            em.getTransaction().begin();
            em.merge(categoria);
            em.getTransaction().commit();
            return true;
        }catch(Exception error){
            System.out.println("ERRO: " + error.toString());
        }
        return false;
    }
    
    public List<Categoria> selectAll(){
        try{
            Query query = em.createQuery("SELECT c FROM Categoria c");
            return query.getResultList();
        }catch(Exception error){
            System.out.println("ERRO: " + error.toString());
        }
        return null;
    }
    
    public Categoria selectById(int id){
        return em.find(Categoria.class, id);
    } 
    
    public List<Categoria> selectByFilter(String filter){
        Query query = em.createQuery("SELECT c FROM Categoria c WHERE c.nome LIKE :filtro");
        query.setParameter("filtro", "%" + filter + "%");
        return query.getResultList();
    }    
}
