package principal.ui.telas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import principal.MaskFieldUtil;
import principal.db.dal.DALCidade;
import principal.db.dal.DALCliente;
import principal.db.entidade.Cidade;
import principal.db.entidade.Cliente;
import principal.db.util.Banco;

public class CadastroClienteController implements Initializable {

    @FXML
    private TextField txtcpf;
    @FXML
    private TextField txtnome;
    @FXML
    private DatePicker dtpnascimento;
    @FXML
    private TextField txttelefone;
    @FXML
    private TextField txtemail;
    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btAlterar;
    @FXML
    private JFXButton btApagar;
    @FXML
    private JFXButton btConfirmar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private AnchorPane pnDados;
    @FXML
    private VBox pnTabela;
    @FXML
    private JFXTextField txPesquisar;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private TableView<Cliente> tabela;
    @FXML
    private TableColumn<Cliente, Integer> colCod;
    @FXML
    private TableColumn<Cliente, String> colNome;
    @FXML
    private TableColumn<Cliente, String> colCPF;
    @FXML
    private TableColumn<Cliente, Cidade> colCidade;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXComboBox<String> cbbSexo;
    @FXML
    private JFXComboBox<Cidade> cbbCidade;
    @FXML
    private TableColumn<Cliente, String> colSexo;
    @FXML
    private TableColumn<Cliente, LocalDate> colDataNasc;
    @FXML
    private TableColumn<Cliente, String> colTelefone;
    @FXML
    private TableColumn<Cliente, String> colEmail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colCod.setCellValueFactory(new PropertyValueFactory("cod"));
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colCPF.setCellValueFactory(new PropertyValueFactory("cpf"));
        colCidade.setCellValueFactory(new PropertyValueFactory("cidade"));
        colSexo.setCellValueFactory(new PropertyValueFactory("sexo"));
        colDataNasc.setCellValueFactory(new PropertyValueFactory("dataNasc"));
        colTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        
        dtpnascimento.setValue(LocalDate.now());
        
        MaskFieldUtil.cpfField(txtcpf);
        MaskFieldUtil.foneField(txttelefone);
                
