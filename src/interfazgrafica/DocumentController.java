//Tradicional
package interfazgrafica;

import static interfazgrafica.DocumentController.rootP;
import static java.time.temporal.ChronoUnit.DAYS;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import Entidades.*;
import Utils.ResidenteUtils;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import javafx.event.ActionEvent;

import Utils.BDUtils;
import Utils.EntidadSerializableUtils;

import javax.swing.*;


/**
 *
 * @author Adrian
 */
public class DocumentController implements Initializable{

    @FXML private AnchorPane root;

    @FXML public static AnchorPane rootP;

    //Alta de residentes
    private String imgPath = "";
    @FXML private ImageView imagenPreview;
    @FXML private  TextField nuevoResidenteNombre;
    @FXML private  TextField nuevoResidenteFdN;
    @FXML private  TextField nuevoResidenteCuarto;
    @FXML private  TextField nuevoResidenteCama;
    @FXML private  TextField nuevoResidenteSdE;
    @FXML private  TextField nuevoResidenteNumSeguro;
    @FXML private  TextField nuevoResidenteFamiliar;
    @FXML private  TextField nuevoResidenteTelefono;
    @FXML private  TextField nuevoResidenteCondiciones;

    //Medicinas
    @FXML private TextField nMedNombre;
    @FXML private TextField nMedDescripcion;
    @FXML private TextField nMedDosis;
    @FXML private TextField nMedDuracion;
    @FXML private TextField nMedPrecauciones;
    @FXML private TextField nMedRestantes;

    //Perfiles
    @FXML private ChoiceBox choiceBoxResidentes;
    @FXML private  TextField residenteNombre;
    @FXML private  TextField residenteFdN;
    @FXML private  TextField residenteCuarto;
    @FXML private  TextField residenteCama;
    @FXML private  TextField residenteSdE;
    @FXML private  TextField residenteNumSeguro;
    @FXML private  TextField descripcionEventualidad;
    @FXML private  TextField horaEventualidad;
    @FXML private  TextField fechaEventualidad;
    @FXML private  TextField atendidoPorEventualidad;
    @FXML private TextField residenteCondiciones;
    @FXML private ImageView imgResidente;
    private String residenteActual = "";
    //Reportes
    @FXML private DatePicker diaReporte;
    @FXML private TableView tablaReporte;
    @FXML private TableColumn numeroEventualidad = new TableColumn("Número de eventualidad");
    @FXML private TableColumn residente = new TableColumn("Residente");
    @FXML private TableColumn descripcion = new TableColumn("Descripción");
    @FXML private TableColumn hora = new TableColumn("Hora");
    @FXML private TableColumn atendidoPor = new TableColumn("Atendido por");

    //Eventualidades
    @FXML private TableView tablaEventualidades;
    @FXML private TableColumn eveDescripcion = new TableColumn("Descripcion");
    @FXML private TableColumn eveHora = new TableColumn("Hora");
    @FXML private TableColumn eveAtendidoPor = new TableColumn("Atendido por");
    @FXML private TableColumn eveFecha = new TableColumn("Fecha");

    //Medicinas
    @FXML private TableView tablaMedicina;
    @FXML private TableColumn medNombre = new TableColumn("Nombre");
    @FXML private TableColumn medDescripcion = new TableColumn("Descripcion");
    @FXML private TableColumn medDosis = new TableColumn("Dosis");
    @FXML private TableColumn medPrecauciones = new TableColumn("Precacuciones");
    @FXML private TableColumn medRestantes = new TableColumn("Restantes");
    @FXML private TableColumn medDuracion = new TableColumn("Duracion");

    //Familiares
    @FXML private TableView tablaFamiliares;
    @FXML private TableColumn famNombre = new TableColumn("Nombre");
    @FXML private TableColumn famTelefono = new TableColumn("Teléfono");
    @FXML private TextField nFamNombre;
    @FXML private TextField nFamTelefono;

    //Notificaciones
    @FXML private TableView tablaNotificacion;
    @FXML private TextField diasConsulta;
    @FXML private TableColumn conResidente = new TableColumn("Residente");
    @FXML private TableColumn conMedicamento = new TableColumn("Medicamento");
    @FXML private TableColumn conCuarto = new TableColumn("Cuarto");
    @FXML private TableColumn conCama = new TableColumn("Cama");
    @FXML private TableColumn conDiasRestantes = new TableColumn("Días Restantes");
    @FXML private TableColumn conContacto = new TableColumn("Contacto");
    @FXML private TableColumn conTelefono = new TableColumn("Telefono");


