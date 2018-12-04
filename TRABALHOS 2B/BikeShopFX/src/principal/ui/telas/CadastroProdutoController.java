package principal.ui.telas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
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
import principal.MaskFieldUtil;
import principal.db.dal.DALMarca;
import principal.db.dal.DALProduto;
import principal.db.dal.DALTipoProduto;
import principal.db.dal.DALUnidadeComercial;
import principal.db.entidade.Marca;
import principal.db.entidade.Produto;
import principal.db.entidade.TipoProduto;
import principal.db.entidade.UnidadeComercial;
import principal.db.util.Banco;

public class CadastroProdutoController implements Initializable {

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
    private JFXTextField txtPreco;
    @FXML
    private VBox pnTabela;
    @FXML
    private JFXTextField txPesquisar;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private TableView<Produto> tabela;
    @FXML
    private TableColumn<Produto, Integer> colCod;
    @FXML
    private TableColumn<Produto, String> colDescricao;
    @FXML
    private TableColumn<Produto, Double> colPreco;
    @FXML
    private JFXComboBox<TipoProduto> cbbTipo;
    @FXML
    private JFXComboBox<UnidadeComercial> cbbUnidCom;
    @FXML
    private JFXComboBox<Marca> cbbMarca;
    @FXML
    private TableColumn<Produto, TipoProduto> colTipo;
    @FXML
    private TableColumn<Produto, UnidadeComercial> colUnidade;
    @FXML
    private TableColumn<Produto, Marca> colMarca;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCod.setCellValueFactory(new PropertyValueFactory("cod"));
        colDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        colPreco.setCellValueFactory(new PropertyValueFactory("preco"));
        colTipo.setCellValueFactory(new PropertyValueFactory("tipoProduto"));
        colUnidade.setCellValueFactory(new PropertyValueFactory("unidadeComercial"));
        colMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        
//        MaskFieldUtil.monetaryField(txtPreco);
        
        carregaTabela("");
    }    

    @FXML
    private void evtNovo(ActionEvent event) {
        estadoEdicao();
    }

    @FXML
    private void evtAlterar(ActionEvent event) {

        
        Produto c = (Produto) tabela.getSelectionModel().getSelectedItem();
        txCodigo.setText("" + c.getCod());
        txtDescricao.setText(c.getDescricao());
        
        BigDecimal abc = MaskFieldUtil.ConvertStringValueToBigDecimal(""+c.getPreco(), 2);
        
//        txtPreco.setText(c.getPreco()+".0");
        
        txtPreco.setText(""+c.getPreco());
        
        cbbTipo.getSelectionModel().select(0);
        cbbTipo.getSelectionModel().select(c.getTipoProduto());
        
        cbbUnidCom.getSelectionModel().select(0);
        cbbUnidCom.getSelectionModel().select(c.getUnidadeComercial());
        
        cbbMarca.getSelectionModel().select(0);
        cbbMarca.getSelectionModel().select(c.getMarca());
        
        
        estadoEdicao();
    }

    @FXML
    private void evtApagar(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirmar exclusão?");
        if (a.showAndWait().get() == ButtonType.OK) {
            DALProduto dal = new DALProduto();
            Produto c = tabela.getSelectionModel().getSelectedItem();
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
    
    private boolean verificaCampos()
    {
        if(txtDescricao.getText().length() > 0 && txtPreco.getText().length() > 0 &&
                !cbbTipo.getSelectionModel().isSelected(-1) && 
                !cbbUnidCom.getSelectionModel().isSelected(-1) &&
                !cbbMarca.getSelectionModel().isSelected(-1))
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
        
        
        if(verificaCampos())
        {

            Produto p = new Produto(
                    cod,
                    new DALTipoProduto().get(cbbTipo.getSelectionModel().getSelectedItem().getCod()),
                    txtDescricao.getText(),
                    new DALUnidadeComercial().get(cbbUnidCom.getSelectionModel().getSelectedItem().getCod()),
                    new DALMarca().get(cbbMarca.getSelectionModel().getSelectedItem().getCod()),
                    Double.parseDouble(txtPreco.getText())
            );

            DALProduto dal = new DALProduto();

            if(p.getCod() == 0)
            {
                if(dal.gravar(p))
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
                if(dal.alterar(p))
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
       carregaTabela("upper(prod_descricao) like '%"+txPesquisar.getText().toUpperCase()+"%'");
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
        carregarTiposProduto();
        carregarUnid();
        carregarMarcas();
        
        
        DALProduto dal = new DALProduto();
        List<Produto> res = dal.get(filtro);
        ObservableList<Produto> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);
    }
    
    private void carregarTiposProduto(){
        DALTipoProduto dal = new DALTipoProduto();
        List<TipoProduto> res = dal.get("");
        ObservableList<TipoProduto> modelo;
        modelo = FXCollections.observableArrayList(res);
        cbbTipo.setItems(modelo);
    }
    
    private void carregarUnid(){
        DALUnidadeComercial dal = new DALUnidadeComercial();
        List<UnidadeComercial> res = dal.get("");
        ObservableList<UnidadeComercial> modelo;
        modelo = FXCollections.observableArrayList(res);
        cbbUnidCom.setItems(modelo);
        
    }
    private void carregarMarcas(){
        DALMarca dal = new DALMarca();
        List<Marca> res = dal.get("");
        ObservableList<Marca> modelo;
        modelo = FXCollections.observableArrayList(res);
        cbbMarca.setItems(modelo);
    }

    @FXML
    private void evtDigitar(KeyEvent event) {
        carregaTabela("upper(prod_descricao) like '%"+txPesquisar.getText().toUpperCase()+"%'");
    }
}