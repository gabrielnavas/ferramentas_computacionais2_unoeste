package principal.ui.telas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import principal.db.dal.DALOrcamento;
import principal.db.entidade.Cliente;
import principal.db.entidade.Orcamento;
import principal.db.entidade.Orcamento.ItemProduto;
import principal.db.entidade.Orcamento.ItemServico;
import principal.db.entidade.Produto;
import principal.db.entidade.Servico;

/**
 *
 * @author Silvio
 */
public class TelaOrcamentoController implements Initializable {
    
    public static Cliente clienteTela; 
    public static Produto produtoTela;
    public static Servico servicoTela;
    
    public static Orcamento orcamento;
    
    @FXML
    private JFXButton btnBuscarProdutos;
    @FXML
    private JFXTextField txtCliente;
    @FXML
    private JFXTextField txtValidade;
    @FXML
    private JFXTextField txtDesconto;
    @FXML
    private JFXComboBox<String> cbbCondicPaga;
    @FXML
    private JFXComboBox<Integer> cbbVezesCondicPagamento;
    @FXML
    private JFXTextField txtProduto;
    @FXML
    private JFXTextField txtServico;
    @FXML
    private JFXTextField txtQntProd;
    @FXML
    private JFXTextField txtQntServico;
    @FXML
    private TableView<ItemProduto> tabelaItemsProdutos;
    @FXML
    private TableView<ItemServico> tabelaItemsServicos;
    @FXML
    private TableColumn<Integer, Integer> colCodProd;
    @FXML
    private TableColumn<Integer, Produto> colProdProd;
    @FXML
    private TableColumn<Integer, Integer> colProdQntd;
    @FXML
    private TableColumn<ItemServico, Integer> colCodServ;
    @FXML
    private TableColumn<ItemServico, Servico> colServServ;
    @FXML
    private TableColumn<ItemServico, Integer> colCodQtnd;
    
    private Object pnDados;
    @FXML
    private JFXTextField txtValorDesconto;
    @FXML
    private JFXTextField txtValorReal;
    
    private void handleButtonAction(ActionEvent event) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCodProd.setCellValueFactory(new PropertyValueFactory("cod"));
        colProdProd.setCellValueFactory(new PropertyValueFactory("produto"));
        colProdQntd.setCellValueFactory(new PropertyValueFactory("quantidade"));
        
        
        colCodServ.setCellValueFactory(new PropertyValueFactory("cod"));
        colServServ.setCellValueFactory(new PropertyValueFactory("servico"));
        colCodQtnd.setCellValueFactory(new PropertyValueFactory("quantidade"));
        
        //MaskFieldUtil.monetaryField(txtValorReal);
        
        clienteTela = null;
        servicoTela = null;
        produtoTela = null;
        
        orcamento = new Orcamento();
        
        carregarCondicoesPagamento();
        
        cbbVezesCondicPagamento.setVisible(false);
        
