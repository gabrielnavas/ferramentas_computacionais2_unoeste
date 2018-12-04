package exheranca;
// superclasse

import java.io.Serializable;

public class Funcionario implements Modelo, Serializable
{   private int ctps;
    private String nome;
    protected int numhtrab;//numero de horas trabalhadas
    protected double valorhora;//valor por hora

    public Funcionario() {
        this(0,"",0,0);
    }

    public Funcionario(int ctps, String nome, int numhtrab, double valorhora) {
        this.ctps = ctps;
        this.nome = nome;
        this.numhtrab = numhtrab;
        this.valorhora = valorhora;
    }

    public int getCtps() {
        return ctps;
    }

    public void setCtps(int ctps) {
        this.ctps = ctps;
    }

    public String getNome() {
        return nome;
    }

    final public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumhtrab() {
        return numhtrab;
    }

    public void setNumhtrab(int numhtrab) {
        this.numhtrab = numhtrab;
    }

    public double getValorhora() {
        return valorhora;
    }

    public void setValorhora(double valorhora) {
        this.valorhora = valorhora;
    }
    @Override
    public double getSalario()
    {
        return valorhora * numhtrab - (IMPOSTO/100);
    }  
    
    @Override
    public String toString()
    {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        return ctps==((Funcionario)obj).getCtps();
        
    }

    @Override
    public double getImpostoDescontado() {
        return getSalario()*(IMPOSTO/100);
    }
    
}
