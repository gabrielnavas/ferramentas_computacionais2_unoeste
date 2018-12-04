package principal.db.entidade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Orcamento {
    
    public static class ItemServico //==> classe dentro da classe INNER CLASS, classe embutida
    {
        private int cod;
        private Servico servico;
        private int quantidade;

        public ItemServico(Servico servico, int quantidade) {
            this(0, servico, quantidade);
        }
        
        public ItemServico(int cod, Servico servico, int quantidade) {
            this.cod = cod;
            this.servico = servico;
            this.quantidade = quantidade;
        }

        public int getCod() {
            return cod;
        }
        
        public Servico getServico() {
            return servico;
        }

        public int getQuantidade() {
            return quantidade;
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
            final ItemServico other = (ItemServico) obj;
            if (this.cod != other.cod) {
                return false;
            }
            if (this.quantidade != other.quantidade) {
                return false;
            }
            if (!Objects.equals(this.servico, other.servico)) {
                return false;
            }
            return true;
        }
    }
    
    public static class ItemProduto
    {
        private int cod;
        private Produto produto;
        private int quantidade;
        
        public ItemProduto(Produto produto, int quantidade)
        {
            this(0, produto, quantidade);
        }
        
        public ItemProduto(int cod, Produto produto, int quantidade)
        {
            this.cod = cod;
            this.produto = produto;
            this.quantidade = quantidade;
        }

        public int getCod() {
            return cod;
        }

        public Produto getProduto() {
            return produto;
        }

        public void setProduto(Produto produto) {
            this.produto = produto;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
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
            final ItemProduto other = (ItemProduto) obj;
            if (this.cod != other.cod) {
                return false;
            }
            if (this.quantidade != other.quantidade) {
                return false;
            }
            if (!Objects.equals(this.produto, other.produto)) {
                return false;
            }
            return true;
        }
    }
    
    private int cod;
    private LocalDate data;
    private int prazo;
    private int condpgto;
    private double desconto;
    private double total;
    
    private Cliente cliente;
    
    private List<ItemServico> listServicos;
    private List<ItemProduto> listProdutos;
    
    /* select * from orcamento, servico, item_servico 
    where item_servico.orc_cod = orcamento.orc_cod and item_servico.ser_cod = servico.ser_cod*/

    public Orcamento() 
    {
        this(0, null, LocalDate.now(), 0, 0, 0.0, 0.0);
    }

    public Orcamento(
            int cod,
            Cliente cliente,
            LocalDate data, 
            int prazo, 
            int condpgto,
            double desconto,
            double total) 
    {
        listServicos = new ArrayList();
        listProdutos = new ArrayList();
        this.cliente = cliente;
        this.cod = cod;
        this.data = data;
        this.prazo = prazo;
        this.condpgto = condpgto;
        this.desconto = desconto;
        this.total = total;
    }

    public Orcamento(
            Cliente cliente,
            LocalDate data, 
            int prazo, 
            int condpgto,
            double desconto,
            double total) 
    {
        this(0, cliente,data,prazo,condpgto, desconto,total);
    }

    public int getCod() {
        return cod;
    }

    public LocalDate getData() {
        return data;
    }

    public int getPrazo() {
        return prazo;
    }

    public int getCondpgto() {
        return condpgto;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getTotal() {
        return total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    public void setCondpgto(int condpgto) {
        this.condpgto = condpgto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }
    
    
    /* METODOS SERVICO LIST */    
    public void addServico(int quantidade, Servico servico)
    {
        listServicos.add(new ItemServico(servico, quantidade));
        total += servico.getPreco()*quantidade;
    }
    
    public List<ItemServico> getListServicos() {
        return listServicos;
    }
    
    public boolean excluirItemServico(int quantidade, Servico servico)
    {
        this.total = this.total - (servico.getPreco()*quantidade);
        
        ItemServico its = new ItemServico(servico, quantidade);
        
        return listServicos.remove(its);
    }
    
    public void excluirTodosServicos()
    {
        listServicos.clear();
    }
    
    public int getQuantServicosList()
    {
        return listServicos.size();
    }
    
    public void setListServicos(List<ItemServico> listServicos) {
        this.listServicos = listServicos;
    }
    
    
    /* METODOS PRODUTO LIST */
    public void addProduto(int quantidade, Produto produto)
    {
        listProdutos.add(new ItemProduto(produto, quantidade));
        total += produto.getPreco()*quantidade;
    }
    
    public List<ItemProduto> getListProdutos()
    {
        return listProdutos;
    }
    
    public boolean excluirItemProduto(int quantidade, Produto produto)
    {
        this.total = this.total - (produto.getPreco()*quantidade);
        
        ItemProduto itp = new ItemProduto(produto, quantidade);
        
        return listProdutos.remove(itp);
    }
    
    public void excluirTodosProdutos()
    {
        this.total=0.0;
        listProdutos.clear();
    }
    
    public int getQuantProdutosList()
    {
        return listProdutos.size();
    }
    
    public boolean existsItemProduto(Produto p){
        for(ItemProduto ip : this.listProdutos)
            if(ip.getProduto().equals(p))
                return true;
        
        return false;
    }
    
    public boolean existsItemServico(Servico s){
        for(ItemServico is : this.listServicos)
            if(is.getServico().equals(s))
                return true;
        
        return false;
    }

    

    public void setListProdutos(List<ItemProduto> listProdutos) {
        this.listProdutos = listProdutos;
    }
    
    
}
