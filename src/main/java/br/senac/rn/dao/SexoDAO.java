
package br.senac.rn.dao;

import br.senac.rn.model.Sexo;
import java.util.List;
import javax.persistence.Query;


public class SexoDAO extends GenericDAO<Sexo> {
    
    public Class<Sexo> getClassType(){
        return Sexo.class;
    }
    
    public List<Sexo> selectByFilter(String filter){
        try{
            em = getEm();
            Query query = em.createQuery("SELECT s FROM Sexo s WHERE s.nome LIKE : f");
            query.setParameter("f", "%" + filter + "%");
            return query.getResultList();
        }catch(Exception error){
            System.out.println("Erro: " + error.toString());
        }
        return null;
    }
}
