package principal.db.dal;

import principal.db.entidade.Cliente;
import principal.db.util.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DALCliente {
    
    public boolean gravar(Cliente cliente)
    {
        String sql = "INSERT INTO cliente " +
                "(cli_sexo, cli_datanasc, cid_cod, est_cod, cli_telefone, cli_email, cli_nome, cli_cpf) " +
                "VALUES ('@1', '@2', '@3', '@4', '@5', '@6', '@7', '@8')";
        
        sql = sql.replace("@8", cliente.getCpf());
        sql = sql.replace("@7", cliente.getNome());
        sql = sql.replace("@1", ""+cliente.getSexo());
        sql = sql.replace("@2", cliente.getDataNasc().toString());
        sql = sql.replace("@3", ""+cliente.getCidade().getCod());
        sql = sql.replace("@4", ""+cliente.getEstado().getCod());
        sql = sql.replace("@5", cliente.getTelefone());
        sql = sql.replace("@6", cliente.getEmail());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Cliente cliente)
    {
        String sql="update cliente set "
                + "cli_cpf = '@1', cli_nome = '@2',"
                + "cli_sexo = '@3', cli_datanasc = '@4',"
                + "cid_cod = '@5', est_cod = '@6',"
                + "cli_telefone = '@7', cli_email = '@8'"
                + "where cli_cod = '@9'";
        
        sql = sql.replace("@1", cliente.getCpf());
        sql = sql.replace("@2", cliente.getNome());
        sql = sql.replace("@3", ""+cliente.getSexo());
        sql = sql.replace("@4", cliente.getDataNasc().toString());
        sql = sql.replace("@5", ""+cliente.getCidade().getCod());
        sql = sql.replace("@6", ""+cliente.getEstado().getCod());
        sql = sql.replace("@7", cliente.getTelefone());
        sql = sql.replace("@8", cliente.getEmail());
        sql = sql.replace("@9", ""+cliente.getCod());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean apagar(Cliente cliente)
    {
        String sql="delete from cliente where cli_cod = "+cliente.getCod();
        return Banco.getCon().manipular(sql);
    }
    
    public Cliente get(int cod)
    {
        String sql = "select * from cliente where cli_cod = " + cod;
        
        Cliente cli = null;
        
        try
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            if(rs.next())
            {
                DALCidade dc = new DALCidade();
                DALEstado de = new DALEstado();
                
                
                cli = new Cliente(
                        cod, 
                        rs.getString("cli_nome"),
                        rs.getString("cli_cpf"),
                        rs.getString("cli_sexo").charAt(0),
                        rs.getDate("cli_datanasc").toLocalDate(),
                        dc.get(rs.getInt("cid_cod")),
                        de.get(rs.getInt("est_cod")),
                        rs.getString("cli_telefone"),
                        rs.getString("cli_email")
                );
            }
                
        }
        catch(Exception ex)
        {
            System.out.println("ERRO: " + ex.toString());
        }
        
        return cli;
    }
    
    public List<Cliente> get(String filtro)
    {
        String sql = "select * from cliente";
        
        if(!filtro.isEmpty())
            sql += " where " + filtro;
        sql += " order by cli_nome";
        
        List<Cliente> clientes=  new ArrayList();
        
        try
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            
            DALCidade dc = new DALCidade();
            DALEstado de = new DALEstado();  
            
            while(rs.next())
            {                
                clientes.add(new Cliente(
                        rs.getInt("cli_cod"), 
                        rs.getString("cli_nome"),
                        rs.getString("cli_cpf"),
                        rs.getString("cli_sexo").charAt(0),
                        rs.getDate("cli_datanasc").toLocalDate(),
                        dc.get(rs.getInt("cid_cod")),
                        de.get(rs.getInt("est_cod")),
                        rs.getString("cli_telefone"),
                        rs.getString("cli_email")
                ));
            }
        }
        catch(Exception ex)
        {
            System.out.println("ERRO: " + ex.toString());
        }
        
        return clientes;
    }
}
