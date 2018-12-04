package exheranca;

public class Gerente extends Funcionario{
    private double adicional; //atributo novo
    public Gerente(){
        super(); //chama o construtor sem param. da superclasse, TEM QUE ESTAR NA PRIMEIRA LINHA
        adicional = 0;
    }
    public Gerente (int ctps, String nome, int numhtrab, double valorhora, double adicional)
    {
        super(ctps,nome,numhtrab,valorhora);
        this.adicional = adicional;
    }

    public double getAdicional() {
        return adicional;
    }

    public void setAdicional(double adicional) {
        this.adicional = adicional;
    }
    
    @Override
    public double getSalario()  //over ride
    {
        //return super.getValorhora() * super.getNumhtrab() * (1+(adicional/100));
        //return super.getSalario() * (1+(adicional/100));
        return valorhora * numhtrab * (1+(adicional/100));      // coloquei o procted e posso acessar desta forma
    }
}
