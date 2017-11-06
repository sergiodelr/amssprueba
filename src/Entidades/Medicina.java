package Entidades;

import com.sun.istack.internal.Nullable;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.Date;
@XStreamAlias("message")
public class Medicina extends ProductoGenerico{
    @XStreamImplicit
    private ArrayList<Boolean> Dosis = new ArrayList<>(3);
    @XStreamAlias("type")
    private String precauciones;
    @Nullable
    @XStreamAlias("type")
    private int duracionDias;
    @XStreamAlias("type")
    private Date fechaDeCaducidad;

    public Medicina(String nombre, String descripcion, int cantidad, String precauciones, int duracionDias, Date fechaDeCaducidad) {
        super(nombre, descripcion, cantidad);
        this.precauciones = precauciones;
        this.duracionDias = duracionDias;
        this.fechaDeCaducidad = fechaDeCaducidad;
    }

    public void modifyDosis(int i, boolean status){
        Dosis.set(i, status);
    }

    public ArrayList<Boolean> getDosis() {
        return Dosis;
    }

    public void setDosis(ArrayList<Boolean> dosis) {
        Dosis = dosis;
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
