package Entidades;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("message")
public class ProductoGenerico{
    @XStreamAlias("type")
    private String nombre;
    @XStreamAlias("type")
    private String descripcion;
    @XStreamAlias("type")
    private int cantidad;

    public ProductoGenerico(String nombre, String descripcion, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
