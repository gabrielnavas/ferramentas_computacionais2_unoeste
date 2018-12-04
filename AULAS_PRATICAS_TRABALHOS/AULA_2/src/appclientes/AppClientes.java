
package appclientes;

import java.util.ArrayList;
import java.util.List;


public class AppClientes 
{

    public static void main(String[] args) 
    {

//        
//        TodosClientes tc = new TodosClientes();
//        tc.insere(cli);
//        tc.insere(new Cliente(123432, "Gabriel Navas", "189296-5528", 1000));
//        tc.insere(new Cliente(321321, "Keyla", "189296-5528", 500));
//        tc.insere(new Cliente(321123, "Ronaldo", "189296-5528", 800));
//        tc.remove(321123);
//        
//        System.out.println(tc.listaClientesRenda(500));
////        
////        Cliente cli= new Cliente(123432,"Heitor","189996-5588", 500);
////        
////        List<Cliente> lClientes = new ArrayList<>();
////        lClientes.add(new Cliente(123432,"Heitor","189996-5588", 501));
////        lClientes.add(new Cliente(123432,"Heitor","189996-5588", 502));
////        lClientes.add(new Cliente(123432,"Heitor","189996-5588", 503));
////        
////        System.out.println(lClientes.size());
////        System.out.println(lClientes.get(0).toString());
////        
////        for(Cliente c : lClientes)
////            System.out.println(c.toString());
        
        List<Cliente> lClientes = new ArrayList<>();
        lClientes.add(new Cliente(123432,"Heitor","189996-5588", 501));
        lClientes.add(new Cliente(123432,"Heitor","189996-5588", 502));
        lClientes.add(new Cliente(123432,"Heitor","189996-5588", 503));
        
        lClientes.remove(2);
    }
    
}
