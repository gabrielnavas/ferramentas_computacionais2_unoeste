package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Quiz {
    
    //QUESTOES SORTEADAS DE ACORDO COM O METODO;
    List<Questao> questoesSorteadas;
    
    //QUESTOES TODAS DA MEMORIA;
    List<Questao> questoesTodas;

    public Quiz() {
        this.questoesSorteadas = new ArrayList();
        this.questoesTodas = new ArrayList();
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
    
     public void gravar()
    {
        //GRAVAR TODAS PERGUNTAS DO ARQUIVO;
    }
    
    public void carregar()
    {
        //CARREGAR TODAS PERGUNTAS DO ARQUIVO;
    }
    
    public void montarQuestionario(int qtde)
    {
        //BUSCAR NO BANCO OU ARQUIVO?!
    }
    
    public void montarQuestionario(int qtde, String tema)
    {
        //BUSCAR NO BANCO OU ARQUIVO?!
    }
    
    public void montarQuestionario(int qtde, int graduacao)
    {
        //BUSCAR NO BANCO OU ARQUIVO?!
    }
    
    public void aplicarQuestionario()
    {
        for(Questao q : this.questoesSorteadas)
        {
            String resposta = JOptionPane.showInputDialog(null, ""+q.getPergunta());            
            q.responder(resposta);
            
            q.analisarResposta();
            JOptionPane.showMessageDialog(null, q.getRetornoAnalise());
        }
            
    }
    
    public double mostrarResultado()
    {
        int qntdQuestao = this.questoesSorteadas.size();
        if(qntdQuestao > 0)
        {
            int qntdQuestaoAcertadas=0;

            for(Questao q : this.questoesSorteadas)
                if(q.getStatus() == Questao.respondida)
                    qntdQuestaoAcertadas++;

            return qntdQuestaoAcertadas/qntdQuestao*100;
        }
        
        return 0.0;
    }
}
