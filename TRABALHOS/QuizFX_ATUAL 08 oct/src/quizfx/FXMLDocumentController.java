/*
    --------

    ALUNO 1: ANDRESSA
    RA: 261742078
    
    ALUNO 2: BARBARA DE CARVALHO ALEXANDRE
    RA: 261741608

    ALUNO 3: GABRIEL MIGUEL NAVAS
    RA: 261741888
    
    ---------
*/



package quizfx;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.ListSpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private TextField txnomecat;
    @FXML
    private ComboBox<String> cbbgraduacao;
    @FXML
    private RadioButton rbsimples;
    @FXML
    private ToggleGroup tipo;
    @FXML
    private RadioButton rbverdoufalso;
    @FXML
    private RadioButton rbmultipla;
    @FXML
    private Spinner<Integer> spquant;
    @FXML
    private Spinner<String> sptema;
    @FXML
    private Spinner<String> spgrad;
    @FXML
    private TextField txpergunta;
    @FXML
    private TextField txdica;
    @FXML
    private TextField txresposta;
    
    public static Quiz quiz;
    
    public static List<Categoria> categorias;
    
    @FXML
    private ComboBox<Categoria> cbbcategoria;
    @FXML
    private Button btnajuda;
    @FXML
    private Button btconfirmarquestao;
    @FXML
    private Button btconfirmar;
    @FXML
    private TextArea taalternativas;
    @FXML
    private Button btfechar;
    @FXML
    private RadioButton rbtema;
    @FXML
    private RadioButton rbgraduacao;
    @FXML
    private ToggleGroup praticar;
    @FXML
    private Button btaplicar;
    @FXML
    private RadioButton rbquantidade;
    @FXML
    private Label lbAlternativas;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        quiz = Quiz.carregar();
        if(quiz == null)
            quiz = new Quiz();
        
        categorias = new ArrayList<Categoria>();
        
        configCbbGraduacaoQuestao();
        
        configSpinnerGraduacaoQuestao();
        
        configSpinnerQntdQuestao();
        
        carregarCategoria();
    }    
    
    
    private void carregarCategoria()
    {
        //CARREGAR CATEGORIAS DO OBJETO QUIZ
        for(Questao qs : this.quiz.questoes)
            categorias.add(qs.getCategoria());

        carregarCbbCategoria();
    }

    private void carregarCbbCategoria()
    {
        //CARREGAR CATEGORIAS NO COMBOBOX CATEGORIA
        for(Categoria cat : this.categorias)
            cbbcategoria.getItems().add(cat);
    }
    
    private void configSpinnerQntdQuestao()
    {
        //SPINNER COM INTEIRO => QUANTIDADE DE QUESTAO
        spquant.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10,1));
    }
    
    private void configSpinnerGraduacaoQuestao()
    {
        //SPINNER COM TEXT => GRADUACAO
        ObservableList<String> grad = FXCollections.observableArrayList();
        grad.addAll("0 - Ensino Fundamental","1 - Ensino Medio","2 - Ensino Superior");
        spgrad.valueFactoryProperty().set(new ListSpinnerValueFactory<>(grad));
    }
    
    private void configCbbGraduacaoQuestao()
    {
        //ITEMS DE SELECAO DO COMBO BOX DE GRADUACAO, TELA
        cbbgraduacao.getItems().add("0 - Ensino Fundamental");
        cbbgraduacao.getItems().add("1 - Ensino Medio");
        cbbgraduacao.getItems().add("2 - Ensino Superior");
    
         //ADD ITEMS CATEGORIA 
        for(Categoria c : categorias)
            this.cbbcategoria.getItems().add(c);
    }
    
    private boolean buscarCategoria(Categoria catKey)
    {
        for(Categoria c : this.categorias)
            if(c.equals(catKey))
                return true;
        return false;
    }
    
    @FXML
    private void evtBtConfirmar(ActionEvent event) 
    {
        if(cbbgraduacao.getValue() != null)
        {
            int grad=Integer.parseInt(cbbgraduacao.getValue().substring(0,1));
            if(txnomecat.getText().length() > 0)
            {
                Categoria cat=new Categoria(txnomecat.getText(), grad);

                if(!buscarCategoria(cat))
                {
                    categorias.add(cat);
                    alertInformation("Sucesso!", "Categoria inserida com sucesso!");

                    txnomecat.setText("");
                    cbbgraduacao.getSelectionModel().select(-1);
                    txnomecat.requestFocus();
                }
                else
                    alertError("Problema...", "Categoria já existe");
            }
            else
                alertError("Erro", "Campo tema não pode ficar vazio.");
        }
        else
            alertError("Erro", "Selecione um nível de graduação.");
        
    }

    private void alertError(String title, String headerText)
    {
        Alert alert = new Alert (Alert.AlertType.ERROR);
        alert.setHeaderText(headerText);
        alert.setTitle(title);
        alert.showAndWait();
    }
    
    private void alertInformation(String title, String headerText)
    {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setHeaderText(headerText);
        alert.setTitle(title);
        alert.showAndWait();
    }
    
    @FXML
    private void evtBtSimples(ActionEvent event)
    {
        taalternativas.setVisible(false);
        lbAlternativas.setVisible(false);
    }

    @FXML
    private void evtBtVerdoufalso(ActionEvent event)
    {
        taalternativas.setVisible(false);
        lbAlternativas.setVisible(false);
    }

    @FXML
    private void evtBtMultiplo(ActionEvent event) 
    {
        taalternativas.setVisible(true);
        lbAlternativas.setVisible(true);
    }

    @FXML
    private void evtBtAplicar(ActionEvent event) 
    {
        if(rbgraduacao.isSelected())
        {
            if(spquant.getValue() <= quiz.getTotalQuestaoGraduacao(Integer.parseInt(spgrad.getValue().charAt(0)+"")))
            {
                quiz.montarQuestionario(spquant.getValue(), Integer.parseInt(spgrad.getValue().charAt(0)+""));
                
                iniciarQuestionarioMontado(quiz.getQuestionarioMontado(), "graduacao");
            }
                
            else
                alertError("Problema...", "Questões com tema graduação insuficientes.");
        }
        else if(rbtema.isSelected())
        {
            if(spquant.getValue() <= quiz.getTotalQuestaoTema(sptema.getValue()))
            {
                int quantidade = spquant.getValue();
                String tema = sptema.getValue().toString();
                quiz.montarQuestionario(quantidade, tema);
                
                iniciarQuestionarioMontado(quiz.getQuestionarioMontado(), "tema");
            }
            else
                alertError("Problema...", "Questões com tema insuficientes.");
        }
        else if(rbquantidade.isSelected())
        {
            if(spquant.getValue() <= quiz.getTotalQuestao())
            {
                quiz.montarQuestionario(spquant.getValue());
                
                iniciarQuestionarioMontado(quiz.getQuestionarioMontado(), "quantidade");
            }
            else
                alertError("Problema...", "Questões insuficientes.");
        }
    }
    
    private void iniciarQuestionarioMontado(List<Questao> questMontado, String tipoCategoria)
    {
        if(questMontado != null)
        {
            for(Questao q : questMontado)
            {
                if(q instanceof QRespostaUnica)
                    q = responderQRespostaUnica(q, tipoCategoria);
                
                else if(q instanceof QVerdFalso)
                    q = responderQVerdFalso(q, tipoCategoria);
                    
                else if(q instanceof QMultiplaEscolha)
                    q = responderQMultiplaEscolha(q, tipoCategoria);
                    
            }
            
            DecimalFormat df = new DecimalFormat("0.##");
            String dx = df.format((double) quiz.mostrarResultado());
            alertInformation("Resultado final.", "Resultado final: " + dx + "% de acertos");
            
            btaplicar.requestFocus();
        }
    }
    
    private String textInputDialogQuestao(String title, String headerText, String contentText)
    {
        TextInputDialog dialog = new TextInputDialog();
        
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        dialog.setContentText(contentText);
        dialog.setResizable(true);
        dialog.setWidth(800);
        dialog.setHeight(600);
        
        return dialog.showAndWait().get();
    }
    
    private String configCategoriaTxt(Categoria c, String categoria)
    {
        if(categoria.equals("quantidade"))
            return "nenhuma";

        else if(categoria.equals("tema"))
            return "tema, " + c.getTema();

        else if(categoria.equals("graduacao"))
        {
            if(c.getGraduacao() == Categoria.ensinoFundamental)
                return "graduaçao, ensino fundamental";
            else if(c.getGraduacao() == Categoria.ensinoMedio)
                return "graduaçao, ensino medio";
            else if(c.getGraduacao() == Categoria.ensinoSuperior)
                return "graduaçao, ensino superior";
        }
        
        return categoria;
    }
    
    private Questao responderQMultiplaEscolha(Questao q, String categoria)
    {   
        q.responder(textInputDialogQuestao("Questao Multipla Escolha",
                "Categoria: " + configCategoriaTxt(q.getCategoria(), categoria) + "\n\n"+
                "PERGUNTA: " + q.getPergunta() + "\n\n" +
                "DICA: " + q.getDica() + "\n\n" + 
                "ALTERNATIVAS: " +"\n"+
                ((QMultiplaEscolha)q).getAlternativasln() + "\n",
                "RESPOSTA: "));

        q.analisarResposta();
        alertInformation("Feedback questão.", ((QMultiplaEscolha)q).getRetornoAnalise());

        return q;
    }
    
    private Questao responderQVerdFalso(Questao q, String categoria)
    {
        q.responder(textInputDialogQuestao("Questao Verdadeira ou Falsa.",
                "Categoria: " + configCategoriaTxt(q.getCategoria(), categoria) + "\n\n"+
                "PERGUNTA: "+q.getPergunta() + "\n\n" +
                "DICA: " + q.getDica() + "\n\n" + 
                "ALTERNATIVA: sim/não.",
                "RESPOSTA: "));
        q.analisarResposta();
        
        alertInformation("Feedback questão.", ((QVerdFalso)q).getRetornoAnalise());
        
        return q;
    }
    
    private Questao responderQRespostaUnica(Questao q, String categoria)
    {
        q.responder(textInputDialogQuestao("Questao Única.",
                "Categoria: " + configCategoriaTxt(q.getCategoria(), categoria) + "\n\n"+
                "PERGUNTA: " + q.getPergunta() + "\n\n" +
                "DICA: " + q.getDica()+"\n\n\n",
                "RESPOSTA: "));
        
        q.analisarResposta();
        alertInformation("Feedback questão.", ((QRespostaUnica)q).getRetornoAnalise());

        return q;
    }
    
    @FXML
    private void evtAbaQuestao(Event event) 
    {
        cbbcategoria.setItems(FXCollections.observableArrayList(categorias));
    }

    @FXML
    private void evtAjuda(ActionEvent event) 
    {
        try
        {
            Stage stage = new Stage();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("TelaHelp.fxml")));
            stage.setScene(scene);
            stage.setTitle("Ajuda"); 
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        catch(Exception e)
        {    
            alertInformation("Error tela ajuda.", "ERRO: " + e.getMessage());
        }
    }
    
    @FXML
    private void evtConfirmarQuestao(ActionEvent event) 
    {
        if(cbbcategoria.getValue() == null || 
            txpergunta.getText().length() == 0 ||
            txdica.getText().length() == 0 || 
            txresposta.getText().length() == 0 ||
            (rbmultipla.isSelected() && taalternativas.getText().length() == 0))
                
            alertError("Problema...", "Preencha todos os campos.");
        else
        {
            
            Questao questaoAdd = null;
            Categoria catEscolhida = cbbcategoria.getValue();

            if(rbsimples.isSelected())
            {
                questaoAdd = new QRespostaUnica(txpergunta.getText(), txdica.getText(), txresposta.getText());
                questaoAdd.setCategoria(catEscolhida);
            }
            else if(rbmultipla.isSelected())
            {
                String[] alternativas = taalternativas.getText().split("\n");
                questaoAdd = new QMultiplaEscolha(
                                txpergunta.getText(),
                                txdica.getText(), 
                                txresposta.getText(), 
                                alternativas
                            );

                questaoAdd.setCategoria(catEscolhida);
            }
            else if(rbverdoufalso.isSelected())
            {
                questaoAdd = new QVerdFalso(txpergunta.getText(), txdica.getText(), txresposta.getText());
                questaoAdd.setCategoria(catEscolhida);
            }
            
            
            if(quiz.buscarQuestao(questaoAdd) == null)
            {
                quiz.adQuestao(questaoAdd);
                alertInformation("Sucesso.", "Questão inserida.");
                txpergunta.setText("");
                txdica.setText("");
                txresposta.setText("");
                taalternativas.setText("");
                cbbcategoria.getSelectionModel().select(-1);
                txpergunta.requestFocus();
            }
            else
            {
                alertError("Problema...", "Questao ja existente, mude a categoria ou a pergunta.");
                txpergunta.requestFocus();
            }
            
        }
    }

    @FXML
    private void evtBtFechar(ActionEvent event) {
        quiz.gravar();
        Platform.exit();          
    }

    @FXML
    private void evtRbSomenteQuantidadePraticar(ActionEvent event) {
        spgrad.setVisible(false);
        sptema.setVisible(false);
    }

    @FXML
    private void evtRbTemaOnPraticar(ActionEvent event) {
        spgrad.setVisible(false);
        sptema.setVisible(true);
        
        //SPINNER COM TEXT => TEMAS
        ObservableList<String> tema = FXCollections.observableArrayList();
        for(Questao q : this.quiz.questoes)
            tema.add(q.getCategoria().getTema());
        sptema.valueFactoryProperty().set(new ListSpinnerValueFactory<>(tema));
    }

    @FXML
    private void evtRbGraduacaoPraticar(ActionEvent event) {
        spgrad.setVisible(true);
        sptema.setVisible(false);
    }
}
