package projeto.quiz;

public abstract class Questao implements IQuestao{
    private String pergunta;
    private String dica;
    private int status;
    private String resposta;
    private String respostaUsuario;

    public Questao() {
        this.pergunta = "";
        this.dica = "";
        this.resposta = "";
    }
    
    public Questao(String pergunta, String dica, String resposta) {
        setPergunta(pergunta);
        setDica(dica);
        setResposta(resposta);
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        if(pergunta.length() > 0)
            this.pergunta = pergunta;
    }

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        if(dica.length() > 0)
            this.dica = dica;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        if(status == 0 || status == 1 || status == 2)
            this.status = status;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        if(resposta.length() > 0)
            this.resposta = resposta;
    }

    public String getRespostaUsuario() {
        return respostaUsuario;
    }

    public void setRespostaUsuario(String respostaUsuario) {
        if(respostaUsuario.length() > 0)
            this.respostaUsuario = respostaUsuario;
    }
    
    public abstract void responder(String resposta);
    public static void main(String[] args) {
        
    }
}
