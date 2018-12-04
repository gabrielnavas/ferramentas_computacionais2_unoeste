
package exheranca;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ExHeranca {
    private static void mostraFicha(Funcionario f)
    {
        System.out.println("Nome: "+f.getNome());
        System.out.println("Salário de R$ "+f.getSalario()+" semanal");
        System.out.println("Imposto de R$ "+f.getImpostoDescontado());
        if (f instanceof Gerente)
           System.out.println("Adicional de "+((Gerente)f).getAdicional()+"%");
        
    }
    public static void main(String[] args) 
    {   Equipe equipe;
        equipe=Equipe.carregar();
        if(equipe==null)    
           equipe=new Equipe();
        
        //equipe.contrata(new Funcionario(123,"Rudolph",40,18.5));
       // equipe.contrata(new Funcionario(321,"Prancer",40,12.30));
       // equipe.contrata(new Gerente(999,"Papai Noel",40,25,50));
        System.out.println(equipe.listaEquipe());
        equipe.gravar();
                
        
        
        
        
//        System.out.println(f1);
//        Funcionario[] equipe=new Funcionario[30];
//        equipe[0]=f1;         
//        equipe[1]=f2;
//        equipe[2]=g;
//        for (int i = 0; i < 3; i++) 
//            mostraFicha(equipe[i]);
    }
}
