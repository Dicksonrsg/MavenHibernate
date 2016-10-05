
package br.senac.rn.dao;

import br.senac.rn.model.Venda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class VendaDAO {
    
    private EntityManager em;
    
    public VendaDAO(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexaoDB");
        em = emf.createEntityManager();
    }
    
        public boolean insert(Venda venda){
        try{
            em.getTransaction().begin();
            em.persist(venda);
            em.getTransaction().commit();
            return true;
        }catch(Exception error){
            System.out.println("ERRO: " + error.toString());
        }
        return false;
    }
        
    public boolean delete(Venda venda){
        try{
            em.getTransaction().begin();
            em.remove(venda);
            em.getTransaction().commit();
            return true;
        }catch(Exception error){
            System.out.println("ERRO: " + error.toString());
        }
        return false;
    }    
    
    public boolean update(Venda venda){
        try{
            em.getTransaction().begin();
            em.merge(venda);
            em.getTransaction().commit();
            return true;
        }catch(Exception error){
            System.out.println("ERRO: " + error.toString());
        }
        return false;
    }
    
    public List<Venda> selectAll(){
        try{
            Query query = em.createQuery("SELECT p FROM Produto p");
            return query.getResultList();
        }catch(Exception error){
            System.out.println("ERRO: " + error.toString());
        }
        return null;
    }
    
    public Venda selectById(int id){
        return em.find(Venda.class, id);
    } 
    
    public List<Venda> selectByFilter(String filter){
        Query query = em.createQuery("SELECT v FROM Venda v WHERE v.nome LIKE :filtro");
        query.setParameter("filtro", "%" + filter + "%");
        return query.getResultList();
    }        
}
