package Entidades;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.mapdb.DB;
import com.thoughtworks.xstream.*;
@XStreamAlias("message")
public class Recordatorio {
    @XStreamAlias("type")
    private int diasFaltantes;
    @XStreamAlias("type")
    private boolean status;
    @XStreamAlias("type")
    public Eventualidad ev;

    public Recordatorio(int diasFaltantes, boolean status, Eventualidad ev) {
        this.diasFaltantes = diasFaltantes;
        this.status = status;
        this.ev = ev;
    }
    public int getDiasFaltantes() {
        return diasFaltantes;
    }

    public void setDiasFaltantes(int diasFaltantes) {
        this.diasFaltantes = diasFaltantes;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}