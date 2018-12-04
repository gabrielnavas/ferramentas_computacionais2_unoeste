/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Categoria {
    private String tema;
    
    
    private int graduacao;
    public static final int ensinoFundamental=0;
    public static final int ensinoMedio=1;
    public static final int ensinoSuperior=2;
    
    public Categoria() {
        this.tema = "";
        this.graduacao = 0;
    }
    
    public Categoria(String tema, int graduacao) {
        this();
        
        this.tema = tema;
        this.graduacao = graduacao;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        if(tema.length() > 0)
            this.tema = tema;
    }

    public int getGraduacao() {
        return graduacao;
    }

    public void setGraduacao(int graduacao) {
        if(graduacao >=0 && graduacao <= 3)
            this.graduacao = graduacao;
    }
}
