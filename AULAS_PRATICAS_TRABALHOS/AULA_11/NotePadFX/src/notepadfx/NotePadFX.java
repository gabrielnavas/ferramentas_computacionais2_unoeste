package notepadfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NotePadFX extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        //stage.initStyle(StageStyle.UTILITY);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}