package principal.db.dal;

import java.sql.ResultSet;
import java.util.ArrayList;
import principal.db.entidade.Orcamento;
import java.util.List;
import principal.db.entidade.Orcamento.ItemProduto;
import principal.db.entidade.Orcamento.ItemServico;
import principal.db.util.Banco;


public class DALOrcamento {
    
    public boolean gravar(Orcamento orc)
    {
        boolean erro = false;
        try
        {
            Banco.getCon().getConnect().setAutoCommit(false); //SEGURANCA
            
            String sql="INSERT INTO orcamento "
                    + "(orc_data, orc_prazo, orc_condpgto, orc_desconto, orc_total, cli_cod) "
                    + "VALUES ('@1', @2, @3, @4, @5, @6)"; 
            
            sql = sql.replace("@1", orc.getData().toString());
            sql = sql.replace("@2", ""+orc.getPrazo());
            sql = sql.replace("@3", ""+orc.getCondpgto());
            sql = sql.replace("@4", ""+orc.getDesconto());
            sql = sql.replace("@5", ""+orc.getTotal());
            sql = sql.replace("@6", ""+orc.getCliente().getCod());

            /* GRAVA ORCAMENTO */
            if(Banco.getCon().manipular(sql))
            {
                int cod_orc = Banco.getCon().getMaxPK("orcamento", "orc_cod");
                
                /* GRAVAR ITEMS SERVICO */
                for(ItemServico itemServico : orc.getListServicos())
                {
                    if(!Banco.getCon().manipular(
                            "INSERT INTO item_servico (orc_cod, ser_cod, its_quant) "
                                    + "VALUES ("+cod_orc+"," 
                                    + itemServico.getServico().getCod()+","
                                    + itemServico.getQuantidade()+")")
                            )
                        erro = true;
                }
                
                /* GRAVA ITEMS PRODUTOS */
                for(ItemProduto itemProd : orc.getListProdutos())
                {
                    if(!Banco.getCon().manipular(
                            "INSERT INTO item_produto (orc_cod, prod_cod, itp_quant) "
                                    + "VALUES ("+cod_orc+","
                                    + itemProd.getProduto().getCod()+","
                                    + itemProd.getQuantidade()+")")
                            )
                        erro = true;
                }
            }
            else
            {
                System.out.println(Banco.getCon().getMensagemErro());
                erro = true;
            }
                
            if(!erro)
                Banco.getCon().getConnect().commit(); // CONFIRMA ALTERACOES
        }
        catch(Exception ex)
        {
            System.out.println("ERROR " + ex.toString());
        }

        return !erro;
    }
    
    public boolean alterar(Orcamento orc)
    {
        //update na tabela orcamento
        //apagar os itens de servico e produto
        //gravar novamente os itens de servico e produto
        
        boolean erro = false;
        
        try
        {
            Banco.getCon().getConnect().setAutoCommit(false);
        
            String sqlOrcUpdate = "UPDATE orcamento SET "
                    + "orc_data = '@1', "
                    + "orc_prazo = @2, "
                    + "orc_condpgto = @3, "
                    + "orc_desconto = @4, "
                    + "orc_total = @5,"
                    + "cli_cod = @6"
                    + " WHERE orc_cod = @7";
            
            sqlOrcUpdate = sqlOrcUpdate.replace("@1", orc.getData().toString());
            sqlOrcUpdate = sqlOrcUpdate.replace("@2", ""+orc.getPrazo());
            sqlOrcUpdate = sqlOrcUpdate.replace("@3", ""+orc.getCondpgto());
            sqlOrcUpdate = sqlOrcUpdate.replace("@4", ""+orc.getDesconto());
            sqlOrcUpdate = sqlOrcUpdate.replace("@5", ""+orc.getTotal());
            sqlOrcUpdate = sqlOrcUpdate.replace("@6", ""+orc.getCliente().getCod());
            sqlOrcUpdate = sqlOrcUpdate.replace("@7", ""+orc.getCod());
            
            /* UPDATE NA TABELA ORCAMENTO */
            if(Banco.getCon().manipular(sqlOrcUpdate))
            {
                //* DELETAR TODOS ITEMSERVICOS *//
                String sqlItSDel = "DELETE FROM item_servico WHERE orc_cod = " + orc.getCod();
                if(Banco.getCon().consultar("select * from item_servico where orc_cod="+orc.getCod()).next() 
                        && !Banco.getCon().manipular(sqlItSDel)) 
                {
                    System.out.println("ERRO: " + Banco.getCon().getMensagemErro());
                    erro = true;
                }
                    
                //* DELETAR TODOS ITEMPRODUTOS *//
                String sqlItPDel = "DELETE FROM item_produto WHERE orc_cod = " + orc.getCod();
                if(Banco.getCon().consultar("select * from item_produto where orc_cod="+orc.getCod()).next() 
                        && !Banco.getCon().manipular(sqlItPDel))
                {
                    System.out.println("ERRO: " + Banco.getCon().getMensagemErro());
                    erro = true;
                }
                
                /* GRAVAR ITEMSSERVICO E ITEMS PRODUTOS */
                if( !erro )
                {
                    //* GRAVAR TODOS ITEMSERVICOS *//
                    for(ItemServico is : orc.getListServicos())
                    {
                        String sqlItSInsert = "INSERT INTO item_servico (orc_cod, ser_cod, its_quant)"
                            + "VALUES (@1, @2, @3)";
                        sqlItSInsert = sqlItSInsert.replace("@1", ""+orc.getCod());
                        sqlItSInsert = sqlItSInsert.replace("@2", ""+is.getServico().getCod());
                        sqlItSInsert = sqlItSInsert.replace("@3", ""+is.getQuantidade());

                        if(!Banco.getCon().manipular(sqlItSInsert))
                        {
                            System.out.println("ERRO: " + Banco.getCon().getMensagemErro());
                            erro = true;
                        }   
                    }

                    //* GRAVAR TODOS ITEMPRODUTOS *//
                    for(ItemProduto ip : orc.getListProdutos())
                    {
                        String sqlItSInsert = "INSERT into item_produto (orc_cod, prod_cod, itp_quant)"
                            + "VALUES (@1, @2, @3)";
                        sqlItSInsert = sqlItSInsert.replace("@1", ""+orc.getCod());
                        sqlItSInsert = sqlItSInsert.replace("@2", ""+ip.getProduto().getCod());
                        sqlItSInsert = sqlItSInsert.replace("@3", ""+ip.getQuantidade());

                        if(!Banco.getCon().manipular(sqlItSInsert))
                        {
                            System.out.println("ERRO: " + Banco.getCon().getMensagemErro());
                            erro = true;
                        }
                    }
                }
            }
            else
            {
                System.out.println("ERRO: " + Banco.getCon().getMensagemErro());
                erro = false;
            }
                
            if(!erro)
                    Banco.getCon().getConnect().commit();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR " + ex.toString());
        }

        return !erro;
    }
    
