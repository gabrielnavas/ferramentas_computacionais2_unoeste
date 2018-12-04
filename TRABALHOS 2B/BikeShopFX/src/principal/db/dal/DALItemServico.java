package principal.db.dal;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import principal.db.entidade.Orcamento;
import principal.db.entidade.Orcamento.ItemServico;
import principal.db.util.Banco;


public class DALItemServico {
    
    public boolean gravar(ItemServico itemServico, int orcCod)
    {
        String sql = "INSERT INTO item_servico (orc_cod, ser_cod, its_quant) "
                + "VALUES ('@1', '@2', '@3')";
        sql = sql.replace("@1", ""+orcCod);
        sql = sql.replace("@2", ""+itemServico.getServico().getCod());
        sql = sql.replace("@3", ""+itemServico.getQuantidade());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(ItemServico itemServico, int orcCod)
    {
        String sql = "update item_servico set "
                + "its_cod = '@1', orc_cod = '@2', ser_cod = '@3', its_quant = '@4'";
        sql = sql.replace("@1", "" + itemServico.getCod());
        sql = sql.replace("@2", "" + orcCod);
        sql = sql.replace("@3", "" + itemServico.getServico().getCod());
        sql = sql.replace("@4", "" + itemServico.getQuantidade());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean apagar(ItemServico itemServico, int orcCod)
    {
        return Banco.getCon().manipular("delete from item_servico where its_cod = " + itemServico.getCod());
    }
    
    public ItemServico get(int cod)
    {
        ItemServico its = null;
        
        try
        {
            String sql = "select * from item_servico where its_cod = " + cod;
            ResultSet rs = Banco.getCon().consultar(sql);
            
            if(rs.next())
                its = new ItemServico(
                    cod,
                    new DALServico().get(rs.getInt("ser_cod")),
                    rs.getInt("its_quant")    
                );
        }
        catch(Exception ex)
        {
            System.out.println("ERROR: " + ex.toString());
        }
        
        return its;
    }
    
    public List<ItemServico> get(String filtro)
    {
        String sql = "select * from item_servico";
        if(!filtro.isEmpty())
            sql += " where " + filtro;
        sql += " order by its_cod";
        
        List<ItemServico> its = new ArrayList();
        
        try
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            
            while(rs.next())
                its.add(new ItemServico(
                        rs.getInt("its_cod"),
                        new DALServico().get(rs.getInt("ser_cod")),
                        rs.getInt("its_quant")
                ));
        }
        catch(Exception ex)
        {
            System.out.println("ERROR: " + ex.toString());
        }
        
        return its;
    }
}
