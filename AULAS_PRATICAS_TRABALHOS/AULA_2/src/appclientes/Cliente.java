
package appclientes;

public class Cliente 
{
    private int rg;
    private String nome, fone;
    private double renda;

    public Cliente(int rg, String nome, String fone, double renda) 
    {
        this();
        setRg(rg);
        this.nome = nome;
        this.fone = fone;
        setRenda(renda);
    }

    public Cliente() {
        rg=1;
        nome=fone="";
        renda=0;
    }

    Cliente(Cliente cli) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        if(rg>0)
            this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        if(renda>=0)
            this.renda = renda;
    }
    
    public String toString()
    {
        return rg+" "+nome+" "+fone+" "+renda;
    }
    
    
}
