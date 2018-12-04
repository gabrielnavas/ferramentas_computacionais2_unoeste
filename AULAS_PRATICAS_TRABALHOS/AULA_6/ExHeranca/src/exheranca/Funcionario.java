package exheranca;

public class Funcionario implements Modelo{
    private int ctps;
    private String nome;
    protected int numhtrab;
    protected double valorhora;
    
    public Funcionario() {
        this(0,"",0,0);       //chamar o super no lugar
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

    public void setNome(String nome) {
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
    public boolean equals (Object obj)
    {
        return ctps == ((Funcionario)obj).getCtps();
    }
    
    @Override
    public double getImpostoDescontado()
    {
        return getSalario()*(IMPOSTO/100);
    }
}
