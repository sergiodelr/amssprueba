package Entidades;
import Utils.BDUtils;
import Utils.EntidadSerializableUtils;
import com.sun.istack.internal.Nullable;
import javafx.util.Pair;
import com.thoughtworks.xstream.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;


import javax.imageio.ImageIO;

/* para incluir la libreria: https://stackoverflow.com/questions/1051640/correct-way-to-add-external-jars-lib-jar-to-an-intellij-idea-project */
public class Residente{
    private BufferedImage image;
    private String nombre;
    private Date fechaDeNacimiento;
    private int numCuarto;
    private int numCama;
    private int status = 1;
    @Nullable
    private Date fechaDefuncion = null;
    private String servicioEmergencia;
    private String numSeguro;
    private Map<String, String> contactos = new HashMap<>();
    private List<String> condiciones = new ArrayList<>();
    //TODO(@adanvillarreal, @javier96): Los siguientes atributos son de clases pendientes
    private List<ProductoGenerico> productos;
    private List<Eventualidad> eventualidades;
    //TODO(@adanvillarreal): Investigar si es la manera adecuada de representar salidas

    public Residente(String nombre, Date fechaDeNacimiento, int numCuarto, int numCama, Date fechaDefuncion, String servicioEmergencia, String numSeguro, int status) throws IOException {
        /*
        try {
            this.image = ImageIO.read(new File(nombre + ".jpg"));
        } catch(IOException e){
            this.image = ImageIO.read(new File("generic.jpg"));
        }*/
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

    public void addProducto(ProductoGenerico productoGenerico) {
        this.productos.add(productoGenerico);
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

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
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

    public Date getFechaDefuncion() {
        return fechaDefuncion;
    }

    public void setFechaDefuncion(Date fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    public String getServicioEmergencia() {
        return servicioEmergencia;
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
}