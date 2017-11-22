/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazgrafica;

import Entidades.Medicina;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import Entidades.Eventualidad;
import Entidades.Residente;
import Utils.EntidadSerializableUtils;
import Entidades.Recordatorio;


import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.mapdb.*;
import com.thoughtworks.xstream.XStream;
import org.mapdb.DBMaker;
import Utils.BDUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;



/**
 *
 * @author Adrian
 */
public class InterfazGrafica extends Application {

    public static Boolean isLoaded = false;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Interfaz General.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Paradise City");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        launch(args);
    }

}
