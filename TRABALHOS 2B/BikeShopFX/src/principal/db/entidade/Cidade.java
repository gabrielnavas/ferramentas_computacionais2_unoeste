package principal.db.entidade;

public class Cidade {
    
    private int cod;
    private Estado estado;
    private String nome;

    public Cidade() {
        this(0, null, "");
    }

    public Cidade(int cod, Estado estado, String nome) {
        this.cod = cod;
        this.estado = estado;
        this.nome = nome;
    }

    public Cidade(Estado estado, String nome) {
        this.cod = 0;
        this.estado = estado;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    } 

    @Override
    public String toString() {
        return estado.getSigla() + "-" + nome;
    }
}
