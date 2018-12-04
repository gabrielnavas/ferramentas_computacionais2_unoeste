/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocoanotacoes;

import Models.Anotacao;
import Models.BlocoAnotacoes;
import java.io.File;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author navas
 */
public class Main {

    public static void main(String[] args) {
        BlocoAnotacoes ba = new BlocoAnotacoes();

        Anotacao at1 = new Anotacao();
        at1.setTitulo("Titulo1");
        at1.setTexto("Este eh o texto um");
        at1.setData(LocalDate.now());
        ba.inserirAnotacao(at1);

        Anotacao at2 = new Anotacao();
        at2.setTitulo("Titulo2");
        at2.setTexto("Este eh o texto dois");        
        at2.setData(LocalDate.now());
        ba.inserirAnotacao(at2);

        Anotacao at3 = new Anotacao();
        at3.setTitulo("Titulo3");
        at3.setTexto("Este eh o texto tres");
        at3.setData(LocalDate.now());
        ba.inserirAnotacao(at3);
        
        
        System.out.println(ba.readAnotacoes());

        
        List<Anotacao> anotacoes = ba.getAllAnotacoes();

        if(anotacoes.size()>0)
            for(Anotacao at : anotacoes)
            {
                System.out.println(at.getTitulo());
                System.out.println(at.getData().toString());
                System.out.println(at.getTexto());
            }
        
        Anotacao anotSearch = ba.buscarAnotacoes("Titulo1");
        if(anotSearch != null)
        {
            
            ba.apagarAnotacao(anotSearch);
            System.out.println("DELETADO");
        }
            
        else
            System.out.println("NAO ENCONTRADO");
////        
//        File f = new File("");
//        System.out.println(f.getAbsolutePath());
    }
    
}
