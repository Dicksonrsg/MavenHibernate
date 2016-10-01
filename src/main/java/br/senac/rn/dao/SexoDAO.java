
package br.senac.rn.dao;

import br.senac.rn.model.Sexo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class SexoDAO {
    
    private EntityManager em;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexaoDB");
    
    public SexoDAO(){
        if(em == null){
            em = emf.createEntityManager();
        }
    }
    
    public boolean insert(Sexo sexo){
        try{
            em.getTransaction().begin();
            em.persist(sexo);
            em.getTransaction().commit();
            return true;
        }catch(Exception error){
            System.out.println("Error: " + error.toString());
        }
        return false;
    }
    
    public boolean delete(Sexo sexo){
        try{
            em.getTransaction().begin();
            em.remove(sexo);
            em.getTransaction().commit();
            return true;
        }catch(Exception error){
            System.out.println("Error: " + error.toString());
        }
        return false;
    }
    
    public boolean update(Sexo sexo){
        try{
            em.getTransaction().begin();
            em.merge(sexo);
            em.getTransaction().commit();
            return true;
        }catch(Exception error){
            System.out.println("Error: " + error.toString());
        }
        return false;
    }
    
    public List<Sexo> selectAll(){
        return em.createQuery("SELECT s FROM Sexo s").getResultList();
    }
    
    public Sexo selectById(int id){
        return em.find(Sexo.class, id);
    }
    
    public List<Sexo> selectByFilter(String filter){
        Query query = em.createQuery("SELECT s FROM Sexo s WHERE s.nome LIKE :filtro");
        query.setParameter("filtro", "%" + filter + "%");
        return query.getResultList();
    }
}
