package projeto.quiz;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    List<Questao> questoesSorteadas;

    public Quiz() {
        this.questoesSorteadas = new ArrayList();
    }
    
    public void adQuestao(Questao q)
    {
        if(q != null)
            questoesSorteadas.add(q);
    }
    
    public void exQuestao(String pergunta)
    {
        for(Questao q : this.questoesSorteadas)
            if(q.getPergunta().equals(pergunta))
                    this.questoesSorteadas.remove(q);
    }
    
    public void mostarQuestionario(int qtde)
    {
        List<
        for()
    }
}
