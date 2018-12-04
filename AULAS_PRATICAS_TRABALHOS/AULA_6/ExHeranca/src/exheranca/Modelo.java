package exheranca;

public interface Modelo {
    public double IMPOSTO=5; //já é final (não pode modificar nas g=heranças), constante
    public double getSalario(); // metodos abstract
    public double getImpostoDescontado(); //abstract
}
