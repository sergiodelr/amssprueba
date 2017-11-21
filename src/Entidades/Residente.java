package Entidades;
import Utils.BDUtils;
import Utils.EntidadSerializableUtils;
import com.sun.istack.internal.Nullable;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import javafx.util.Pair;
import com.thoughtworks.xstream.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;


import javax.imageio.ImageIO;

/* para incluir la libreria: https://stackoverflow.com/questions/1051640/correct-way-to-add-external-jars-lib-jar-to-an-intellij-idea-project */
@XStreamAlias("message")
public class  Residente{
    @XStreamAlias("type")
    private BufferedImage image;
    @XStreamAlias("type")
    private String nombre;
    @XStreamAlias("type")
    private LocalDate fechaDeNacimiento;
    @XStreamAlias("type")
    private int numCuarto;
    @XStreamAlias("type")
    private int numCama;
    @XStreamAlias("type")
    private int status = 1;
    @Nullable
    @XStreamAlias("type")
    private LocalDate fechaDefuncion = null;
    @XStreamAlias("type")
    private String servicioEmergencia;
    @XStreamAlias("type")
    private String numSeguro;
    @XStreamImplicit
    private Map<String, String> contactos = new HashMap<>();
    @XStreamImplicit
    private List<String> condiciones = new ArrayList<>();
    //TODO(@adanvillarreal, @javier96): Los siguientes atributos son de clases pendientes
    @XStreamImplicit
    private List<Medicina> medicinas = new ArrayList<>();
    @XStreamImplicit
    private List<Eventualidad> eventualidades = new ArrayList<>();
    //TODO(@adanvillarreal): Investigar si es la manera adecuada de representar salidas

    public Residente(String nombre, LocalDate fechaDeNacimiento, int numCuarto, int numCama, LocalDate fechaDefuncion, String servicioEmergencia, String numSeguro, int status) throws IOException {

        try {
            this.image = ImageIO.read(new File(nombre + ".jpg"));
        } catch(IOException e){
            this.image = ImageIO.read(new File("generic.jpg"));
        }
        System.out.println("Residente");
        this.nombre = nombre;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.numCuarto = numCuarto;
        this.numCama = numCama;
        this.fechaDefuncion = null;
        this.servicioEmergencia = servicioEmergencia;
        this.numSeguro = numSeguro;
        this.status = status;
        insertToMap("residentes.db");
    }
    public Residente(){}

    public void insertToMap(String file){
        BDUtils db = new BDUtils(file);
        if(db.getObject(this.nombre) == null){
            db.insertObject(this.nombre, EntidadSerializableUtils.getXml(this));
        }
        db.closeDB();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus (int status) {
        this.status = status;
    }

    public void addContacto(String contacto, String telefono) {
        this.contactos.put(contacto, telefono);
    }

    public void addCondicion(String condicion) {
        this.condiciones.add(condicion);
    }

    public void addEventualidad(Eventualidad eventualidad) {
        this.eventualidades.add(eventualidad);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public int getNumCuarto() {
        return numCuarto;
    }

    public void setNumCuarto(int numCuarto) {
        this.numCuarto = numCuarto;
    }

    public int getNumCama() {
        return numCama;
    }

    public void setNumCama(int numCama) {
        this.numCama = numCama;
    }

    public LocalDate getFechaDefuncion() {
        return fechaDefuncion;
    }

    public void setFechaDefuncion(LocalDate fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    public String getServicioEmergencia() {
        return servicioEmergencia;
    }

    public List<Eventualidad> getEventualidades(){
        return eventualidades;
    }
    public List<Medicina> getMedicinas(){
        return this.medicinas;
    }
    public void addMedicina(Medicina medicina){
        (this.medicinas).add(medicina);
    }
    public void setServicioEmergencia(String servicioEmergencia) {
        this.servicioEmergencia = servicioEmergencia;
    }

    public String getNumSeguro() {
        return numSeguro;
    }

    public void setNumSeguro(String numSeguro) {
        this.numSeguro = numSeguro;
    }

    public List<String> getCondiciones(){
        return this.condiciones;
    }
}