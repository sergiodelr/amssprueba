package Entidades;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("message")
public class Familiar{

    @XStreamAlias("type")
    private String nombre;

    @XStreamAlias("type")
    private String telefono;

    public Familiar(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}