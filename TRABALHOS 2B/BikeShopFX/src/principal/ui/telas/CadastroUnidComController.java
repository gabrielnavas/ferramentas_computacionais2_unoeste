package principal.ui.telas;

import com.jfoenix.controls.JFXButton;
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
import principal.db.dal.DALUnidadeComercial;
import principal.db.entidade.UnidadeComercial;
import principal.db.util.Banco;

public class CadastroUnidComController implements Initializable {

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
    private JFXTextField txtDescricao;
    @FXML
    private VBox pnTabela;
    @FXML
    private JFXTextField txPesquisar;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private TableView<UnidadeComercial> tabela;
    @FXML
    private TableColumn<?, ?> colCod;
    @FXML
    private TableColumn<?, ?> colDescricao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       colCod.setCellValueFactory(new PropertyValueFactory("cod"));
       colDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
       
       carregaTabela("");
    }    

    @FXML
    private void evtNovo(ActionEvent event) {
        estadoEdicao();
    }

    @FXML
    private void evtAlterar(ActionEvent event) {
        UnidadeComercial c = (UnidadeComercial) tabela.getSelectionModel().getSelectedItem();
        txCodigo.setText("" + c.getCod());
        txtDescricao.setText(c.getDescricao());
        estadoEdicao();
    }

    @FXML
    private void evtApagar(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirmar exclusão?");
        if (a.showAndWait().get() == ButtonType.OK) {
            DALUnidadeComercial dal = new DALUnidadeComercial();
            UnidadeComercial c = tabela.getSelectionModel().getSelectedItem();
            if(dal.apagar(c))
            {
                carregaTabela("");
            }
            else
            {
                a.setContentText("Existem cadastro vinculados a esse. \nNão foi possível realizar a exclusão.");
                a.showAndWait();
            }
        }
    }

    private boolean verificarCampos()
    {
        if(txtDescricao.getText().length() > 0)
            return true;
        return false;
    }
    
    @FXML
    private void evtConfirmar(ActionEvent event) {
        int cod;
        try {
            cod = Integer.parseInt(txCodigo.getText());
        } catch (Exception e) {
            cod = 0;
        }
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(verificarCampos())
        {

            UnidadeComercial c = new UnidadeComercial(
                    cod,
                    txtDescricao.getText()
            );

            DALUnidadeComercial dal = new DALUnidadeComercial();

            if(c.getCod() == 0)
            {
                if(dal.gravar(c))
                {
                    a.setContentText("Gravado com sucesso!");
                    a.setTitle("Sucesso");
                }
                else
                {
                    a.setContentText("Problemas ao gravar: " + Banco.getCon().getMensagemErro());
                    a.setTitle("Problema...");
                }
            }
            else
            {
                if(dal.alterar(c))
                {
                    a.setTitle("Sucesso");
                    a.setContentText("Alterado com sucesso!");
                }
                    
                else
                {    
                    a.setTitle("Problema...");
                    a.setContentText("Problemas ao alterar: " + Banco.getCon().getMensagemErro());
                }
                
            }       
            
            estadoOriginal();
            carregaTabela("");
        }
        else
        {
            a.setTitle("Problema...");
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
        carregaTabela("upper(unitcomerc_descr) like '%"+txPesquisar.getText().toUpperCase()+"%'");
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
        txtDescricao.requestFocus();
    }
    
    private void carregaTabela(String filtro) {
        DALUnidadeComercial dal = new DALUnidadeComercial();
        List<UnidadeComercial> res = dal.get(filtro);
        ObservableList<UnidadeComercial> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);
    }

    @FXML
    private void evtDigitar(KeyEvent event) {
    }
}
