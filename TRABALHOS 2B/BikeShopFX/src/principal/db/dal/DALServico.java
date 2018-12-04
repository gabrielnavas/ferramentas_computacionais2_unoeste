package principal.db.dal;

import principal.db.util.Banco;
import principal.db.entidade.Servico;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DALServico 
{
    public boolean gravar(Servico s)
    {
        String sql="insert into servico (ser_descr, ser_preco) values ('@1' ,@2)";
        sql=sql.replace("@1",s.getDescr());
        sql=sql.replace("@2", ""+s.getPreco());
        return Banco.getCon().manipular(sql);
    }
    public boolean alterar(Servico s)
    {
        String sql="update servico set ser_descr= '#1', ser_preco= '#2' where ser_cod= '#3'";
        sql=sql.replace("#1",s.getDescr());
        sql=sql.replace("#2", ""+s.getPreco());
        sql=sql.replace("#3", ""+s.getCod());
        return Banco.getCon().manipular(sql);
    }
    public boolean apagar(Servico s)
    {
        return Banco.getCon().manipular("delete from servico where ser_cod="+s.getCod());
    }
    public Servico get(int cod)
    {
        Servico s=null;
        ResultSet rs = Banco.getCon().consultar("select * from servico where ser_cod="+cod);
        try
        {
          if(rs.next())
          {
            s=new Servico(rs.getInt("ser_cod"),rs.getString("ser_descr"),
                    rs.getDouble("ser_preco"));
          }    
        }catch(Exception e){System.out.println("erro ao pesquisar");}
        
        return s;        
    }
    public List<Servico> get(String filtro)
    {
        List<Servico> list = new ArrayList();
        
        String sql="select * from servico";
        
        if(!filtro.isEmpty())
            sql+=" where " + filtro;
        sql += " order by ser_descr";
        
        try
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            
            while(rs.next())
            {
              list.add(new Servico(
                      rs.getInt("ser_cod"),
                      rs.getString("ser_descr"),
                      rs.getDouble("ser_preco")));
            }    
        }
        catch(Exception e)
        {
            System.out.println("erro ao pesquisar");
        }

        return list;        
    }
}
