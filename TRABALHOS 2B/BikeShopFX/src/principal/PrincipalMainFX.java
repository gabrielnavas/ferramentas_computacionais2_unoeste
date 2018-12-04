package principal;

import principal.db.util.Banco;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class PrincipalMainFX  extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ui/telas/TelaMenu.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("MENU");
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void main(String[] args) 
    {   
        if(!Banco.conectar())
            JOptionPane.showMessageDialog(null, "ERROR: " + Banco.getCon().getMensagemErro());
        else
            launch(args);
    }
}


