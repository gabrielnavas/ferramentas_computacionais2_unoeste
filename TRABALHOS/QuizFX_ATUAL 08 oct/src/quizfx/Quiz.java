package quizfx;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Random;

public class Quiz implements Serializable {
    ArrayList<Questao> questoes;
    ArrayList<Questao> questoesSorteadas;

    public Quiz() {
        this.questoes = new ArrayList<Questao>();
        this.questoesSorteadas = new ArrayList<Questao>();
    }
    
    public void adQuestao(Questao q)
    {
        if(q != null)
            this.questoes.add(q);
    }
    
    public void exQuestao(String pergunta)
    {
        for(Questao q : this.questoes)
            if(q.getPergunta().equals(pergunta))
                    this.questoes.remove(q);
    }
    
    private int[] sortearValoresRange(int max)
    {
        int tl=0;
        int[] valores = new int[max];
    
        for(int i=0 ; i < max ; i++)
        {
            int j, valRandom;
            do
            {
                valRandom = (int)(Math.random()*max);
                j=0;
                while(j < tl &&valRandom != valores[j])
                    j++;

                if(j == tl)
                    valores[tl] = valRandom;       
                    
            }while(j != tl);
            
            tl++;
        }
        
        return valores;
    }
    
    public void montarQuestionario(int qtde)
    {
        if(qtde <= this.questoes.size())
        {
            questoesSorteadas.clear();

            int[] numsSort = sortearValoresRange(qtde);
            for(int o=0; o<qtde; o++)   
            {
                Questao q = questoes.get(numsSort[o]);
                q.setStatus(Questao.naoRespondida);
                questoesSorteadas.add(q);
            }       
        }
    }
    
    public void montarQuestionario(int qtde, String tema)
    {
        if(qtde <= this.getTotalQuestaoTema(tema))
        {
            ArrayList<Questao> questoesTema = new ArrayList();
                        
            for(Questao q : questoes) 
                if(q.getCategoria().getTema().equals(tema))
                {
                    q.setStatus(Questao.naoRespondida);
                    questoesTema.add(q);
                }
                    
            
            if(qtde <= questoesTema.size())
            {
                questoesSorteadas.clear();
             
                int[] numsSort = this.sortearValoresRange(qtde);
                for(int o=0; o<qtde; o++) 
                    if(questoesTema.get(numsSort[o]).getCategoria().getTema().equals(tema))
                        questoesSorteadas.add(questoesTema.get(numsSort[o]));
            }
            
        }
    }
    
    public void montarQuestionario(int qtde, int graduacao)
    {
        if(qtde <= this.getTotalQuestaoGraduacao(graduacao))
        {
            ArrayList<Questao> questoesGrad = new ArrayList();
                        
            for(Questao q : questoes) 
                if(q.getCategoria().getGraduacao() == graduacao)
                {
                    q.setStatus(Questao.naoRespondida);
                    questoesGrad.add(q);
                }
            
            if(qtde <= questoesGrad.size())
            {
                questoesSorteadas.clear();
             
                int[] numsSort = this.sortearValoresRange(qtde);
                for(int o=0; o<qtde; o++) 
                    if(questoesGrad.get(numsSort[o]).getCategoria().getGraduacao() == graduacao)
                        questoesSorteadas.add(questoesGrad.get(numsSort[o]));
            }
            
        }
    }
    
    @Deprecated
    public void aplicarQuestionario()
    {
        if(questoesSorteadas.size() > 0)
        {
            
            for(int i=0; i<questoesSorteadas.size();i++)
            {
                Questao q = (Questao) questoesSorteadas.get(i);
                
                String resposta = JOptionPane.showInputDialog(null, ""+q.getPergunta());            
                q.responder(resposta);

                q.analisarResposta();
                JOptionPane.showMessageDialog(null, q.getRetornoAnalise());
            }   
        }
        else
            JOptionPane.showMessageDialog(null, "Questoes não adicionadas");
        
    }
    
    public List<Questao> getQuestionarioMontado()
    {
        if(questoesSorteadas.size() > 0)
               return this.questoesSorteadas;
        
        return null;
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
            
            this.questoesSorteadas.clear();
            return (double) qntdQuestaoAcertadas/qntdQuestao*100;
        }
        
        return 0.0;
    }
    
    public static Quiz carregar()
    {   
        Quiz quiz=null;
        FileInputStream fin = null;
        ObjectInputStream in;
        try {
            fin = new FileInputStream("quiz.dat");
            in = new ObjectInputStream(fin);
            quiz = (Quiz) in.readObject();
            in.close();
        } 
        catch (Exception e) {
            System.out.println("Erro:"+e.getMessage());
            quiz=null;
        }
        return quiz;
    }
    
    public void gravar()
    {
        FileOutputStream fout = null;
        ObjectOutputStream out;
        try {
            fout = new FileOutputStream("quiz.dat");
            out = new ObjectOutputStream(fout);
            out.writeObject(this);
            out.close();
        } 
        catch (Exception e) { System.out.println("Erro:"+e.getMessage()); }
    }

    public int getTotalQuestao() {
        return questoes.size();
    }
    
    public int getTotalQuestaoTema(String temaFiltro){
        int qntd=0;
        for(Questao q : questoes)
            if(q.getCategoria().getTema().equals(temaFiltro))
                qntd++;
        return qntd;
    }
    
    public int getTotalQuestaoGraduacao(int graduacaoFiltro){
        int qntd=0;
        for(Questao q : questoes)
            if(q.getCategoria().getGraduacao() == graduacaoFiltro)
                qntd++;
        return qntd;
    }
    
    public Questao buscarQuestao(Questao qKey)
    {
        for(Questao q : this.questoes)
            if(q.getPergunta().equals(qKey.getPergunta()) && q.getCategoria().equals(qKey.getCategoria()))
                return q;
        
        return null;
    }
}
