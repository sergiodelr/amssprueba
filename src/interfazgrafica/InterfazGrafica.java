/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazgrafica;

import javafx.application.Application;
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
import java.util.Date;
import java.util.concurrent.ConcurrentMap;



/**
 *
 * @author Adrian
 */
public class InterfazGrafica extends Application {
    
    public static Boolean isLoaded = false;
    
   @Override
    public void start(Stage stage) throws Exception {
        System.out.println("00000");

        Parent root = FXMLLoader.load(getClass().getResource("Interfaz General.fxml"));
       System.out.println("KHA");
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Prototipo Alfa");

        stage.show();
        

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{

        //BDUtils db = new BDUtils("residente.db");
        //System.out.println((String)db.getObject("1"));
        //Eventualidad eventualidad = new Eventualidad("Adan", "a", "Javier", new Date(8,5,5));
        BDUtils db = new BDUtils("reportes.db");
        String a = (String)db.getObject(new Date(8,5,5).toString());
        System.out.println(a);
        launch(args);
        db.closeDB();
        // db.closeDB();
    }

}
