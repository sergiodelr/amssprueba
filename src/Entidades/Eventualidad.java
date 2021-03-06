package Entidades;

import Utils.BDUtils;
import Utils.EntidadSerializableUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XStreamAlias("message")
public class Eventualidad {
    @XStreamAlias("type")
    private String encargado;
    @XStreamAlias("type")
    private String descripcion;
    @XStreamAlias("type")
    private String residente;
    @XStreamAlias("type")
    private String hora;
    private LocalDate fechaDeEventualidad;
    public Eventualidad(){}
    public Eventualidad(String encargado, String descripcion, String residente, LocalDate fechaDeEventualidad, String hora) {
        this.encargado = encargado;
        this.descripcion = descripcion;
        this.residente = residente;
        this.fechaDeEventualidad = fechaDeEventualidad;
        this.hora = hora;
        insertToMap("reportes.db");
    }

    public Reporte insertToMap(String file){
        BDUtils db = new BDUtils(file);
        Reporte reporte;
        try {
            String xml = (String) db.getObject(this.fechaDeEventualidad.toString());
            //System.out.println(xml);
            reporte = (Reporte) EntidadSerializableUtils.getEntidadFromXml(xml);
        } catch(NullPointerException e){
            reporte = new Reporte(this.fechaDeEventualidad, new ArrayList<Eventualidad>());
        }
        reporte.addEventualidad(this);
        if(db.getObject(this.fechaDeEventualidad.toString()) == null)
            db.insertObject(this.fechaDeEventualidad.toString(), EntidadSerializableUtils.getXml(reporte));
        db.replaceObject(this.fechaDeEventualidad.toString(), EntidadSerializableUtils.getXml(reporte));
        db.closeDB();
        return reporte;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResidente() {
        return residente;
    }

    public void setResidente(String residente) {
        this.residente = residente;
    }

    public LocalDate getFechaDeEventualidad() {
        return fechaDeEventualidad;
    }

    public void setFechaDeEventualidad(LocalDate fechaDeEventualidad) {
        this.fechaDeEventualidad = fechaDeEventualidad;
    }

    public String getHora(){
        return this.hora;
    }
}