        carregaTabela("");
        
    }    
    
    private void alertInformation(String title, String headerText)
    {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setContentText(headerText);
        alert.setTitle(title);
        alert.showAndWait();
    }

    @FXML
    private void evtNovo(ActionEvent event) {
        estadoEdicao();
    }

    @FXML
    private void evtAlterar(ActionEvent event) {
        Cliente c = (Cliente) tabela.getSelectionModel().getSelectedItem();
        txtCodigo.setText("" + c.getCod());
        txtcpf.setText(c.getCpf());
        txtnome.setText(c.getNome());
        if(c.getSexo() == 'M')
        {
            cbbSexo.getSelectionModel().select(0);
            cbbSexo.getSelectionModel().select("Masculino");
        }
        else
        {
            cbbSexo.getSelectionModel().select(0);
            cbbSexo.getSelectionModel().select("Feminino");
        }
        dtpnascimento.setValue(c.getDataNasc());
        
        txttelefone.setText(c.getTelefone());
        txtemail.setText(c.getEmail());
        
        cbbCidade.getSelectionModel().select(0);
        cbbCidade.getSelectionModel().select(c.getCidade());
        
        estadoEdicao();
    }

    @FXML
    private void evtApagar(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        
        a.setContentText("Confirmar exclusão?");
        
        if (a.showAndWait().get() == ButtonType.OK) 
        {
            DALCliente dal = new DALCliente();
            Cliente c = tabela.getSelectionModel().getSelectedItem();
            if(dal.apagar(c))
                carregaTabela("");
            else
            {
                a.setContentText("Existem cadastro vinculados a esse. \nNão foi possível realizar a exclusão.");
                a.showAndWait();
            }
        }
    }
    
    public boolean verificaDados()
    {
        if(txtnome.getText().length() > 0 && txtcpf.getText().length() > 0 &&
                !cbbSexo.getSelectionModel().isSelected(-1) && !cbbCidade.getSelectionModel().isSelected(-1) &&
                txttelefone.getText().length() > 0 && txtemail.getText().length() > 0)
            return true;
        return false;
    }

    @FXML
    private void evtConfirmar(ActionEvent event) {
        int cod;
        try {
            cod = Integer.parseInt(txtCodigo.getText());
        } catch (Exception e) {
            cod = 0;
        }
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(verificaDados())
        {
            Cliente c = new Cliente(
                    cod,
                    txtnome.getText(),
                    txtcpf.getText().replace(".", "").replace("-", ""),
                    cbbSexo.getSelectionModel().getSelectedItem().toString().charAt(0),
                    dtpnascimento.getValue(),
                    cbbCidade.getSelectionModel().getSelectedItem(),
                    cbbCidade.getSelectionModel().getSelectedItem().getEstado(),
                    txttelefone.getText().replace("(", "").replace(")", "").replace("-", ""),
                    txtemail.getText()
            );

            DALCliente dal = new DALCliente();


            if(c.getCod() == 0)
            {
                if(dal.gravar(c))
                {
                    a.setTitle("Sucesso");
                    a.setContentText("Gravado com sucesso!");
                }
                else
                {
                    a.setTitle("Problema");
                    a.setContentText("Problemas ao gravar: " + Banco.getCon().getMensagemErro());
                }
            }
            else
                if(dal.alterar(c))
                {
                    a.setTitle("Sucesso");
                    a.setContentText("Alterado com sucesso!");
                }
                else
                {
                    a.setTitle("Problema");
                    a.setContentText("Problemas ao alterar: " + Banco.getCon().getMensagemErro());
                }

            estadoOriginal();
            carregaTabela("");
            
        }
        else
        {
            a.setTitle("Problema");
            a.setContentText("Complete todos os campos.");
        }
        
        a.showAndWait();
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        estadoOriginal();
    }

    @FXML
    private void evtPesquisar(ActionEvent event) {
        carregaTabela("upper(cli_nome) like '%"+txPesquisar.getText().toUpperCase()+"%'");
    }

    @FXML
    private void evtTabela(MouseEvent event) {
        if (tabela.getSelectionModel().getSelectedIndex() >= 0) {
            btAlterar.setDisable(false);
            btApagar.setDisable(false);
        }
    }
    
    private void estadoOriginal() {
        pnTabela.setDisable(false);
        pnDados.setDisable(true);

        btConfirmar.setDisable(true);
        btCancelar.setDisable(false);
        btApagar.setDisable(true);
        btAlterar.setDisable(true);
        btNovo.setDisable(false);

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
        carregaTabela("");
    }

    private void estadoEdicao() {     
        // carregar os componentes da tela (listbox, combobox, ...)
        // p.e. : carregaEstados();
        pnTabela.setDisable(true);
        pnDados.setDisable(false);
        btConfirmar.setDisable(false);
        btNovo.setDisable(true);
        btApagar.setDisable(true);
        btAlterar.setDisable(true);
        txtcpf.requestFocus();
    }
    
    private void carregaTabela(String filtro) {
        DALCliente dal = new DALCliente();
        List<Cliente> res = dal.get(filtro);
        ObservableList<Cliente> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);
        
        carregarCidades();
        carregarSexos();
        carregarCidades();
    }
    
    private void carregarSexos()
    {
        List<String> lista = new ArrayList<>();
        lista.add("Masculino");
        lista.add("Feminino");
        ObservableList<String> modelo;
        modelo = FXCollections.observableArrayList(lista);
        cbbSexo.setItems(modelo);
    }
    
    private void carregarCidades()
    {
        DALCidade dal = new DALCidade();
        List<Cidade> lista = dal.get("");
        ObservableList<Cidade> modelo;
        modelo = FXCollections.observableArrayList(lista);
        cbbCidade.setItems(modelo);
    }

    @FXML
    private void evtDigitar(KeyEvent event) {
    }
}
