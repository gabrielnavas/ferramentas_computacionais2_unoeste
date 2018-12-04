/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import model.Categoria;
import model.QMultiplaEscolha;
import model.QRespostaUnica;
import model.QVerdFalso;
import model.Quiz;

/**
 *
 * @author navas
 */
public class Test {
    public static void main(String[] args) {
        Categoria cat = new Categoria("futebol", Categoria.ensinoFundamental);
        
        QRespostaUnica q1 = new QRespostaUnica("Campeão Mundial 2012", "o melhor", "Corinthians");
        q1.setCategoria(cat);
        
        QVerdFalso q2=new QVerdFalso("Corinthians é campeão Mundial 2012", "sem dicas", "sim");
        q2.setCategoria(cat);
        
        QMultiplaEscolha q3 = new QMultiplaEscolha("Campeão Mundial 2012", "o melhor", "Corinthians", 
            new String[]{
                "Chelsea","Benfica", 
                "Corinthians", "Real Madrid"
            }
        );
        
        Quiz q = new Quiz();
        q.adQuestao(q1); 
        q.adQuestao(q2);
        q.adQuestao(q3);
        
        q.aplicarQuestionario();
        
        System.out.println("RESULTADO: " + (double)q.mostrarResultado() + "%");
    }
}
