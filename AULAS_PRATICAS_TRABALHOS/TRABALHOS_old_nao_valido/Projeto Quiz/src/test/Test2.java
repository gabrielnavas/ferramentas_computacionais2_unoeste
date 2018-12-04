/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author navas
 */
public class Test2 {
    Pessoa p;
    
    p = Pessoa.carregar();
    if(p == null)    
       p= new Pessoa();

    //equipe.contrata(new Funcionario(123,"Rudolph",40,18.5));
   // equipe.contrata(new Funcionario(321,"Prancer",40,12.30));
   // equipe.contrata(new Gerente(999,"Papai Noel",40,25,50));
    System.out.println(equipe.listaEquipe());
    equipe.gravar();
}
