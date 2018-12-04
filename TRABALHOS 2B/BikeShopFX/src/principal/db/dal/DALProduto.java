package principal.db.dal;

import principal.db.entidade.Marca;
import principal.db.entidade.Produto;
import principal.db.entidade.TipoProduto;
import principal.db.entidade.UnidadeComercial;
import principal.db.util.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DALProduto {
    
    public boolean gravar(Produto prod)
    {
        String sql = "insert into produto"
                + "(tp_cod, prod_descricao,"
                + "unitcomerc_cod, marca_cod,"
                + "prod_preco) "
                + "values('@1', '@2', '@3', '@4', '@5')";
        
        sql = sql.replace("@1", ""+prod.getTipoProduto().getCod());
        sql = sql.replace("@2", prod.getDescricao());
        sql = sql.replace("@3", ""+prod.getUnidadeComercial().getCod());
        sql = sql.replace("@4", ""+prod.getMarca().getCod());
        sql = sql.replace("@5", ""+prod.getPreco());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Produto prod)
    {
        String sql = "update produto set "
                + "tp_cod = '@1',"
                + "prod_descricao = '@2',"
                + "unitcomerc_cod = '@3', "
                + "marca_cod = '@4',"
                + "prod_preco = '@5' "
                + "where prod_cod = '@6'";
        
        sql = sql.replace("@1", ""+prod.getTipoProduto().getCod());
        sql = sql.replace("@2", prod.getDescricao());
        sql = sql.replace("@3", ""+prod.getUnidadeComercial().getCod());
        sql = sql.replace("@4", ""+prod.getMarca().getCod());
        sql = sql.replace("@5", ""+prod.getPreco());
        sql = sql.replace("@6", ""+prod.getCod());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean apagar(Produto prod)
    {
        String sql = "delete from produto where prod_cod = " + prod.getCod();
        return Banco.getCon().manipular(sql);
    }
    
    public Produto get(int cod)
    {
        Produto produto = null;
        
        String sql = "select * from produto where prod_cod = " + cod;
        ResultSet rs = Banco.getCon().consultar(sql);

        try
        {
            if(rs.next())
            {
                DALTipoProduto dtp = new DALTipoProduto();
                DALUnidadeComercial duc = new DALUnidadeComercial();
                DALMarca dm = new DALMarca();
                                
                produto = new Produto(
                        cod, 
                        dtp.get(rs.getInt("tp_cod")), /* TipoProduto */
                        rs.getString("prod_descricao"),
                        duc.get(rs.getInt("unitcomerc_cod")), /*UnidadeComercial*/
                        dm.get(rs.getInt("marca_cod")), /* Marca */
                        rs.getDouble("prod_preco")
                );
            }
        }
        catch(Exception ex)
        {
            System.out.println("ERROR DAL PRODUTO GET PRODUTO: " + ex.toString());
        }
        
        return produto;
    }
    
    public List<Produto> get(String filtro)
    {
        List<Produto> produtos = new ArrayList();
        
        String sql = "select * from produto";
        
        if(!filtro.isEmpty())
            sql += " where " + filtro;
        sql += " order by prod_descricao";
        
        try
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            
            DALTipoProduto dtp = new DALTipoProduto();
            DALUnidadeComercial duc = new DALUnidadeComercial();
            DALMarca dm = new DALMarca();
            
            while(rs.next())
                produtos.add(new Produto(
                        rs.getInt("prod_cod"), 
                        dtp.get(rs.getInt("tp_cod")),
                        rs.getString("prod_descricao"),
                        duc.get(rs.getInt("unitcomerc_cod")),
                        dm.get(rs.getInt("marca_cod")),
                        rs.getDouble("prod_preco")
                ));
        }
        catch(Exception ex)
        {
            System.out.println("ERROR DAL PRODUTO GET PRODUTO FILTRO: " + ex.toString());
        }
        
        return produtos;
    }
}
