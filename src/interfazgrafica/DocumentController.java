package interfazgrafica;

import static interfazgrafica.DocumentController.rootP;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import Entidades.Residente;

import Utils.BDUtils;

/**
 *
 * @author Adrian
 */
public class DocumentController implements Initializable{
    
    @FXML private AnchorPane root;
    
    @FXML public static AnchorPane rootP;

    //Alta de resdientes
    @FXML private  TextField nuevoResidenteNombre;
    @FXML private  TextField nuevoResidenteFdN;
    @FXML private  TextField nuevoResidenteCuarto;
    @FXML private  TextField nuevoResidenteCama;
    @FXML private  TextField nuevoResidenteSdE;
    @FXML private  TextField nuevoResidenteNumSeguro;

    //Perfiles
    @FXML private ChoiceBox choiceBoxResidentes;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*System.out.println("111111");
        if(!InterfazGrafica.isLoaded){
            loadSplashScreen();
        }
        rootP = root;*/
        BDUtils db = new BDUtils("residente.db");

        ConcurrentMap dbMap = db.getMap();
        Set<String > sNombres = dbMap.keySet();
        ArrayList<String> nombres = new ArrayList<String>(sNombres);
        ObservableList<String> olNombres = FXCollections.observableArrayList(nombres);
        choiceBoxResidentes.setItems(olNombres);

    }
    
    private void loadSplashScreen() {
        System.out.println("112111");

        try{
            InterfazGrafica.isLoaded = true;
            StackPane pane = FXMLLoader.load(getClass().getResource("Splash Screen.fxml"));
            System.out.println("112888");
            root.getChildren().setAll(pane);
            System.out.println("1128000999");
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3),pane );
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            System.out.println("11280009978979");
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
                    root.getChildren().setAll(parentContent);
                } catch (IOException ex) {
                    Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        catch(IOException ex) {
                Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void altaIndividual(ActionEvent event) throws ParseException, IOException {

        System.out.println("click");
        if(!nuevoResidenteNombre.getText().isEmpty() && !nuevoResidenteFdN.getText().isEmpty() &&
                !nuevoResidenteCuarto.getText().isEmpty() &&
                !nuevoResidenteCama.getText().isEmpty() &&
                !nuevoResidenteSdE.getText().isEmpty() &&
                !nuevoResidenteNumSeguro.getText().isEmpty()){
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            Date date = format.parse(nuevoResidenteFdN.getText());
            new Residente(nuevoResidenteNombre.getText(), date, Integer.parseInt(nuevoResidenteCuarto.getText()),
                    Integer.parseInt(nuevoResidenteCama.getText()),null, nuevoResidenteSdE.getText(), nuevoResidenteNumSeguro.getText(),1);
            nuevoResidenteNombre.clear();
            nuevoResidenteFdN.clear();
            nuevoResidenteCuarto.clear();
            nuevoResidenteCama.clear();
            nuevoResidenteSdE.clear();
            nuevoResidenteNumSeguro.clear();
        }
    }

    
}
