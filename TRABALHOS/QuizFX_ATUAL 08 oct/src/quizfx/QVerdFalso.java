package quizfx;

import java.text.Collator;
import java.util.Locale;

public class QVerdFalso extends Questao{
    
    
    public QVerdFalso(String pergunta, String dica, String resposta) {
        super(pergunta, dica, resposta);
    }

    @Override
    public String getRetornoAnalise() {
        switch (super.getStatus()) {
            case Questao.naoRespondida:
                return "Incorreto.";
                
            case Questao.respondida:
                return "Correto.";
            
            case Questao.respondidaIncorreta:
                return "Incorreta.";
            
            default:
                return "Erro: Chame o Coodernador do curso.";
        }
    }

    @Override
    public void analisarResposta() {
        if(super.getRespostaUsuario().length() > 0)
        {
            Collator collator = Collator.getInstance(new Locale("pt", "BR"));
            collator.setStrength(Collator.PRIMARY);
            
            String respUser = super.getRespostaUsuario().toLowerCase();
            String respQuestion = super.getResposta().toLowerCase();
            
            if(collator.compare(respUser, respQuestion) == 0)
            {
                super.setStatus(Questao.respondida);
            }
//            
//            if(respUser.equals(respQuestion))
//                super.setStatus(Questao.respondida);
//            
            else if( !respUser.equals(respQuestion))
                super.setStatus(Questao.respondidaIncorreta);
        }
        else
            super.setStatus(Questao.naoRespondida);
    }
    
}