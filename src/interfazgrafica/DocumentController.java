package interfazgrafica;

import static interfazgrafica.DocumentController.rootP;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Adrian
 */
public class DocumentController implements Initializable{
    
    @FXML private AnchorPane root;
    
    @FXML public static AnchorPane rootP;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("111111");
        if(!InterfazGrafica.isLoaded){
            loadSplashScreen();
        }
        
        rootP = root;
    }
    
    private void loadSplashScreen() {
        System.out.println("111111");
        
        try{
            InterfazGrafica.isLoaded = true;
            StackPane pane = FXMLLoader.load(getClass().getResource("Splash Screen.fxml"));
            root.getChildren().setAll(pane);
            
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3),pane );
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3),pane );
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);
            
            fadeIn.play();
            System.out.println("22222");
            fadeIn.setOnFinished((e)->{
                fadeOut.play();
            });
            
            fadeOut.setOnFinished((e)->{
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("Interfaz General.fxml")));
                    root.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        catch(IOException ex) {
                Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