    public void actualizarMedicinas(){
        mostrarMedicinas();
        nMedNombre.clear();
        nMedDescripcion.clear();
        nMedDosis.clear();
        nMedPrecauciones.clear();
        nMedDuracion.clear();
        nMedRestantes.clear();
    }

    public void initializeUtils(){
        System.out.println("initializeUtils");

        BDUtils db = new BDUtils("residentes.db");
        Map<String,String> dbMap = db.getMap();
        Set<String > sNombres = dbMap.keySet();
        ArrayList<String> nombres = new ArrayList<String>(sNombres);
        ObservableList<String> olNombres = FXCollections.observableArrayList(nombres);
        choiceBoxResidentes.setItems(olNombres);
        db.closeDB();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*System.out.println("111111");
        if(!InterfazGrafica.isLoaded){
            loadSplashScreen();
        }
        rootP = root;*/
        initializeUtils();

        diaReporte.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("diaReporte: handle");
                LocalDate fecha = diaReporte.getValue();
                mostrarReportes(fecha);
                //mostrarMedicinas(); //para que?
            }
        });

        diasConsulta.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try{
                    int a = Integer.parseInt(diasConsulta.getText());
                    consultaGeneral(a);
                }catch (NumberFormatException e) {
                    //Not an integer
                    System.out.println("Ingrese un número");
                    JOptionPane.showMessageDialog(null, "Ingrese un número");
                }
            }
        });


        choiceBoxResidentes.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                residenteActual = (String) choiceBoxResidentes.getItems().get((Integer) number2);
                mostrarInfo(residenteActual);
            }
        });
    }


    private void mostrarReportes(LocalDate fecha) {
        BDUtils db = new BDUtils("reportes.db");
        System.out.println(fecha.toString());
        LocalDate date = fecha;
        Map<String, String> map = db.getMap();
        System.out.println("todas las fechas son");
        for(Map.Entry<String,String> entry :map.entrySet()){
            System.out.print(entry.getKey()+" ");
        }
        System.out.println();
        Reporte reporte;
        try {
            reporte = (Reporte) EntidadSerializableUtils.getEntidadFromXml(
                    (String) db.getObject(date.toString()));
        }catch(NullPointerException e){
            tablaReporte.getItems().clear();
            tablaReporte.refresh();
            db.closeDB(); //esto es necesario?
            return;
        }
        db.closeDB();
        ObservableList<Eventualidad> eventualidades = FXCollections.observableArrayList(reporte.getEventualidads());
        System.out.println("Tamaño de reportes" + eventualidades.size());
        tablaReporte.setEditable(true);
        hora.setCellValueFactory(new PropertyValueFactory<Eventualidad, String>("hora")); //NOMBRES DE VARIABLES
        residente.setCellValueFactory(new PropertyValueFactory<Eventualidad, String>("residente"));
        atendidoPor.setCellValueFactory(new PropertyValueFactory<Eventualidad, String>("encargado"));
        descripcion.setCellValueFactory(new PropertyValueFactory<Eventualidad, String>("descripcion"));
        tablaReporte.setItems(eventualidades);

    }

    private void mostrarInfo(String nombreResidente) {
        mostrarEventualidades();
        mostrarMedicinas();
        mostrarFamiliares();

        BDUtils db = new BDUtils("residentes.db");
        String objRes = (String) db.getObject(nombreResidente);
        db.closeDB();
        Residente res = (Residente) EntidadSerializableUtils.getEntidadFromXml(objRes);
        Period period = Period.between(res.getFechaDeNacimiento(), LocalDate.now());
        String fechaDeNacimiento = String.valueOf(period.getYears());
        residenteFdN.setText(fechaDeNacimiento);
        residenteCuarto.setText(Integer.toString(res.getNumCuarto()));
        residenteCama.setText(Integer.toString(res.getNumCama()));
        residenteSdE.setText(res.getServicioEmergencia());
        residenteNumSeguro.setText(res.getNumSeguro());
        residenteCondiciones.setText(res.getCondiciones());
        try {
            imgResidente.setImage(SwingFXUtils.toFXImage(res.getImage(), null));
        }catch(Exception e){
            imgResidente.setImage(new Image(new File ("Old Man.jpg").toURI().toString()));
            System.out.println("excepcion: sin imagen");
        }

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

        System.out.println("altaIndividual");
        if(!nuevoResidenteNombre.getText().isEmpty() && !nuevoResidenteFdN.getText().isEmpty() &&
                !nuevoResidenteCuarto.getText().isEmpty() &&
                !nuevoResidenteCama.getText().isEmpty() &&
                !nuevoResidenteSdE.getText().isEmpty() &&
                !nuevoResidenteNumSeguro.getText().isEmpty() &&
                !nuevoResidenteFamiliar.getText().isEmpty() &&
                !nuevoResidenteTelefono.getText().isEmpty()){
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(nuevoResidenteFdN.getText());
            if(!imgPath.isEmpty()) {
                Residente residente = new Residente(nuevoResidenteNombre.getText(), date, Integer.parseInt(nuevoResidenteCuarto.getText()),
                        Integer.parseInt(nuevoResidenteCama.getText()), null, nuevoResidenteSdE.getText(), nuevoResidenteNumSeguro.getText(), 1, imgPath, nuevoResidenteCondiciones.getText(), nuevoResidenteFamiliar.getText(), nuevoResidenteTelefono.getText());
            }
            else {
                Residente residente = new Residente(nuevoResidenteNombre.getText(), date, Integer.parseInt(nuevoResidenteCuarto.getText()),
                        Integer.parseInt(nuevoResidenteCama.getText()),null, nuevoResidenteSdE.getText(), nuevoResidenteNumSeguro.getText(),1, "Old Man.jpg", nuevoResidenteCondiciones.getText(), nuevoResidenteFamiliar.getText(), nuevoResidenteTelefono.getText());
            }

            nuevoResidenteNombre.clear();
            nuevoResidenteFdN.clear();
            nuevoResidenteCuarto.clear();
            nuevoResidenteCama.clear();
            nuevoResidenteSdE.clear();
            nuevoResidenteNumSeguro.clear();
            nuevoResidenteFamiliar.clear();
            nuevoResidenteTelefono.clear();
            nuevoResidenteCondiciones.clear();

            imagenPreview.setImage(new Image(new File ("Old Man.jpg").toURI().toString()));
            initializeUtils();
        }else{
            System.out.println("Llene todos los campos");
            JOptionPane.showMessageDialog(null, "Llene todos los campos");
        }
    }
    @FXML
    void agregarEventualidad(ActionEvent event) throws ParseException {
        if(!descripcionEventualidad.getText().isEmpty() &&
                !horaEventualidad.getText().isEmpty() &&
                !fechaEventualidad.getText().isEmpty() &&
                !atendidoPorEventualidad.getText().isEmpty()){
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            LocalDate date = LocalDate.parse(fechaEventualidad.getText());

            Eventualidad eventualidad = new Eventualidad(atendidoPorEventualidad.getText(),descripcionEventualidad.getText(),residenteActual,date,horaEventualidad.getText());

            BDUtils db = new BDUtils("residentes.db");
            String objRes = (String) db.getObject(residenteActual);
            db.closeDB();
            Residente res = (Residente) EntidadSerializableUtils.getEntidadFromXml(objRes);
            res.addEventualidad(eventualidad);
            ResidenteUtils.modifyResidente(res);
            mostrarEventualidades();
            descripcionEventualidad.clear();
            horaEventualidad.clear();
            fechaEventualidad.clear();
            atendidoPorEventualidad.clear();
        }else{
            System.out.println("Llene todos los campos");
            JOptionPane.showMessageDialog(null, "Llene todos los campos");
        }
    }

    @FXML
    void mostrarEventualidades(){
        BDUtils db = new BDUtils("residentes.db");
        String objRes = (String) db.getObject(residenteActual);
        System.out.println("mostrar eventualidades " + residenteActual);
        db.closeDB();
        Residente res = (Residente) EntidadSerializableUtils.getEntidadFromXml(objRes);
        ObservableList<Eventualidad> eventualidades = FXCollections.observableArrayList(res.getEventualidades());
        System.out.println("Numero de eventualidades " + eventualidades.size());
        eveHora.setCellValueFactory(new PropertyValueFactory<Eventualidad, String>("hora"));
        eveFecha.setCellValueFactory(new PropertyValueFactory<Eventualidad, String>("fechaDeEventualidad"));
        eveAtendidoPor.setCellValueFactory(new PropertyValueFactory<Eventualidad, String>("encargado"));
        eveDescripcion.setCellValueFactory(new PropertyValueFactory<Eventualidad, String>("descripcion"));
        tablaEventualidades.setItems(eventualidades);
    }

    @FXML
    void diaAnterior(ActionEvent event){
        diaReporte.setValue(diaReporte.getValue().minusDays(1L));
        tablaReporte.getItems().clear();
        mostrarReportes(diaReporte.getValue());
    }

    @FXML
    void diaSiguiente(ActionEvent event){
        diaReporte.setValue(diaReporte.getValue().plusDays(1L));
        tablaReporte.getItems().clear();
        mostrarReportes(diaReporte.getValue());
    }

    @FXML
    void mostrarMedicinas(){
        BDUtils db = new BDUtils("residentes.db");
        String objRes = (String)db.getObject(residenteActual);
        db.closeDB();
        Residente res = (Residente)EntidadSerializableUtils.getEntidadFromXml(objRes);
        ObservableList<Medicina> medicinas = FXCollections.observableArrayList(res.getMedicinas());
        medDescripcion.setCellValueFactory(new PropertyValueFactory<Medicina, String>("descripcion"));

        medDescripcion.setCellFactory(TextFieldTableCell.forTableColumn());
        medDescripcion.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Medicina, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Medicina, String> t) {
                        System.out.println("commit");
                        Medicina m = (Medicina) t.getTableView().getItems().get(
                                t.getTablePosition().getRow());
                        m.setDescripcion(t.getNewValue());
                        System.out.println(m.getDescripcion());
                        res.deleteMedicina(m.getNombre());
                        res.addMedicina(m);
                        ResidenteUtils.modifyResidente(res);
                    }
                }
        );

        medDosis.setCellValueFactory(new PropertyValueFactory<Medicina, String>("dosis"));

        medDosis.setCellFactory(TextFieldTableCell.forTableColumn());
        medDosis.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Medicina, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Medicina, String> t) {
                        System.out.println("commit");
                        Medicina m = (Medicina) t.getTableView().getItems().get(
                                t.getTablePosition().getRow());
                        m.setDosis(t.getNewValue());
                        res.deleteMedicina(m.getNombre());
                        res.addMedicina(m);
                        ResidenteUtils.modifyResidente(res);
                    }
                }
        );

        medNombre.setCellValueFactory(new PropertyValueFactory<Medicina, String>("nombre"));
        medPrecauciones.setCellValueFactory(new PropertyValueFactory<Medicina, String>("precauciones"));

        medPrecauciones.setCellFactory(TextFieldTableCell.forTableColumn());
        medPrecauciones.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Medicina, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Medicina, String> t) {
                        System.out.println("commit");
                        Medicina m = (Medicina) t.getTableView().getItems().get(
                                t.getTablePosition().getRow());
                        m.setPrecauciones(t.getNewValue());
                        res.deleteMedicina(m.getNombre());
                        res.addMedicina(m);
                        ResidenteUtils.modifyResidente(res);
                    }
                }
        );

        medDuracion.setCellValueFactory(new PropertyValueFactory<Medicina, String>("duracionDias"));
        medRestantes.setCellValueFactory(new PropertyValueFactory<Medicina, String>("cantidad"));
        tablaMedicina.setItems(medicinas);
    }
    @FXML
    void agregarMedicina(ActionEvent event){

        if(nMedNombre.getText().isEmpty() || nMedDescripcion.getText().isEmpty() || nMedDuracion.getText().isEmpty() ||
                nMedDosis.getText().isEmpty() || nMedPrecauciones.getText().isEmpty() || nMedRestantes.getText().isEmpty()){
            System.out.println("Llene todos los campos");
            JOptionPane.showMessageDialog(null, "Llene todos los campos");
        }else {

            Medicina medicina = new Medicina(nMedNombre.getText(), nMedDescripcion.getText(),
                    Integer.parseInt(nMedRestantes.getText()), nMedPrecauciones.getText(),
                    Integer.parseInt(nMedDuracion.getText()), nMedDosis.getText(), residenteActual); //quite fecha de caducidad del constructor
            BDUtils db = new BDUtils("residentes.db");
            String objRes = (String) db.getObject(residenteActual);
            db.closeDB();
            Residente res = (Residente) EntidadSerializableUtils.getEntidadFromXml(objRes);
            System.out.println(medicina.getNombre());
            res.addMedicina(medicina);
            ResidenteUtils.modifyResidente(res);

            //actualizar y limpiar (Se usará en Modificar y Eliminar)
            actualizarMedicinas();

        }

    }

    @FXML
    void mostrarFamiliares(){
        BDUtils db = new BDUtils("residentes.db");
        String objRes = (String) db.getObject(residenteActual);
        System.out.println("mostrar familiares " + residenteActual);
        db.closeDB();
        Residente res = (Residente) EntidadSerializableUtils.getEntidadFromXml(objRes);

        Map<String,String> contactos = res.getContactos();

        List<String> nombres = new ArrayList<String>(contactos.keySet());
        List<String> telefonos = new ArrayList<String>(contactos.values());
        List<Familiar> familiares = new ArrayList<Familiar>();

        for(int i = 0; i < nombres.size() && i < telefonos.size(); i++){
            Familiar fam = new Familiar(nombres.get(i), telefonos.get(i));
            familiares.add(fam);
            System.out.println(fam.getNombre()+" "+fam.getTelefono());
        }

        ObservableList<Familiar> familiaresTabla = FXCollections.observableArrayList(familiares);
        famNombre.setCellValueFactory(new PropertyValueFactory<Familiar, String>("nombre"));
        famTelefono.setCellValueFactory(new PropertyValueFactory<Familiar, String>("telefono"));
        tablaFamiliares.setItems(familiaresTabla);

    }


    @FXML
    void altaMasiva(ActionEvent event){
        System.out.println("file Browser");
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files","*.csv"));
        File f = fc.showOpenDialog(null);

        if(f != null){
            //System.out.println(f.getAbsolutePath());
            String aux = f.getAbsolutePath();
            System.out.println(aux);
            try{
                ResidenteUtils.altaMasiva(aux);
            }catch(IOException e){
                System.out.println("Error");
            }
        }
        initializeUtils(); // para que se updatee la tabla de residentes en Perfiles
    }

    @FXML
    void consultaGeneral(int a){
        System.out.println("consulta de medicinas dias = " + a);

        BDUtils db = new BDUtils("residentes.db");

        Map<String,String> dbMap = db.getMap();
        List<String> residentesXML = new ArrayList<String>(); //XML de los residentes
        String aux, aux2;

        for(Map.Entry<String,String> entry :dbMap.entrySet()){
            aux = (String)entry.getValue();
            residentesXML.add(aux);
        }

        db.closeDB();

        Residente res;
        Medicina med;
        List<Medicina> medicinas = new ArrayList<>();
        List<Notificacion> notificaciones = new ArrayList<>();

        for(int i= 0; i<residentesXML.size(); i++){
            aux = residentesXML.get(i);
            res = (Residente) EntidadSerializableUtils.getEntidadFromXml(aux);

            medicinas = res.getMedicinas();
            //System.out.println(res.getNombre() + " " + medicinas.size());
            for(int j = 0; j < medicinas.size(); j++){
                med = medicinas.get(j);

                long diasRestantes = med.getDiasRestantes();

                if(diasRestantes <= a){ //si se acaba antes de 'a' días
                    System.out.println();
                    aux = res.getFirstContacto();
                    aux2 = res.getFirstTelefono();
                    Notificacion not = new Notificacion(res.getNombre(), med.getNombre(), res.getNumCama(), res.getNumCuarto(), diasRestantes, aux, aux2);
                    notificaciones.add(not);
                }
            }
        }

        ObservableList<Notificacion> notTabla = FXCollections.observableArrayList(notificaciones);
        tablaNotificacion.setEditable(true);
        conResidente.setCellValueFactory(new PropertyValueFactory<Notificacion, String>("residente")); //NOMBRES DE VARIABLES
        conMedicamento.setCellValueFactory(new PropertyValueFactory<Notificacion, String>("medicamento"));
        conCuarto.setCellValueFactory(new PropertyValueFactory<Notificacion, Integer>("cuarto"));
        conCama.setCellValueFactory(new PropertyValueFactory<Notificacion, Integer>("cama"));
        conDiasRestantes.setCellValueFactory(new PropertyValueFactory<Notificacion, Long>("diasRestantes"));
        conContacto.setCellValueFactory(new PropertyValueFactory<Notificacion, String>("contacto"));
        conTelefono.setCellValueFactory(new PropertyValueFactory<Notificacion, String>("telefono"));
        tablaNotificacion.setItems(notTabla);

    }
    @FXML
    void seleccionarImagen(){
        System.out.println("file Browser");
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("ImageFiles","*.jpg","*.png", "*.jpeg"));
        File f = fc.showOpenDialog(null);

        if(f != null){
            //System.out.println(f.getAbsolutePath());
            String aux = f.getAbsolutePath();
            System.out.println(aux);
            Image image = new Image(new File(aux).toURI().toString());
            imgPath = aux;
            imagenPreview.setImage(image);
        }
    }

    @FXML
    void borrarMedicina(){
        Medicina medicina = (Medicina) tablaMedicina.getSelectionModel().getSelectedItem();
        BDUtils db = new BDUtils("residentes.db");
        String objRes = (String) db.getObject(residenteActual);
        db.closeDB();
        Residente res = (Residente) EntidadSerializableUtils.getEntidadFromXml(objRes);
        res.deleteMedicina(medicina.getNombre());
        ResidenteUtils.modifyResidente(res);
        actualizarMedicinas();
    }


    @FXML
    void agregarFamiliar(){
        System.out.println("hola");
        if(nFamNombre.getText().isEmpty() || nFamTelefono.getText().isEmpty()){ //alguno esta vacio
            System.out.println("Llene todos los campos");
            JOptionPane.showMessageDialog(null, "Llene todos los campos");
        }else{

            BDUtils db = new BDUtils("residentes.db");
            String objRes = (String)db.getObject(residenteActual);
            db.closeDB();

            Residente res = (Residente)EntidadSerializableUtils.getEntidadFromXml(objRes);
            res.addContacto(nFamNombre.getText(), nFamTelefono.getText());
            ResidenteUtils.modifyResidente(res);

            mostrarFamiliares();
            nFamNombre.clear();
            nFamTelefono.clear();
        }
    }

    @FXML
    void modificarResidente(){
        if(residenteCuarto.getText().isEmpty() || residenteCama.getText().isEmpty() || residenteSdE.getText().isEmpty() ||
                residenteNumSeguro.getText().isEmpty() || residenteCondiciones.getText().isEmpty()    ){ //alguno esta vacio
            System.out.println("Llene todos los campos");
            JOptionPane.showMessageDialog(null, "Llene todos los campos");
        }else{

            BDUtils db = new BDUtils("residentes.db");
            String objRes = (String)db.getObject(residenteActual);
            db.closeDB();

            Residente res = (Residente)EntidadSerializableUtils.getEntidadFromXml(objRes);
            res.setNumCuarto( Integer.parseInt(residenteCuarto.getText()) );
            res.setNumCama( Integer.parseInt(residenteCama.getText()) );
            res.setServicioEmergencia(residenteSdE.getText());
            res.setNumSeguro(residenteNumSeguro.getText() );
            res.setCondiciones(residenteCondiciones.getText() );
            ResidenteUtils.modifyResidente(res);
        }
    }

    @FXML
    void bajaResidente(){
        if(residenteActual != ""){
            System.out.println("dar de baja a " + residenteActual);
            BDUtils db = new BDUtils("residentes.db");


            residenteActual = "";
            residenteCuarto.clear();
            residenteCama.clear();
            residenteFdN.clear();
            residenteSdE.clear();
            residenteNumSeguro.clear();
            residenteCondiciones.clear();
            tablaMedicina.getItems().clear();
            tablaNotificacion.getItems().clear();
            tablaFamiliares.getItems().clear();
            imgResidente.setImage(new Image(new File ("Old Man.jpg").toURI().toString()));
            //choiceBoxResidentes.getItems().clear();
            Map<String,String> dbMap = db.getMap();
            Set<String > sNombres = dbMap.keySet();
            ArrayList<String> nombres = new ArrayList<String>(sNombres);
            ObservableList<String> olNombres = FXCollections.observableArrayList(nombres);
            choiceBoxResidentes.getItems().clear();
            db.deleteObject(residenteActual);
            choiceBoxResidentes.setItems(olNombres);
            db.closeDB();


        }else{
            System.out.println("Seleccione un residente para dar de baja");
            JOptionPane.showMessageDialog(null, "Seleccione un residente para dar de baja");
        }
    }

}
