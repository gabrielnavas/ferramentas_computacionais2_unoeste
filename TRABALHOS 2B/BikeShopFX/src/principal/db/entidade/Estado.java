package principal.db.entidade;


public class Estado {
    private int cod;
    private String sigla;
    private String nome;

    public Estado() {
        this(0, "", "");
    }

    public Estado(int cod, String sigla, String nome) {
        this.cod = cod;
        this.sigla = sigla;
        this.nome = nome;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return sigla + " - " + nome;
    }
    
    
}
