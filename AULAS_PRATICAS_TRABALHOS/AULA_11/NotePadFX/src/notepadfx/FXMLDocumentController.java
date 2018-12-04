/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepadfx;

import java.awt.Font;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Aluno
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextArea taEdicao;
    @FXML
    private Button btnAumentarFonte;
    @FXML
    private Button btnDiminuirFonte;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnAbrir;
    @FXML
    private Button btnSalvar;
    
    private double fontePadrao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.fontePadrao=taEdicao.getFont().getSize();
    }    
    
    @FXML
    public void evtBtNovo(ActionEvent event)
    {
        taEdicao.setText("");
    }

    @FXML
    private void evtAumentarFonte(ActionEvent event) {
        //taEdicao.setFont(Font.font(++fontePadrao));
    }

    @FXML
    private void evtDiminuiFonte(ActionEvent event) {
        //taEdicao.setFont(Font.font(--fontePadrao));
    }

    @FXML
    private void evtAbrir(ActionEvent event) {
        
        FileChooser fc = new FileChooser();
        fc.setTitle("Selecione um arquivo");
        File arq = fc.showOpenDialog(null);

        
        try
        {
            
            RandomAccessFile raf = new RandomAccessFile(arq, "r");
            
            byte[] dados = new byte[(int)raf.length()];
            raf.readFully(dados);
            String s = new String(dados);
            taEdicao.setText(s);
            raf.close();
            
            ((Stage) (taEdicao.getScene().getWindow())).setTitle(arq.getName());
        }
        catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atenção");
            alert.setHeaderText("Erro ao abrir o arquivo");
        }
        
    }

    @FXML
    private void evtSalvar(ActionEvent event) {
        
        FileChooser fc = new FileChooser();
        fc.setTitle("Selecione a pasta e o nome do arquivo");
        File arq = fc.showSaveDialog(null);

        try
        {
            
            RandomAccessFile raf = new RandomAccessFile(arq, "r");
            raf.writeBytes(taEdicao.getText());
            
            raf.close();
        }
        catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atenção");
            alert.setHeaderText("Erro ao salvar o arquivo." + e.toString());
        }
    }
    
}
