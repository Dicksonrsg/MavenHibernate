
package br.senac.rn.view;

import br.senac.rn.dao.ClienteDAO;
import br.senac.rn.dao.SexoDAO;
import br.senac.rn.model.Cliente;
import br.senac.rn.model.Sexo;

public class Principal {
    
    public static void main(String[] args) {
        
        ClienteDAO cdao = new ClienteDAO();
        SexoDAO sdao = new SexoDAO();
        Sexo sex = new Sexo();
        Cliente cli = new Cliente();
        
//        cli.setNome("Cedric");
//        cli.setCpf("012345678910");
//        cli.setSexo(sex = sdao.selectById(1));
//        
//        cdao.insert(cli);
        
        for(Cliente clie : cdao.selectAll()){
            System.out.println(clie.toString());
        }
        
        System.exit(0);
    }
    
}
