package principal.ui.telas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;
import principal.db.dal.DALCliente;
import principal.db.dal.DALProduto;
import principal.db.entidade.Cidade;
import principal.db.entidade.Cliente;
import principal.db.entidade.Estado;
import principal.db.entidade.Marca;
import principal.db.entidade.Produto;
import principal.db.entidade.TipoProduto;
import principal.db.entidade.UnidadeComercial;
import principal.db.util.Banco;

public class RelatorioOrcamentoController implements Initializable {
    
    private List<DALCliente> clientesList;
    
    private Cliente clienteTela;
    
    private JFXTextField txtDesc;
    @FXML
    private TableView<Cliente> tabela;
    @FXML
    private TableColumn<Produto, Integer> colCod;
    private VBox pnDados;
    @FXML
    private JFXTextField txtClienteNome;
    @FXML
    private TableColumn<Cliente, String> colNome;
    @FXML
    private TableColumn<Cliente, String> colCpf;
    @FXML
    private TableColumn<Cliente, String> colSexo;
    @FXML
    private TableColumn<Cliente, LocalDate> colDataNasc;
    @FXML
    private TableColumn<Cliente, Cidade> colCidade;
    @FXML
    private TableColumn<Cliente, Estado> colEstado;
    @FXML
    private TableColumn<Cliente, String> colTelefone;
    @FXML
    private TableColumn<Cliente, String> colEmail;
    @FXML
    private JFXButton btnGerar;
    @FXML
    private JFXDatePicker dpInicial;
    @FXML
    private JFXDatePicker dpFinal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        clientesList = new ArrayList();
        
        colCod.setCellValueFactory(new PropertyValueFactory("cod"));
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        colSexo.setCellValueFactory(new PropertyValueFactory("sexo"));
        colDataNasc.setCellValueFactory(new PropertyValueFactory("dataNasc"));
        colCidade.setCellValueFactory(new PropertyValueFactory("cidade"));
        colEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        colTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        
        clienteTela = null;
        
        carregarTabela("");
    }    

    @FXML
    private void evtPesquisar(ActionEvent event) {
        if(txtDesc.getText().length() > 0)
            carregarTabela(txtDesc.getText());
        
    }
//
//    private void evtSelecionar(ActionEvent event) {
//        if(tabela.getSelectionModel().getSelectedItem() != null)
//        {
//            TelaOrcamentoController.produtoTela = tabela.getSelectionModel().getSelectedItem();
//            ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
//        }
//    }
    
    private void carregarTabela(String filtro) {
        DALCliente dal = new DALCliente();
        List<Cliente> res = dal.get(filtro);
        ObservableList<Cliente> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);
    }
    
    private void estadoOriginal() {

        ObservableList<Node> componentes = pnDados.getChildren(); //”limpa” os componentes
        for (Node n : componentes) {
            if (n instanceof TextInputControl) // textfield, textarea e htmleditor
            {
                ((TextInputControl) n).setText("");
            }
            if (n instanceof ComboBox) {
                ((ComboBox) n).getItems().clear();
            }
        }
        
        carregarTabela("");
    }

    @FXML
    private void evtDigitar(KeyEvent event) {
        carregarTabela("upper(cli_nome) like '%"+txtClienteNome.getText().toUpperCase()+"%'");
        
        if(txtClienteNome.getText().length() == 0)
            clienteTela=null;
    }
    
    private void evtCancelar(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    private void evtGerar(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        String sql = "select * from orcamento, cliente where"
                            + " cliente.cli_cod = '@1' and"
                            + " orcamento.cli_cod = '@2' and"
                            + " orcamento.orc_data >= '@3' and "
                            + "orcamento.orc_data <= '@4'";
       
        
        if(clienteTela != null )
        {
            if(dpInicial.getValue().isBefore(dpFinal.getValue()))
            {
                sql = sql.replace("@1", ""+clienteTela.getCod());
                sql = sql.replace("@2", ""+clienteTela.getCod());
                sql = sql.replace("@3", dpInicial.getValue().toString());
                sql = sql.replace("@4", dpFinal.getValue().toString());

                gerarRelatorio(sql,
                    "Relatorios/rel_certos/rel_orcamento_cliente_data_2.jasper");
            }
            else
            {
                a.setTitle("Erro...");
                a.setHeaderText("Data inicial não pode ser maior que data final.");
                a.showAndWait();
            }
            
        }
        else if(clienteTela == null)
        {
            sql = sql.replace("cliente.cli_cod = '@1' and", " ");
            sql = sql.replace("'@2'", "cliente.cli_cod");
            sql = sql.replace("'@3'", "orcamento.orc_data");
            sql = sql.replace("'@4'", "orcamento.orc_data");
            
            gerarRelatorio(sql,
                "Relatorios/MyReports/MyReports/rel_orcamento_cliente_data.jasper");
        }    
        
        
    }
    
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
            
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Erro...");
            a.setHeaderText("Não existe");
            a.showAndWait();
        }
    }

    @FXML
    private void evtSelecionarCliente(MouseEvent event) {
        clienteTela = (Cliente) tabela.getSelectionModel().getSelectedItem();
        if(clienteTela != null)
            txtClienteNome.setText(clienteTela.getNome());
    }
}
