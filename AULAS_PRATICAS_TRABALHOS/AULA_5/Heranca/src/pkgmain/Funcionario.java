package pkgmain;

public class Funcionario {
    private int ctps;
    private String nome;
    protected double numhTrab;
    protected double valorHora;

    public Funcionario() {
        this(0,"",0,0.0);
    }

    public Funcionario(int ctps, String nome, int numhTrab, double valorHora) {
        this.ctps = ctps;
        this.nome = nome;
        this.numhTrab = numhTrab;
        this.valorHora = valorHora;
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

    public double getNumhTrab() {
        return numhTrab;
    }

    public void setNumhTrab(double numhTrab) {
        this.numhTrab = numhTrab;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public double getSalario()
    {
        return this.getValorHora() * this.getNumhTrab();
    }
}