    public boolean apagar(Orcamento orc)
    { 
        boolean certo = false;
        if(orc.getQuantProdutosList() > 0)
            certo = Banco.getCon().manipular("DELETE FROM item_produto WHERE orc_cod = " + orc.getCod());
        
        if(orc.getQuantServicosList() > 0)
            certo = Banco.getCon().manipular("DELETE FROM item_servico WHERE orc_cod = " + orc.getCod());
            
        certo = Banco.getCon().manipular("DELETE FROM orcamento WHERE orc_cod = " + orc.getCod());
                
        return certo;
    }
    
    public Orcamento get(int cod)
    {
        String sql = "SELECT * FROM orcamento WHERE orc_cod = " + cod;
        
        Orcamento orc = null;
        
        try
        {
            ResultSet rsOrc = Banco.getCon().consultar(sql);
            
            if(rsOrc.next())
            {
                orc = new Orcamento(
                        cod,
                        new DALCliente().get(rsOrc.getInt("cli_cod")),
                        rsOrc.getDate("orc_data").toLocalDate(),
                        rsOrc.getInt("orc_prazo"),
                        rsOrc.getInt("orc_condpgto"),
                        rsOrc.getDouble("orc_desconto"),
                        rsOrc.getDouble("orc_total")
                );
                
                ResultSet rsItP = Banco.getCon()
                                    .consultar("SELECT * FROM item_produto WHERE itp_cod = " + rsOrc.getInt("orc_cod"));
                
                while(rsItP.next())
                    orc.addProduto(
                            rsItP.getInt("itp_quant"), 
                            new DALProduto().get(rsItP.getInt("prod_cod"))
                    );
                
                ResultSet rsItS = 
                        Banco.getCon()
                        .consultar("SELECT * FROM item_servico WHERE its_cod = " + rsOrc.getInt("orc_cod"));
                
                while(rsItS.next())
                    orc.addServico(
                            rsItS.getInt("its_quant"), 
                            new DALServico().get(rsItS.getInt("ser_cod"))
                    );
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
        
        return orc;
    }
    
    public List<Orcamento> get(String filtro)
    {
        String sql = "SELECT * FROM orcamento";
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        sql += " order by orc_data DESC";
        
        List<Orcamento> orcList = new ArrayList();
        
        try
        {
            ResultSet rsOrc = Banco.getCon().consultar(sql);
            
            while(rsOrc.next())
            {
                Orcamento orc = new Orcamento(
                        rsOrc.getInt("orc_cod"),
                        new DALCliente().get(rsOrc.getInt("cli_cod")),
                        rsOrc.getDate("orc_data").toLocalDate(),
                        rsOrc.getInt("orc_prazo"),
                        rsOrc.getInt("orc_condpgto"),
                        rsOrc.getDouble("orc_desconto"),
                        rsOrc.getDouble("orc_total")
                );
                
                //*** PEGA TODOS PRODUTOS DESTE ORCAMENTO ***//
                ResultSet rsItP = 
                        Banco.getCon()
                        .consultar("SELECT * FROM item_produto WHERE orc_cod = " + rsOrc.getInt("orc_cod"));
                
                List<ItemProduto> itemProdutoList = new ArrayList();
                
                while(rsItP.next())
                    itemProdutoList.add(
                            new ItemProduto(
                                    new DALProduto().get(rsItP.getInt("prod_cod")),
                                    rsItP.getInt("itp_quant")
                        )
                    );
                
                orc.setListProdutos(itemProdutoList);
                
                
                
                //*** PEGA TODOS SERVICOS DESTE ORCAMENTO ***//
                ResultSet rsItS = 
                        Banco.getCon()
                        .consultar("SELECT * FROM item_servico WHERE orc_cod = " + rsOrc.getInt("orc_cod"));
                
                List<ItemServico> itemServicoList = new ArrayList();
                
                while(rsItS.next())
                    itemServicoList.add(
                            new ItemServico( 
                                new DALServico().get(rsItS.getInt("ser_cod")),
                                rsItS.getInt("its_quant")
                        )
                    );
                
                orc.setListServicos(itemServicoList);
                
                orcList.add(orc);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
        
        return orcList;
    }
}
