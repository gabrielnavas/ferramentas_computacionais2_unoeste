/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class TelaHelpController implements Initializable {

    @FXML
    private Label lbQntdCategorias;
    @FXML
    private Label lbQntdQuestoes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbQntdCategorias.setText("Categorias cadastradas: "+FXMLDocumentController.categorias.size());
        lbQntdQuestoes.setText("Questões cadastradas: "+FXMLDocumentController.quiz.getTotalQuestao());
    }    

    @FXML
    private void evtfechar(ActionEvent event) 
    {
        lbQntdQuestoes.getScene().getWindow().hide();//fecha a janela.
    }
    
}
