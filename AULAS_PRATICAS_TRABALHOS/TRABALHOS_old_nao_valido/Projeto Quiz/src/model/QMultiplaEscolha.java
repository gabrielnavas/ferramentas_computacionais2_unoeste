package model;

public class QMultiplaEscolha extends Questao{

    private String[] alternativas;
    private int alternativaCorreta;

    public QMultiplaEscolha(String pergunta, String dica, 
            String resposta, String[] alternativas) {
        super(pergunta, dica, resposta);
        
        this.alternativas = alternativas;
        this.alternativaCorreta = -1;
    }
    
    @Override
    public String getRetornoAnalise() {
        switch (super.getStatus()) {
            case Questao.naoRespondida:
                return "Não respondida: " + super.getResposta();
                
            case Questao.respondida:
                return "Correta. Voce marcou Alternativa " +
                        this.alternativaCorreta + 
                        ". Resposta: " +
                        super.getResposta();
            
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
            for(int i=0 ; i < this.alternativas.length ; i++)
                if(super.getRespostaUsuario().equals(this.alternativas[i]))
                {
                    super.setStatus(Questao.respondida);
                       this.alternativaCorreta=i;
                }
            if(super.getStatus() == Questao.naoRespondida)
                super.setStatus(Questao.respondidaIncorreta);
        }       
        else
            super.setStatus(Questao.naoRespondida);
                    
    }
    
}
