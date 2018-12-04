package principal.db.entidade;

public class Modelo {
    private int cod;
    private String descricao;
    private int quantidade;
    private double preco;
    private double total;
    private char tipo;

    public Modelo() {
        this.cod = 0;
        this.descricao = "";
        this.quantidade = 0;
        this.preco = 0.0;
        this.total = 0.0;
        this.tipo = 0;
    }

    public Modelo(int cod, String descricao, int quantidade, double preco, double total, char tipo) {
        this.cod = cod;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.total = total;
        this.tipo = tipo;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
}
