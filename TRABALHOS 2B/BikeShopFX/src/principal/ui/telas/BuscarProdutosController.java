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
import principal.db.dal.DALProduto;
import principal.db.entidade.Marca;
import principal.db.entidade.Produto;
import principal.db.entidade.TipoProduto;
import principal.db.entidade.UnidadeComercial;

public class BuscarProdutosController implements Initializable {
    
    List<Produto> produtos;
    
    @FXML
    private JFXTextField txtDesc;
    @FXML
    private JFXButton btnPesquisar;
    @FXML
    private TableView<Produto> tabela;
    @FXML
    private TableColumn<Produto, Integer> colCod;
    @FXML
    private TableColumn<Produto, String> colDescr;
    @FXML
    private TableColumn<Produto, TipoProduto> colTipoProd;
    @FXML
    private TableColumn<Produto, UnidadeComercial> colUnidComerc;
    @FXML
    private TableColumn<Produto, Marca> colMarca;
    @FXML
    private TableColumn<Produto, Double> colPreco;
    
    private VBox pnDados;
    @FXML
    private JFXButton btnSelecionar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        produtos = new ArrayList();
        
        colCod.setCellValueFactory(new PropertyValueFactory("cod"));
        colDescr.setCellValueFactory(new PropertyValueFactory("descricao"));
        colTipoProd.setCellValueFactory(new PropertyValueFactory("tipoProduto"));
        colUnidComerc.setCellValueFactory(new PropertyValueFactory("unidadeComercial"));
        colMarca.setCellValueFactory(new PropertyValueFactory("marca"));
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
            TelaOrcamentoController.produtoTela = tabela.getSelectionModel().getSelectedItem();
            ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        }
    }
    
    private void carregarTabela(String filtro) {
        DALProduto dal = new DALProduto();
        List<Produto> res = dal.get(filtro);
        ObservableList<Produto> modelo;
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
        carregarTabela("upper(prod_descricao) like '%"+txtDesc.getText().toUpperCase()+"%'");
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
    
}
