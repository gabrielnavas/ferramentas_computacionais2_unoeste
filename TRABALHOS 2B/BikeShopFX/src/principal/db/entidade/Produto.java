/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.db.entidade;

public class Produto {
    
    private int cod;
    private TipoProduto tipoProduto;
    private String descricao;
    private UnidadeComercial unidadeComercial;
    private Marca marca;
    private double preco;

    public Produto()
    {
        this(null, "", null, null, 0.0);
    }
    
    public Produto(
            TipoProduto tipoProduto, 
            String descricao, 
            UnidadeComercial unidadeComercial, 
            Marca marca, 
            double preco) 
    {
        this.tipoProduto = tipoProduto;
        this.descricao = descricao;
        this.unidadeComercial = unidadeComercial;
        this.marca = marca;
        this.preco = preco;
    }

    public Produto(int cod, TipoProduto tipoProduto, String descricao, UnidadeComercial unidadeComercial, 
            Marca marca, double preco) {
        this.cod = cod;
        this.tipoProduto = tipoProduto;
        this.descricao = descricao;
        this.unidadeComercial = unidadeComercial;
        this.marca = marca;
        this.preco = preco;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UnidadeComercial getUnidadeComercial() {
        return unidadeComercial;
    }

    public void setUnidadeComercial(UnidadeComercial unidadeComercial) {
        this.unidadeComercial = unidadeComercial;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.cod;
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
        final Produto other = (Produto) obj;
        if (this.cod != other.cod) {
            return false;
        }
        return true;
    }
}

