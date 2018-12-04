/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgmain;

/**
 *
 * @author Aluno
 */
public class Gerente extends Funcionario{
    private double adicional;
    
    public Gerente()
    {
        super();
        this.adicional = 0;
    }
    
    public Gerente(int ctps, String nome, int numhTrab, double valorHora)
    {
        super(ctps, nome, numhTrab, valorHora);
    }
    
    public Gerente(int ctps, String nome, int numhTrab, double valorHora, double adicional)
    {
        super(ctps, nome, numhTrab, valorHora);
        this.adicional = adicional;
    }

    public double getAdicional() {
        return adicional;
    }

    public void setAdicional(double adicional) {
        this.adicional = adicional;
    }
    
    @Override
    public double getSalario()
    {
        //return this.getValorHora() * this.getNumhTrab() * 1 + (this.adicional/100);
        
        //return super.getSalario() * (1+(adicional/100));
        return super.valorHora * super.numhTrab *(1 + (this.adicional/100));
    }
    
}
