package principal.ui.telas;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;
import principal.db.util.Banco;

public class TelaMenuController implements Initializable {

    @FXML
    private BorderPane painelprincipal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }       
    
    @FXML
    private void evtSair(ActionEvent event) {
        System.exit(0);
    }

    @FXML /* ABRIU */
    private void evtCadCidade(ActionEvent event) {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("CadastroCidades.fxml"));
            painelprincipal.setCenter(root);
        }
        catch(Exception e){
            
        }
    }
    
   @FXML /* ABRIU */
    private void evtCadServico(ActionEvent event) {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("CadastroServico.fxml"));
            
            painelprincipal.setCenter(root);
        }
        catch(Exception e){
            
        }
    }

    @FXML
    private void evtCadCliente(ActionEvent event) {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("CadastroCliente.fxml"));
            painelprincipal.setCenter(root);
        }
        catch(Exception e){
            System.out.println("ERRO: " + e.toString());
        }
    }

    @FXML /* ABRIU */
    private void evtCadMarca(ActionEvent event) {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("CadastroMarca.fxml"));
            painelprincipal.setCenter(root);
        }
        catch(Exception e){
            System.out.println("ERRO: " + e.toString());
        }
    }

    @FXML /* ABRIU */
    private void evtCadUnidadeComercial(ActionEvent event) {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("CadastroUnidCom.fxml"));
            painelprincipal.setCenter(root);
        }
        catch(Exception e){
            System.out.println("ERRO: " + e.toString());
        }
    }

    @FXML /* ABRIU */
    private void evtCadTipoProduto(ActionEvent event) {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("CadastroTipoProd.fxml"));
            painelprincipal.setCenter(root);
        }
        catch(Exception e){
            System.out.println("ERRO: " + e.toString());
        }
    }

    @FXML
    private void evtCadProduto(ActionEvent event) {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("CadastroProduto.fxml"));
            painelprincipal.setCenter(root);
        }
        catch(Exception e){
            System.out.println("ERRO: " + e.toString());
        }
    }

//    @FXML
//    private void evtCadEstado(ActionEvent event) {
//        try
//        {
//            Parent root = FXMLLoader.load(getClass().getResource("CadastroEstado.fxml"));
//            painelprincipal.setCenter(root);
//        }
//        catch(Exception e){
//            System.out.println("ERRO: " + e.toString());
//        }
//    }

    @FXML
    private void evtCadOrcamento(ActionEvent event) {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("TelaOrcamento.fxml"));
            painelprincipal.setCenter(root);
        }
        catch(Exception e){
            System.out.println("ERRO: " + e.toString());
        }
    }

    @FXML
    private void evtTelaInicial(ActionEvent event) {
        try
        {
            painelprincipal.setCenter(null);
        }
        catch(Exception e){
            System.out.println("ERRO: " + e.toString());
        }
    }

//    private void evtRelCidades(ActionEvent event) {
//        gerarRelatorio("select * from cidade, estado where cidade.est_cod=estado.est_cod order by cid_nome", 
//                "Relatorios/Simple_Blue.jasper");
//    }

    
    private void gerarRelatorio(String sql,String relat)
    {
        try
        { //sql para obter os dados para o relatorio
          ResultSet rs = Banco.getCon().consultar(sql);
          //implementação da interface JRDataSource para DataSource ResultSet
          JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
          //chamando o relatório
          String jasperPrint =          
              JasperFillManager.fillReportToFile(relat,null, jrRS);
          JasperViewer viewer = new JasperViewer(jasperPrint, false, false);
          viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);//maximizado
          viewer.setTitle("Relatório de Cidades");//titulo do relatório
          viewer.setVisible(true);
        } catch (Exception erro)
        {  
            erro.printStackTrace(); 
        }
    }

    @FXML
    private void evtRelCliente(ActionEvent event) {
        gerarRelatorio("select * from cliente, estado, cidade " +
                        "where estado.est_cod = cliente.est_cod and " +
                        "cidade.cid_cod = cliente.cid_cod", 
                "Relatorios/MyReports/MyReports/rel_cliente.jasper");
    }

    @FXML
    private void evtRelProduto(ActionEvent event) {
        gerarRelatorio("select * from produto, tipo_produto where tipo_produto.tp_cod = produto.tp_cod", 
                "Relatorios/MyReports/MyReports/rel_produto_tipo_produto.jasper");
    }

    @FXML
    private void evtRelOrcamento(ActionEvent event) {
//        gerarRelatorio("select * from orcamento, cliente where orcamento.cli_cod = cliente.cli_cod",
//                "Relatorios/rel_certos/rel_orc_cliente_data.jasper");
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("RelatorioOrcamento.fxml"));
            painelprincipal.setCenter(root);
        }
        catch(Exception e){
            System.out.println("ERRO: " + e.toString());
        }
    }

    @FXML
    private void evtRelServico(ActionEvent event) {
        gerarRelatorio("select * from servico order by ser_descr",
                "Relatorios/MyReports/MyReports/rel_servico_ordernado.jasper");
    }

    @FXML
    private void evtTelaAjuda(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Ajuda.");
        a.setHeaderText("BikeShop Software.\n\n"
                + "ALUNO 1: ANDRESSA HISAE"
                + "RA: 261742078"
                + "\n\nALUNO 2: GABRIEL NAVAS"
                + "\nRA: 261741888");
        a.showAndWait();
    }
}
