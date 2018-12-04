package principal.db.dal;

import principal.db.entidade.Estado;
import principal.db.util.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DALEstado {
    
    public boolean gravar(Estado estado)
    {
        String sql = "insert into estado (est_cod, est_sgl, est_nome) values ('@0', '@1', '@2')";
        sql = sql.replace("@0", ""+(Banco.getCon().getMaxPK("estado", "est_cod,"))+1);
        sql = sql.replace("@1", estado.getSigla());
        sql = sql.replace("@2", estado.getNome());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Estado estado)
    {
        String sql = "update from estado set est_sgl = '@1' est_nome = '@2' where est_cod = '@2'";
        sql = sql.replace("@1", estado.getSigla());
        sql = sql.replace("@2", estado.getNome());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean apagar(Estado estado)
    {
        String sql = "delete estado where est_cod"+estado.getCod();
        return Banco.getCon().manipular(sql);
    }
    
    public Estado get(int cod)
    {
        String sql = "select * from estado where est_cod = " + cod;
        
        Estado estado = null;
        
        try
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            
            if(rs.next())
                estado = new Estado(
                        cod, 
                        rs.getString("est_sgl"),
                        rs.getString("est_nome")
                );
        }
        catch(Exception ex)
        {
            System.out.println("ERRO: " + ex.toString());
        }
        
        return estado;
    }
    
    public List<Estado> get(String filtro)
    {
        String sql = "select * from estado";
        
        if(!filtro.isEmpty())
            sql += " where "+filtro;
        
        List<Estado> estados = new ArrayList();
        
        try
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            
            while(rs.next())
                estados.add(new Estado(
                    rs.getInt("est_cod"),
                    rs.getString("est_sgl"),
                    rs.getString("est_nome")
                ));
        }
        catch(Exception ex)
        {
            System.out.println("ERRO: " + ex.toString());
        }
        
        return estados;
    }
}
