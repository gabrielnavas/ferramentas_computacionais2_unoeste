package quizfx;

import java.io.Serializable;
import java.util.Objects;

public class Categoria implements Serializable{
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
    
    @Override
    public String toString()
    {
        return this.getTema();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categoria other = (Categoria) obj;
        if (this.graduacao != other.graduacao) {
            return false;
        }
        if (!Objects.equals(this.tema, other.tema)) {
            return false;
        }
        return true;
    }
    
    
}
