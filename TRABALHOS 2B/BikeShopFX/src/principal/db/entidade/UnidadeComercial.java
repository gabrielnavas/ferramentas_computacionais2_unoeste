package principal.db.entidade;

public class UnidadeComercial {
    private int cod;
    private String descricao;

    public UnidadeComercial()
    {
        this(0, "");
    }
    
    public UnidadeComercial(String descricao) {
        this.descricao = descricao;
    }

    public UnidadeComercial(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
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

    @Override
    public String toString() {
        return descricao;
    }
}