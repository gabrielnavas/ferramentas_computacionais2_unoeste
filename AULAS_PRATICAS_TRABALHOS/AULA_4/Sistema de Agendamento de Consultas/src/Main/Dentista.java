package Main;


public class Dentista {
    private String nome;
    private String endereco;
    private String especializacao;
    
    private Agenda agenda;
    
    public Dentista(){
       this.nome="";
       this.endereco="";
       this.especializacao="";
    }

    public Dentista(String nome, String endereco, String especializacao) {
        this.nome = nome;
        this.endereco = endereco;
        this.especializacao = especializacao;
    }
    
    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }
    
    
}
