/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appclientes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class TodosClientes2 {
    private List<Cliente>clientes;
    
    public TodosClientes2()
    {
        clientes = new ArrayList<>();   
    }
    
    
    public boolean insere(Cliente c)
    {
        return clientes.add(c);
    }
    
    public String listaClientes()
    {
        return this.listaClientesRenda(0);
    }
    
    public String listaClientesRenda(double renda)
    {
        String clientesTodos = "";
        
        for(Cliente c : clientes)
            if(c.getRenda() >= renda)
                clientesTodos +=c.getNome()+", ";
        if(clientesTodos.length() > 0)
            clientesTodos=clientesTodos.substring(0, clientesTodos.length()-3);
        
        return clientesTodos;
    }
    
    public boolean remove(int rg)
    {
        for(Cliente c : clientes)
            if(c.getRg() == rg)
            {
                clientes.remove(c);
                return true;
            }
        
        return false;
    }
}
