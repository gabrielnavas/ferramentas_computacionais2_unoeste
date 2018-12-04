package quizfx;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

public class QMultiplaEscolha extends Questao{

    private String[] alternativas;
    
    
    public QMultiplaEscolha(String pergunta, String dica,String resposta, String[] alternativas) {
        super(pergunta, dica, resposta);
        this.alternativas = alternativas;
    }
    
    @Override
    public String getRetornoAnalise() {
        switch (super.getStatus()) {
            case Questao.naoRespondida:
                return "Não respondida: " + super.getResposta();
                
            case Questao.respondida:
                return "Correta. \n" +
                        "Resposta: " + super.getResposta();
            
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
            Collator collator = Collator.getInstance(new Locale("pt", "BR"));
            collator.setStrength(Collator.PRIMARY);
            
            for(int i=0 ; i < this.alternativas.length ; i++)
                if(collator.compare(super.getRespostaUsuario(), super.getResposta()) == 0 && 
                        collator.compare(super.getRespostaUsuario(), this.alternativas[i]) == 0)
                {
                    super.setStatus(Questao.respondida);
                }
            if(super.getStatus() == Questao.naoRespondida)
                super.setStatus(Questao.respondidaIncorreta);
        }       
        else
            super.setStatus(Questao.naoRespondida);
                    
    }
    
    public String[] getAlternativas()
    {
        return this.alternativas;
    }
    
    public String getAlternativasln()
    {
        String alternativas="";
        for(String alt : this.alternativas)
            alternativas += alt + "\n";
        return alternativas;
    }
 
}
