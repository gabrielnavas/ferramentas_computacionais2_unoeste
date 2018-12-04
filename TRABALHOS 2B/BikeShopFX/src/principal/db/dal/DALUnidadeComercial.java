package principal.db.dal;

import principal.db.entidade.UnidadeComercial;
import principal.db.util.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import principal.db.entidade.Produto;


public class DALUnidadeComercial {
    
    public boolean gravar(UnidadeComercial uc)
    {
        String sql = "insert into unidade_comercial (unitcomerc_descr) values('@1')";
        sql = sql.replace("@1", uc.getDescricao());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(UnidadeComercial uc)
    {
        String sql = "update unidade_comercial set unitcomerc_descr = '@1' where unitcomerc_cod = '@2'";
        sql = sql.replace("@1", uc.getDescricao());
        sql = sql.replace("@2", ""+uc.getCod());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean apagar(UnidadeComercial uc)
    {
        String sql = "delete from unidade_comercial where unitcomerc_cod = " + uc.getCod();
        return Banco.getCon().manipular(sql);
    }
    
    public UnidadeComercial get(int cod)
    {
        String sql = "select * from unidade_comercial where unitcomerc_cod = "+cod;
        
        UnidadeComercial uc = null;
        
        try
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            if(rs.next())
                uc = new UnidadeComercial(
                        cod, 
                        rs.getString("unitcomerc_descr")
                );
        }
        catch(Exception ex)
        {
            System.out.println("ERRO: " + ex.toString());
        }
        
        return uc;
    }
    
    public List<UnidadeComercial> get(String filtro)
    {
        List<UnidadeComercial> ucList = new ArrayList();
        
        String sql = "select * from unidade_comercial";
        
        if(!filtro.isEmpty())
            sql += " where " + filtro;
        sql += " order by unitcomerc_descr";
        
        try
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            while(rs.next())
                ucList.add(new UnidadeComercial(
                            rs.getInt("unitcomerc_cod"),
                            rs.getString("unitcomerc_descr")   
                ));
        }
        catch(Exception ex)
        {
            System.out.println("ERRO: " + ex.toString());
        }
        
        return ucList;
    }
}
