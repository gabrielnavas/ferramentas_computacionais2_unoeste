package principal.ui.telas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import principal.db.dal.DALEstado;
import principal.db.entidade.Estado;
import principal.db.util.Banco;

public class CadastroEstadoController implements Initializable {

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
    private JFXTextField txCodigo;
    @FXML
    private JFXTextField txNome;
    @FXML
    private VBox pnTabela;
    @FXML
    private JFXTextField txPesquisar;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private TableView<Estado> tabela;
    @FXML
    private TableColumn<Estado, Integer> colCod;
    @FXML
    private TableColumn<Estado, String> colNome;
    @FXML
    private TableColumn<Estado, String> colSigla;
    @FXML
    private JFXTextField txSigla;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCod.setCellValueFactory(new PropertyValueFactory("cod"));
        colSigla.setCellValueFactory(new PropertyValueFactory("sigla"));
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
    
        carregaTabela("");
    }    

    @FXML
    private void evtNovo(ActionEvent event) {
        estadoEdicao();
    }

    @FXML
    private void evtAlterar(ActionEvent event) {
        Estado e = (Estado) tabela.getSelectionModel().getSelectedItem();
        
        txCodigo.setText("" + e.getCod());
        txNome.setText(e.getNome());
        txSigla.setText(e.getSigla());
        
        estadoEdicao();
    }

    @FXML
    private void evtApagar(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirmar exclusão?");
        
        if (a.showAndWait().get() == ButtonType.OK) 
        {
            DALEstado dal = new DALEstado();
            Estado e = tabela.getSelectionModel().getSelectedItem();
            dal.apagar(e);
            
            carregaTabela("");
        }
    }

    @FXML
    private void evtConfirmar(ActionEvent event) {
        int cod;
        try {
            cod = Integer.parseInt(txCodigo.getText());
        } catch (Exception e) {
            cod = 0;
        }
        
        
        Estado c = new Estado(
                cod,
                txSigla.getText(),
                txNome.getText()
        );
        
        DALEstado dal = new DALEstado();
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(c.getCod() == 0)
        {
            if(dal.gravar(c))
                a.setContentText("Estado gravado com sucesso!");
            else
                a.setContentText("Problemas ao gravar: " + Banco.getCon().getMensagemErro());
        }
        else
            if(dal.alterar(c))
                a.setContentText("Estado alterado com sucesso!");
            else
                a.setContentText("Problemas ao alterar: " + Banco.getCon().getMensagemErro());
        
        
        estadoOriginal();
        carregaTabela("");
        a.showAndWait();
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        estadoOriginal();
    }

    @FXML
    private void evtPesquisar(ActionEvent event) {
        carregaTabela("upper(est_nome) like '%" + txPesquisar.getText().toUpperCase()+"%'");
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
        txNome.requestFocus();
    }
    
    private void carregaTabela(String filtro) {
        DALEstado dal = new DALEstado();
        List<Estado> res = dal.get(filtro);
        ObservableList<Estado> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);
    }

    @FXML
    private void evtDigitar(KeyEvent event) {
    }
}
