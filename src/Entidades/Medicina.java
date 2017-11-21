package Entidades;

import com.sun.istack.internal.Nullable;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import org.mockito.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

@XStreamAlias("message")
public class Medicina extends ProductoGenerico{
    @XStreamAlias("type")
    private String residente;
    @XStreamAlias("type")
    private String dosis;
    @XStreamAlias("type")
    private String precauciones;
    @Nullable
    @XStreamAlias("type")
    private int duracionDias;
    @XStreamAlias("type")
    private LocalDate fechaDeInicio;
    @XStreamAlias("type")
    private long diasRestantes;

    public Medicina(String nombre, String descripcion, int cantidad, String precauciones, int duracionDias,
                    String dosis, String residente) {
        super(nombre, descripcion, cantidad);

        this.precauciones = precauciones;
        this.duracionDias = duracionDias;
        this.dosis = dosis;
        this.fechaDeInicio = LocalDate.now();
        this.residente = residente;
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

    public LocalDate getFechaDeInicio() {
        return fechaDeInicio;
    }

    public void setFechaDeInicio(LocalDate fechaDeInicio) {
        this.fechaDeInicio = fechaDeInicio;
    }

    public void setResidente(String residente){
        this.residente = residente;
    }

    public String getResidente() {
        return residente;
    }

    public long getDiasRestantes(){
        return DAYS.between(LocalDate.now(), fechaDeInicio.plusDays(duracionDias));
    }

    public void setDiasRestantes(long diasRestantes){
        this.diasRestantes = diasRestantes;
    }
}
