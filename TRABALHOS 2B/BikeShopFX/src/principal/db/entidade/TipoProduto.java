/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.db.entidade;

public class TipoProduto {
    private int cod;
    private String descricao;

    public TipoProduto()
    {
        this(0, "");
    }
    
    public TipoProduto(String descricao) {
        this.descricao = descricao;
    }

    public TipoProduto(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}

