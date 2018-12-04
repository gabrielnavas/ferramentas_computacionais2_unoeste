package principal.ui.telas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import principal.db.dal.DALCliente;
import principal.db.entidade.Cidade;
import principal.db.entidade.Cliente;
import principal.db.entidade.Estado;

public class TabelaConsultaClienteController implements Initializable {

    @FXML
    private JFXButton btnConfirmar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private TableView<Cliente> tabela;
    @FXML
    private TableColumn<Cliente, Integer> colCod;
    @FXML
    private TableColumn<Cliente, String> colNome;
    @FXML
    private TableColumn<Cliente, String> colCpf;
    @FXML
    private TableColumn<Cliente, Character> colSexo;
    @FXML
    private TableColumn<Cliente, LocalDate> colDataNasc;
    @FXML
    private TableColumn<Cliente, Estado> ColEstado;
    @FXML
    private TableColumn<Cliente, Cidade> colCidade;
    @FXML
    private TableColumn<Cliente, String> colTelefone;
    @FXML
    private TableColumn<Cliente, String> colEmail;
    @FXML
    private JFXTextField txtPesquisar;
    @FXML
    private JFXButton btnConfirmar1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCod.setCellValueFactory(new PropertyValueFactory("cod"));
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        colCidade.setCellValueFactory(new PropertyValueFactory("cidade"));
        ColEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        colSexo.setCellValueFactory(new PropertyValueFactory("sexo"));
        colDataNasc.setCellValueFactory(new PropertyValueFactory("dataNasc"));
        colTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        
        carregarTabela("");
    }    

    @FXML
    private void evtConfirmar(ActionEvent event) {
        
        DALCliente dalCli = new DALCliente();
        
        if(tabela.getSelectionModel().getSelectedItem() != null)
        {
            TelaOrcamentoController.clienteTela = tabela.getSelectionModel().getSelectedItem();
            
            if(TelaOrcamentoController.clienteTela == null)
            {
                Alert alert  =new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerta.");
                alert.setContentText("Cliente não encontrado.");
            }
            else
                ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        }
        else
        {
            Alert alert  =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO. ");
            alert.setContentText("É necessário um ID de cliente.");
        }
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
    
    private void carregarTabela(String filtro)
    {
        List<Cliente> lista = new DALCliente().get(filtro);
        ObservableList<Cliente> modelo;
        modelo = FXCollections.observableArrayList(lista);
        tabela.setItems(modelo);
    }

    @FXML
    private void evtPesquisar(ActionEvent event) {
        carregarTabela("upper(cli_nome) like '%"+txtPesquisar.getText().toUpperCase()+"%'");
    }

    @FXML
    private void evtDigitar(KeyEvent event) {
        carregarTabela("upper(cli_nome) like '%"+txtPesquisar.getText().toUpperCase()+"%'");
    }
}
