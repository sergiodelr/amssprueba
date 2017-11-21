package Entidades;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("message")
public class Notificacion{

    @XStreamAlias("type")
    private String residente;

    @XStreamAlias("type")
    private String medicamento;

    @XStreamAlias("type")
    private int cama;

    @XStreamAlias("type")
    private int cuarto;

    @XStreamAlias("type")
    private long diasRestantes;

    @XStreamAlias("type")
    private String contacto;

    @XStreamAlias("type")
    private String telefono;

    public Notificacion(String residente, String medicamento, int cama, int cuarto, long diasRestantes, String contacto, String telefono) {
        this.residente = residente;
        this.medicamento = medicamento;
        this.cama = cama;
        this.cuarto = cuarto;
        this.diasRestantes = diasRestantes;
        this.contacto = contacto;
        this.telefono = telefono;
    }

    public String getResidente() {
        return residente;
    }

    public void setResidente(String residente) {
        this.residente = residente;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public int getCama() {
        return cama;
    }

    public void setCama(int cama) {
        this.cama = cama;
    }

    public int getCuarto() {
        return cuarto;
    }

    public void setCuarto(int cuarto) {
        this.cuarto = cuarto;
    }

    public long getDiasRestantes() {
        return diasRestantes;
    }

    public void setDiasRestantes(long diasRestantes) {
        this.diasRestantes = diasRestantes;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
