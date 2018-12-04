/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appclientes;

/**
 *
 * @author Aluno
 */
public class TodosClientes {
    private Cliente[] clientes;
    private int tl;
    
    public TodosClientes()
    {
        this(100);   
    }
    
    public TodosClientes(int max)
    {
        tl=0;
        clientes = new Cliente[max];
    }
    
    public boolean insere(Cliente c)
    {
        if(tl < clientes.length)
        {
            clientes[tl++]=c;
            return true;
        }
        
        return false;
    }
    
    public String listaClientes()
    {
        return this.listaClientesRenda(0);
    }
    
    public String listaClientesRenda(double renda)
    {
        String clientesTodos = "";
        
        for(int i=0 ;  i < clientes.length ; i++)
        {
            if(clientes[i].getRenda() >= renda)
            {
                clientesTodos += clientes[i].getNome();
                clientesTodos = (i == tl-1) ? "" : ", ";    
            }
            
        }
        
        return clientesTodos;
    }
    
    public boolean remove(int rg)
    {
        for(int i=0 ; i < tl ; i++)
               if(clientes[i].getRg() == rg)
               {
                    for(int j=i ; j < tl-1 ; j++)
                        clientes[j]=clientes[j++];
                    
                    return true;
               }
        return false;
    }
}
