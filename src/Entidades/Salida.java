package Entidades;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import javafx.util.Pair;

import java.util.Date;
@XStreamAlias("message")
public class Salida {
    @XStreamAlias("type")
    private Pair<Date, Date> fechas;
    @XStreamAlias("type")
    private String tipo;

    public Salida(Pair<Date, Date> fechas, String tipo) {
        this.fechas = fechas;
        this.tipo = tipo;
    }

    public Pair<Date, Date> getFechas() {
        return fechas;
    }

    public void setFechas(Pair<Date, Date> fechas) {

        this.fechas = fechas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}