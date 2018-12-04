package principal.db.dal;

import principal.db.entidade.TipoProduto;
import principal.db.util.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import principal.db.entidade.Produto;


public class DALTipoProduto {
    
    public boolean gravar(TipoProduto tp)
    {
        String sql = "insert into tipo_produto (tp_descr) values('@1')";
        sql = sql.replace("@1", tp.getDescricao());
        return Banco.getCon().manipular(sql);
    }

    public boolean alterar(TipoProduto tp)
    {
        String sql="update tipo_produto set tp_descr = '@2' where tp_cod = '@1'";
        sql = sql.replace("@1", ""+tp.getCod());
        sql = sql.replace("@2", tp.getDescricao());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean apagar(TipoProduto tp)
    {
        return Banco.getCon().manipular("delete from tipo_produto where tp_cod = " + tp.getCod());
    }
    
    public TipoProduto get(int cod)
    {
        TipoProduto tp = null;
        
        String sql = "select * from tipo_produto where tp_cod= "+cod;
        
        try
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            if(rs.next())
                tp = new TipoProduto(rs.getInt("tp_cod"), rs.getString("tp_descr"));
        }
        catch(Exception ex)
        {
            System.out.println("ERRO GET TIPOPRODUTO DALTIPOPRODUTO: " + ex.toString());
        }
        
        return tp;
    }
    
    public List<TipoProduto> get(String filtro)
    {
        List<TipoProduto> tipoProdutos = new ArrayList();
        
        String sql = "select * from tipo_produto";
        
        if(!filtro.isEmpty())
            sql += " where " + filtro;
        sql += " order by tp_descr";
        
        try
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            
            while(rs.next())
                tipoProdutos.add(new TipoProduto(rs.getInt("tp_cod"), rs.getString("tp_descr")));
        }
        catch(Exception ex)
        {
            System.out.println("ERRO GET TIPOPRODUTO DALTIPOPRODUTO FILTRO: " + ex.toString());
        }
        
        return tipoProdutos;
    }
}
