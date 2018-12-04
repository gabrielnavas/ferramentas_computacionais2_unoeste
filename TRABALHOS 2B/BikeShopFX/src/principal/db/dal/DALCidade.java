package principal.db.dal;

import principal.db.entidade.Cidade;
import principal.db.entidade.Estado;
import principal.db.util.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class DALCidade {
    
    public boolean gravar(Cidade cidade)
    {
        String sql = "insert into cidade (cid_cod, cid_nome, est_cod) values (@0,'@1',@2)";
        sql = sql.replace("@0", ""+(Banco.getCon().getMaxPK("cidade", "cid_cod"))+1);
        sql = sql.replace("@1", cidade.getNome());
        sql = sql.replace("@2", ""+cidade.getEstado().getCod());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Cidade cidade)
    {
        String sql = "update cidade set cid_nome = '@1', est_cod = @2 where cid_cod = @3";
        sql = sql.replace("@1", cidade.getNome());
        sql = sql.replace("@2", ""+cidade.getEstado().getCod());
        sql = sql.replace("@3", ""+cidade.getCod());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean apagar(Cidade cidade)
    {
        String sql = "delete cidade where cid_cod = "+cidade.getCod();
        return Banco.getCon().manipular(sql);
    }
    
    public Cidade get(int cod)
    {
        String sql = "select * from cidade where cid_cod = "+cod;
        
        Cidade cidade=null;
        
        try
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            if(rs.next())
            {
                DALEstado dalEstado = new DALEstado();
                Estado estado = dalEstado.get(rs.getInt("est_cod"));
                cidade = new Cidade(
                        cod, 
                        estado, 
                        rs.getString("cid_nome")
                );
            }
                
        }
        catch(Exception ex)
        {
            System.out.println("ERRO: " + ex.toString());
        }
        
        return cidade;
    }
    
    public List<Cidade> get(String filtro)
    {
        String sql = "select * from cidade";
        
        if(!filtro.isEmpty())
            sql += " where "+filtro;
        sql+= " order by cid_nome";
        
        List<Cidade> cidades = new ArrayList();
        
        try
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            Estado estado = null; 
            while(rs.next())
            {
                estado = new DALEstado().get(rs.getInt("est_cod"));
                
                cidades.add(new Cidade(
                        rs.getInt("cid_cod"),
                        estado, 
                        rs.getString("cid_nome")
                ));
            }
        }
        catch(Exception ex)
        {
            System.out.println("ERRO: " + ex.toString());
        }
        
        return cidades;
    }
}
