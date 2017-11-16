package Entidades;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XStreamAlias("message")
public class Reporte {
    @XStreamAlias("type")
    private LocalDate date;
    @XStreamAlias("type")
    private List<Eventualidad> eventualidades = new ArrayList<>();

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Eventualidad> getEventualidads() {
        return eventualidades;
    }

    public void setEventualidads(List<Eventualidad> eventualidads) {
        this.eventualidades = eventualidads;
    }

    public Reporte(LocalDate date, List<Eventualidad> eventualidads) {
        this.date = date;
        this.eventualidades = eventualidads;
    }

    public void addEventualidad(Eventualidad eventualidad){
        this.eventualidades.add(eventualidad);
    }
}
