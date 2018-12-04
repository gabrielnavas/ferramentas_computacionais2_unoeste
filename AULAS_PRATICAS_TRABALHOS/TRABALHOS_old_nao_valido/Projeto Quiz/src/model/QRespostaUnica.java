package model;

public class QRespostaUnica extends Questao {

    public QRespostaUnica() {
        super();        
    }

    public QRespostaUnica(String pergunta, String dica, String resposta) {
        super(pergunta, dica, resposta);
    }
    
    @Override
    public String getRetornoAnalise() {
        switch (super.getStatus()) {
            case Questao.naoRespondida:
                return "Não respondida: " + super.getResposta();
                
            case Questao.respondida:
                return "Correta: " + super.getResposta();
            
            case Questao.respondidaIncorreta:
                return "Incorreta: " + super.getResposta();
            
            default:
                return "Erro: Chame o Coodernador do curso.";
        }
    }
    
    @Override
    public void analisarResposta() {
        
        if(super.getRespostaUsuario().length() > 0)
        {
            
            String respUser = super.getRespostaUsuario().toLowerCase();
            String respQuestion = super.getResposta().toLowerCase();

            if(respUser.equals(respQuestion))
                super.setStatus(Questao.respondida);
            
            else if( !respUser.equals(respQuestion))
                super.setStatus(Questao.respondidaIncorreta);
        }
        else
            super.setStatus(Questao.naoRespondida);
    }
}