        txtValorDesconto.setEditable(false);
    }    

    private void carregarCondicoesPagamento()
    {                
        cbbCondicPaga.getItems().add("À vista.");
        cbbCondicPaga.getItems().add("A prazo.");
        cbbCondicPaga.getSelectionModel().select(0);
        
        for(int i=1  ; i <= 10 ; i++)
            cbbVezesCondicPagamento.getItems().add(i);
        cbbVezesCondicPagamento.getSelectionModel().select(-1);
    }
    
    private boolean verificaDados()
    {
        if((orcamento.getQuantProdutosList() > 0 || orcamento.getQuantServicosList() > 0) &&
                txtValidade.getText().length() > 0 && 
                txtDesconto.getText().length() > 0 && 
                txtValorReal.getText().length() > 0)
        {
            if(cbbCondicPaga.getSelectionModel().isSelected(1))
            {
                if( !cbbVezesCondicPagamento.getSelectionModel().isSelected(-1)) 
                    return true;
                else
                    return false;
            }
            return true;
            
        }
        return false;
        
    }
    
    @FXML
    private void evtBuscarCliente(ActionEvent event) {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("TabelaConsultaCliente.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Buscar Cliente"); 
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            if(clienteTela != null)
                txtCliente.setText(clienteTela.getNome());
        }
        catch(Exception ex)
        {
            
        }

    }

    @FXML
    private void evtBuscarProdutos(ActionEvent event) {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("BuscarProdutos.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Buscar Produtos"); 
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            if(produtoTela != null)
                txtProduto.setText(produtoTela.getDescricao());
        }
        catch(Exception ex)
        {
            
        }
    }

    @FXML
    private void evtBuscarServicos(ActionEvent event) {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("BuscarServicos.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Buscar Servicos"); 
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            if(servicoTela != null)
                txtServico.setText(servicoTela.getDescr());
        }
        catch(Exception ex)
        {
            
        }
    }

    @FXML
    private void evtCondicPagamento(ActionEvent event) {
        if(cbbCondicPaga.getSelectionModel().getSelectedIndex() == 1)
            cbbVezesCondicPagamento.setVisible(true);
        else if(cbbCondicPaga.getSelectionModel().getSelectedIndex() == 0)
        {
            cbbVezesCondicPagamento.setVisible(false);
            cbbVezesCondicPagamento.getSelectionModel().select(0);
        }
    }
    
    private void estadoOriginal() {
                
        clienteTela = null;
        txtCliente.setText("");
        
        produtoTela = null;
        txtProduto.setText("");
        txtQntProd.setText("");

        servicoTela = null;
        txtServico.setText("");
        txtQntServico.setText("");
        
        txtValidade.setText("0.0");
        txtDesconto.setText("0.0");
        txtValorDesconto.setText("0.0");
        txtValorReal.setText("0.0");

        cbbCondicPaga.getSelectionModel().select(0);
        cbbVezesCondicPagamento.getSelectionModel().select(-1);
        
        orcamento = null;
        orcamento = new Orcamento();
        
        tabelaItemsProdutos.setItems(null);
        tabelaItemsServicos.setItems(null);
    }
    

    @FXML
    private void evtInserirProdutoSelected(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        
        if(produtoTela != null && txtQntProd.getText().length() > 0)
        {
            if( !orcamento.existsItemProduto(produtoTela) )
            {
                if(txtValorReal.getText().equals(""))
                    txtValorReal.setText("0,0");
                
                orcamento.addProduto(Integer.parseInt(txtQntProd.getText()), produtoTela);
                
                txtProduto.setText("");
                txtQntProd.setText("");
                produtoTela = null;
                
                txtValorReal.setText("" + orcamento.getTotal());
                alterarCampoDesconto();
                
                ObservableList<ItemProduto> modelo = FXCollections.observableArrayList(orcamento.getListProdutos());
                tabelaItemsProdutos.setItems(modelo);
            }
            else
            {
                alert.setTitle("Problema ao tentar inserir.");
                alert.setContentText("Produto já adicionado.");
                alert.showAndWait();
            }
        }
        else
        {
            alert.setTitle("Problema na quantidade.");
            alert.setContentText("Insira uma quantidade.");
            alert.showAndWait();
        }
    }

    @FXML
    private void evtRemoverProdutoSelected(ActionEvent event) {
        
        ItemProduto itp = tabelaItemsProdutos.getSelectionModel().getSelectedItem();
        if(itp != null)
        {      
            if(orcamento.excluirItemProduto(itp.getQuantidade(), itp.getProduto()))
            {
                ObservableList<ItemProduto> modelo = FXCollections.observableArrayList(orcamento.getListProdutos());
                tabelaItemsProdutos.setItems(modelo);
                txtValorReal.setText("" + orcamento.getTotal());
                
                alterarCampoDesconto();
            }
        }
    }

    @FXML
    private void evtinserirServicoSelected(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        
        if(servicoTela != null && txtQntServico.getText().length() > 0)
        {
            if( !orcamento.existsItemServico(servicoTela) )
            {
                if(txtValorReal.getText().equals(""))
                        txtValorReal.setText("0,0");
                
                orcamento.addServico(Integer.parseInt(txtQntServico.getText()), servicoTela);
                
                txtValorReal.setText("" + orcamento.getTotal());
                
                txtServico.setText("");
                txtQntServico.setText("");
                servicoTela = null;
                
                alterarCampoDesconto();
                
                ObservableList<ItemServico> modelo = FXCollections.observableArrayList(orcamento.getListServicos());
                tabelaItemsServicos.setItems(modelo);
            }
                
            else
            {
                alert.setTitle("Problema ao tentar inserir.");
                alert.setContentText("Servico já adicionado.");
                alert.showAndWait();
            }
        }
        else
        {
            alert.setTitle("Problema na quantidade.");
            alert.setContentText("Insira uma quantidade.");
            alert.showAndWait();
        }
    }

    @FXML
    private void evtRemoverServicoSelected(ActionEvent event) {
        if(tabelaItemsServicos.getSelectionModel().getSelectedItem() != null)
        {
            ItemServico its = tabelaItemsServicos.getSelectionModel().getSelectedItem();
            
            if(orcamento.excluirItemServico(its.getQuantidade(), its.getServico()))
            {
                ObservableList<ItemServico> modelo = 
                        FXCollections.observableArrayList(orcamento.getListServicos());
                tabelaItemsServicos.setItems(modelo);
                
                txtValorReal.setText("" + orcamento.getTotal());
                
                alterarCampoDesconto();
            }
        }
    }
    

    @FXML
    private void evtConfirmar(ActionEvent event) {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        
        if(verificaDados() && 
                (orcamento.getQuantProdutosList() > 0 || orcamento.getQuantServicosList() > 0))
        { 
            orcamento.setCliente(clienteTela);
            orcamento.setCondpgto(
                    (cbbCondicPaga.getSelectionModel().isSelected(0)) ? 1 : 
                            cbbVezesCondicPagamento.getSelectionModel().getSelectedIndex()+1
            );
            orcamento.setData(LocalDate.now());
            orcamento.setDesconto( Double.parseDouble(txtDesconto.getText()) );
            orcamento.setPrazo(Integer.parseInt(txtValidade.getText()));
            orcamento.setTotal( Double.parseDouble(txtValorReal.getText() ));
            
            if(orcamento.getCod() == 0)
            {                
                if(new DALOrcamento().gravar(orcamento))
                {
                    alertInfo.setTitle("Sucesso!");
                    alertInfo.setContentText("Orcamento feito com sucesso!!");
                }
                else
                {
                    alertInfo.setTitle("Problema na gravação");
                    alertInfo.setContentText("Problema ao tentar gravar o orcamento.");
                }
            }
            else if(orcamento.getCod() != 0)
            {
                if(new DALOrcamento().alterar(orcamento))
                {
                    alertInfo.setTitle("Sucesso!");
                    alertInfo.setContentText("Orcamento alterado com sucesso!!");

                    estadoOriginal();

                    produtoTela = null;
                    txtProduto.setText("");
                    txtQntProd.setText("");

                    servicoTela = null;
                    txtServico.setText("");
                    txtQntServico.setText("");
                }
                else
                {
                    alertInfo.setTitle("Problema na alteracao");
                    alertInfo.setContentText("Problema ao tentar alterar o orcamento.");
                }
            }
            
            estadoOriginal();
        }
        else
        {
            alertInfo.setTitle("Problema nos campos.");
            alertInfo.setContentText("Preencha todo os campos."
                    + "\nDados com valores negativos ou zerados."
                    + "\nAdicione Produtos ou Servicos.");
            
        }
        
        alertInfo.showAndWait();
        
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        estadoOriginal();
    }

    @FXML
    private void evtSelecionarItemProduto(SortEvent<?> event) {
        
        ItemProduto itemProdutoSelected = tabelaItemsProdutos.getSelectionModel().getSelectedItem();
        
        produtoTela = itemProdutoSelected.getProduto();
        txtProduto.setText(produtoTela.getDescricao());
        txtQntProd.setText("" + itemProdutoSelected.getQuantidade());
    }

    @FXML
    private void evtSelecionarItemServico(SortEvent<?> event) {
        
        ItemServico itemServicoSelected = tabelaItemsServicos.getSelectionModel().getSelectedItem();
        
        servicoTela = itemServicoSelected.getServico();
        txtServico.setText(servicoTela.getDescr());
        txtQntServico.setText("" + itemServicoSelected.getQuantidade());
    }


    @FXML
    private void evtExcluir(ActionEvent event) {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        
        if(orcamento.getCod() != 0)
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            if(a.showAndWait().get() == ButtonType.OK && new DALOrcamento().apagar(orcamento))
            {
                alertInfo.setTitle("Sucesso!");
                alertInfo.setContentText("Orcamento excluído com sucesso!!");
                

                estadoOriginal();

                produtoTela = null;
                txtProduto.setText("");
                txtQntProd.setText("");

                servicoTela = null;
                txtServico.setText("");
                txtQntServico.setText("");
            }
            else
            {
                alertInfo.setTitle("Problema na exclusão");
                alertInfo.setContentText("Problema ao tentar excluir o orcamento.");
            }
        }
        else if(orcamento.getCod() == 0)
        {
            alertInfo.setTitle("Selecionar.");
            alertInfo.setContentText("Selecione um orcamento no botão Alterar/Selecionar.");
        }
        
        alertInfo.showAndWait();
    }

    @FXML
    private void evtAlterar(ActionEvent event) {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("BuscarOrcamentos.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Buscar Orcamento"); 
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            if(orcamento.getCod() != 0)
            {
                clienteTela = orcamento.getCliente();
                txtCliente.setText(clienteTela.getNome());
                if(orcamento.getCondpgto() == 1)
                {
                    cbbCondicPaga.getSelectionModel().select(0);
                    cbbVezesCondicPagamento.getSelectionModel().select(-1);
                    cbbVezesCondicPagamento.setVisible(false);
                }
                else
                {
                    cbbCondicPaga.getSelectionModel().select(1);
                    cbbVezesCondicPagamento.getSelectionModel().select(orcamento.getCondpgto());
                    cbbVezesCondicPagamento.setVisible(true);
                }
                
                txtValidade.setText(""+orcamento.getPrazo());
                txtDesconto.setText(""+orcamento.getDesconto());
                txtValorReal.setText(""+orcamento.getTotal());
                
                alterarCampoDesconto();
                
                ObservableList<ItemServico> modeloServ = 
                        FXCollections.observableArrayList(orcamento.getListServicos());
                tabelaItemsServicos.setItems(modeloServ);
                
                ObservableList<ItemProduto> modeloProd = 
                        FXCollections.observableArrayList(orcamento.getListProdutos());
                tabelaItemsProdutos.setItems(modeloProd);
            }
        }
        catch(Exception ex)
        {
            
        }
    }

    private void alterarCampoDesconto()
    { 
        DecimalFormat df = new DecimalFormat("0.##");
        
        if(txtDesconto.getText().length() > 0 && txtValorReal.getText().length() > 0)
        {
            double vReal = Double.parseDouble(txtValorReal.getText());
            double desconto = Double.parseDouble(txtDesconto.getText());
            double vDesconto = vReal - ((desconto/100) * vReal);

            txtValorDesconto.setText("" + df.format(vDesconto));
        }
        else if(txtDesconto.getText().length() == 0)
        {
            if(txtValorReal.getText().length() > 0)
                txtValorDesconto.setText(txtValorReal.getText());
        }
    }

    @FXML
    private void evtLimparCampos(ActionEvent event) {
        estadoOriginal();
        
    }

    @FXML
    private void evtAddDesconto(KeyEvent event) {
        alterarCampoDesconto();
    }
    
}
