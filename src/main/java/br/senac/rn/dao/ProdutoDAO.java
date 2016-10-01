
package br.senac.rn.dao;

import br.senac.rn.model.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ProdutoDAO {

    private EntityManager em;
    
    public ProdutoDAO(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexaoDB");
        em = emf.createEntityManager();
    }
    
    public boolean insert(Produto produto){
        try{
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
            return true;
        }catch(Exception error){
            System.out.println("ERRO: " + error.toString());
        }
        return false;
    }
    
    public boolean delete(Produto produto){
        try{
            em.getTransaction().begin();
            em.remove(produto);
            em.getTransaction().commit();
            return true;
        }catch(Exception error){
            System.out.println("ERRO: " + error.toString());
        }
        return false;
    }    
    
    public boolean update(Produto produto){
        try{
            em.getTransaction().begin();
            em.merge(produto);
            em.getTransaction().commit();
            return true;
        }catch(Exception error){
            System.out.println("ERRO: " + error.toString());
        }
        return false;
    }
    
    public List<Produto> selectAll(){
        try{
            Query query = em.createQuery("SELECT p FROM Produto p");
            return query.getResultList();
        }catch(Exception error){
            System.out.println("ERRO: " + error.toString());
        }
        return null;
    }
    
    public Produto selectById(int id){
        return em.find(Produto.class, id);
    } 
    
    public List<Produto> selectByFilter(String filter){
        Query query = em.createQuery("SELECT p FROM Produto p WHERE p.nome LIKE :filtro");
        query.setParameter("filtro", "%" + filter + "%");
        return query.getResultList();
    }    
}
