package principal.ui.telas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import principal.db.dal.DALServico;
import principal.db.entidade.Produto;
import principal.db.entidade.Servico;

public class BuscarServicosController implements Initializable {
    
    List<Produto> produtos;
    
    @FXML
    private JFXTextField txtDesc;
    @FXML
    private JFXButton btnPesquisar;
    @FXML
    private TableView<Servico> tabela;
    @FXML
    private TableColumn<Servico, Integer> colCod;
    @FXML
    private JFXButton btnSelecionar;
    
    private VBox pnDados;
    @FXML
    private TableColumn<Servico, Double> colPreco;
    @FXML
    private TableColumn<Servico, String> colDesc;
    @FXML
    private JFXButton btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        produtos = new ArrayList();
        
        colCod.setCellValueFactory(new PropertyValueFactory("cod"));
        colDesc.setCellValueFactory(new PropertyValueFactory("descr"));
        colPreco.setCellValueFactory(new PropertyValueFactory("preco"));
        
        
        carregarTabela("");
    }    

    @FXML
    private void evtPesquisar(ActionEvent event) {
        if(txtDesc.getText().length() > 0)
            carregarTabela(txtDesc.getText());
    }

    @FXML
    private void evtSelecionar(ActionEvent event) {
        if(tabela.getSelectionModel().getSelectedItem() != null)
        {
            TelaOrcamentoController.servicoTela = tabela.getSelectionModel().getSelectedItem();
            ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        }
    }
    
    private void carregarTabela(String filtro) {
        DALServico dal = new DALServico();
        List<Servico> res = dal.get(filtro);
        ObservableList<Servico> modelo;
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
    private void evtPesquisar(KeyEvent event) {
        carregarTabela("upper(ser_descr) like '%"+txtDesc.getText().toUpperCase()+"%'");
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
    
}
