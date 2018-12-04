package principal.db.dal;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import principal.db.entidade.Orcamento;
import principal.db.entidade.Produto;
import principal.db.util.Banco;


public class DALItemProduto {
    
    public boolean gravar(Orcamento.ItemProduto itemProd, int orcCod)
    {
        String sql = "INSERT INTO item_produto (orc_cod, prod_cod, itp_quant) VALUES ('@1', '@2','@3')";
        sql = sql.replace("@1", "" + orcCod);
        sql = sql.replace("@2", "" + itemProd.getProduto().getCod());
        sql = sql.replace("@3", "" + itemProd.getQuantidade());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Orcamento.ItemProduto itemProd, int orcCod)
    {
        String sql = "update item_produto set "
                + "orc_cod = '@1', prod_cod = '@2', itp_quant = '@3'";
        sql = sql.replace("@1", "" + orcCod);
        sql = sql.replace("@2", "" + itemProd.getProduto().getCod());
        sql = sql.replace("@3", "" + itemProd.getQuantidade());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean apagar(Orcamento.ItemProduto itemProd)
    {
        String sql = "delete from item_produto where itp = " + itemProd.getCod();
        return Banco.getCon().manipular(sql);
    }
    
    public Orcamento.ItemProduto get(int cod)
    {
        String sql = "SELECT * FROM item_produto WHERE itp = " + cod;
        
        Orcamento.ItemProduto itemProd = null;
        
        try
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            if(rs.next())
            {
                Produto produto = new DALProduto().get(rs.getInt("prod_cod"));
                
                itemProd = new Orcamento.ItemProduto(
                        cod, 
                        produto, 
                        rs.getInt("itp_quant")
                );
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
        
        return itemProd;
    }
    
    public List<Orcamento.ItemProduto> get(String filtro)
    {
        String sql = "SELECT * FROM item_produto";
        
        if(!filtro.isEmpty())
            filtro += " where " + filtro;
        
        List<Orcamento.ItemProduto> itemsProdutos = new ArrayList();
        
        try
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            while(rs.next())
            {
                Produto produto = new DALProduto().get(rs.getInt("prod_cod"));
                
                itemsProdutos.add(new Orcamento.ItemProduto(
                        rs.getInt("itp_cod"), 
                        produto, 
                        rs.getInt("itp_quant")
                ));
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
        
        return itemsProdutos;
    }
}
