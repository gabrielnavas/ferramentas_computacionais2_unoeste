package principal.ui.telas;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import principal.db.dal.DALOrcamento;
import principal.db.entidade.Orcamento;
import principal.db.entidade.Orcamento.ItemProduto;
import principal.db.entidade.Orcamento.ItemServico;
import principal.db.entidade.Produto;
import principal.db.entidade.Servico;

public class BuscarOrcamentosController implements Initializable {
    
    List<Produto> produtos;
    
    private JFXTextField txtDesc;
    @FXML
    private JFXButton btnPesquisar;
    @FXML
    private TableView<Orcamento> tabela;
    @FXML
    private TableColumn<Produto, Integer> colCod;
    @FXML
    private JFXButton btnSelecionar;
    private VBox pnDados;
    @FXML
    private TableColumn<Orcamento, LocalDate> colData;
    @FXML
    private TableColumn<Orcamento, Integer> colPrazo;
    @FXML
    private TableColumn<Orcamento, Integer> ColCondicPaga;
    @FXML
    private TableColumn<Orcamento, Double> colDesc;
    @FXML
    private TableColumn<Orcamento, Double> colTotal;
    @FXML
    private TableColumn<ItemProduto, Integer> colCodProd;
    @FXML
    private TableColumn<ItemProduto, Produto> colProdProd;
    @FXML
    private TableColumn<ItemProduto, Integer> colQuantProd;
    @FXML
    private TableColumn<ItemServico, Integer> ColCodServico;
    @FXML
    private TableColumn<ItemServico, Servico> ColProduto;
    @FXML
    private TableColumn<ItemServico, Integer> colQuantiServico;
    @FXML
    private TableView<ItemProduto> tabelaItemProduto;
    @FXML
    private TableView<ItemServico> tabelaItemServico;
    @FXML
    private JFXTextField txtCodOrc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        produtos = new ArrayList();
        
        colCod.setCellValueFactory(new PropertyValueFactory("cod"));
        colData.setCellValueFactory(new PropertyValueFactory("data"));
        colPrazo.setCellValueFactory(new PropertyValueFactory("prazo"));
        ColCondicPaga.setCellValueFactory(new PropertyValueFactory("condpgto"));
        colDesc.setCellValueFactory(new PropertyValueFactory("desconto"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
        
        
        colCodProd.setCellValueFactory(new PropertyValueFactory("cod"));
        colProdProd.setCellValueFactory(new PropertyValueFactory("produto"));
        colQuantProd.setCellValueFactory(new PropertyValueFactory("quant"));
        
        
        ColCodServico.setCellValueFactory(new PropertyValueFactory("cod"));
        ColProduto.setCellValueFactory(new PropertyValueFactory("servico"));
        colQuantiServico.setCellValueFactory(new PropertyValueFactory("quant"));
        
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
            TelaOrcamentoController.orcamento = null;
            TelaOrcamentoController.orcamento = tabela.getSelectionModel().getSelectedItem();
            ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        }
    }
    
    private void carregarTabela(String filtro) {
        DALOrcamento dal = new DALOrcamento();
        List<Orcamento> res = dal.get(filtro);
        ObservableList<Orcamento> modelo = FXCollections.observableArrayList(res);
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
        carregarTabela("upper(orc_) like '%"+txtCodOrc.getText().toUpperCase()+"%'");
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }


    @FXML
    private void evtSelecionarOrcamento(MouseEvent event) {
        ObservableList<ItemProduto> modeloProd = FXCollections.observableArrayList(
                tabela.getSelectionModel().getSelectedItem().getListProdutos()
        );
        tabelaItemProduto.setItems(modeloProd);
        
        ObservableList<ItemServico> modeloServ = FXCollections.observableArrayList(
                tabela.getSelectionModel().getSelectedItem().getListServicos()
        );
        tabelaItemServico.setItems(modeloServ);
        
        txtCodOrc.setText(""+tabela.getSelectionModel().getSelectedItem().getCod());
    }
    
}
