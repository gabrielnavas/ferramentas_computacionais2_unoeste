/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.db.dal;

import principal.db.entidade.Marca;
import principal.db.util.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DALMarca {
    
    
    public boolean gravar(Marca marca)
    {
        String sql = "insert into marca (marca_descr) values ('@1')";
        sql = sql.replace("@1", marca.getDescricao());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Marca marca)
    {
        String sql = "update marca set marca_descr = '@1' where marca_cod='@2'";
        sql = sql.replace("@1", marca.getDescricao());
        sql = sql.replace("@2", ""+marca.getCod());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean apagar(Marca marca)
    {
        String sql = "delete from marca where marca_cod = " + marca.getCod();
        return Banco.getCon().manipular(sql);
    }
    
    public Marca get(int cod)
    {
        String sql = "select * from marca where marca_cod = " + cod;
        Marca marca = null;
        
        try
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            
            if(rs.next())
                marca= new Marca(cod, rs.getString("marca_descr"));
        }
        catch(Exception ex)
        {
            System.out.println("ERRO: "+ex.toString());
        }
        
        return marca;
    }
    
    public List<Marca> get(String filtro)
    {
        String sql = "select * from marca";
        
        if(!filtro.isEmpty())
            sql += " where " + filtro;
        sql += " order by marca_descr";
        
        List<Marca> marcas= new ArrayList();
        
        try
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            while(rs.next())
                marcas.add(new Marca(
                        rs.getInt("marca_cod"),
                        rs.getString("marca_descr"))
                );
        }
        catch(Exception ex)
        {
            System.out.println("ERRO: "+ex.toString());
        }
        
        return marcas;
    }
}
