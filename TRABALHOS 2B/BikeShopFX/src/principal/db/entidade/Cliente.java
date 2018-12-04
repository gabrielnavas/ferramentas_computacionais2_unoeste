package principal.db.entidade;

import java.time.LocalDate;

public class Cliente {
    private int cod;
    private String nome;
    private String cpf;
    private char sexo; /* M/F */
    private LocalDate dataNasc;
    private Cidade cidade;
    private Estado estado;
    private String telefone;
    private String email;

    public Cliente()
    {
        this(0, "", "", 'M', null, null, null, "", "");
    }

    public Cliente(int cod, String nome, String cpf, char sexo, 
            LocalDate dataNasc, Cidade cidade, Estado estado, String telefone, String email) 
    {
        this.cod = cod;
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.email = email;
    }

    public Cliente(String nome, String cpf, char sexo, LocalDate dataNasc, 
            Cidade cidade, Estado estado, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.email = email;
    }
    
    
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

