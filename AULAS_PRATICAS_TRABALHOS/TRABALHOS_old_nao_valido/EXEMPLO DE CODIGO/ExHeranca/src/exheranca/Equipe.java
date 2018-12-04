package exheranca;


import exheranca.Funcionario;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Equipe implements Serializable
{
    private List <Funcionario> lequipe;
    public Equipe()
    {  lequipe=new ArrayList();
    }
    public void contrata(Funcionario f)
    {
        lequipe.add(f);
    }
    public String listaEquipe()
    {
        String aux="";
        for(Funcionario f : lequipe)
        {
            aux+=f.getNome()+" ";
        }
        return aux;
    }
    public void gravar()
    {
        FileOutputStream fout = null;
        ObjectOutputStream out;
        try {
            fout = new FileOutputStream("objeto.dat");
            out = new ObjectOutputStream(fout);
            out.writeObject(this);
            out.close();
        } 
        catch (Exception e) { System.out.println("Erro:"+e.getMessage()); }
    }
    static public Equipe carregar()
    {   Equipe equipe=null;
        FileInputStream fin = null;
        ObjectInputStream in;
        try {
            fin = new FileInputStream("objeto.dat");
            in = new ObjectInputStream(fin);
            equipe = (Equipe) in.readObject();
            in.close();
        } 
        catch (Exception e) {
            System.out.println("Erro:"+e.getMessage());
            equipe=null;
        }
        return equipe;
    }
}
