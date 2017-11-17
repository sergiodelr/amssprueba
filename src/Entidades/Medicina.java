package Entidades;

import com.sun.istack.internal.Nullable;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.Date;
@XStreamAlias("message")
public class Medicina extends ProductoGenerico{
    @XStreamAlias("type")
    private String dosis;
    @XStreamAlias("type")
    private String precauciones;
    @Nullable
    @XStreamAlias("type")
    private int duracionDias;
    @XStreamAlias("type")
    private Date fechaDeCaducidad;

    public Medicina(String nombre, String descripcion, int cantidad, String precauciones, int duracionDias,
                    String dosis) {
        super(nombre, descripcion, cantidad);
        this.precauciones = precauciones;
        this.duracionDias = duracionDias;
        this.dosis = dosis;
        //this.fechaDeCaducidad = fechaDeCaducidad;
    }

    public void setDosis(String dosis){
        this.dosis = dosis;
    }

    public String getDosis() {
        return dosis;
    }

    public String getPrecauciones() {
        return precauciones;
    }

    public void setPrecauciones(String precauciones) {
        this.precauciones = precauciones;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }

    public Date getFechaDeCaducidad() {
        return fechaDeCaducidad;
    }

    public void setFechaDeCaducidad(Date fechaDeCaducidad) {
        this.fechaDeCaducidad = fechaDeCaducidad;
    }
}
