package quizfx;

import java.io.Serializable;
import java.util.Objects;

public abstract class Questao implements IQuestao, Serializable{
    
    private String pergunta;
    private String dica;
    
    private String resposta;
    private String respostaUsuario;
    
    private Categoria categoria;
    
    /*ESTADOS DA QUESTAO*/
    private int status;
    public static final int naoRespondida=0;
    public static final int respondida=1;
    public static final int respondidaIncorreta=2;
    
    public Questao() {
        this.pergunta = "";
        this.dica = "";
        this.resposta = "";
        this.respostaUsuario= "";
        this.status = Questao.naoRespondida;
        this.categoria = new Categoria();
    }

    public Questao(String pergunta, String dica, String resposta) {
        this();
        
        this.pergunta = pergunta;
        this.dica = dica;
        this.resposta = resposta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public String getDica() {
        return dica;
    }
        
    public void responder(String resposta)
    {
        if(resposta.length() > 0)
            this.respostaUsuario = resposta;
    }

    public int getStatus() {
        return status;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public String getResposta() {
        return resposta;
    }
    
    public String getRespostaUsuario() {
        return respostaUsuario;
    }
    
    public void setStatus(int status)
    {
        if(status >= this.naoRespondida && status <=2)
            this.status = status;
    }

}
