package exheranca;

public class Gerente extends Funcionario  
{   private double adicional;// atributo novo
    public Gerente()
    {
        super(); //chama o construtor s/ param. da superclasse
        adicional=0;
    }
    public Gerente(int ctps, String nome, int numhtrab, double valorhora, 
            double adicional)
    {
        super(ctps,nome,numhtrab,valorhora);
        this.adicional=adicional;
    }

    public double getAdicional() //método novo
    {
        return adicional;
    }

    public void setAdicional(double adicional) //método novo
    {
        this.adicional = adicional;
    }
    
    @Override
    public double getSalario() //método override (sobrescrito
    {
        //return super.getValorhora() * super.getNumhtrab() * (1+(adicional/100));
        //return super.getSalario()* (1+(adicional/100));
        return valorhora* numhtrab * (1+(adicional/100));
    }
    
}
