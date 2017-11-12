import Entidades.Eventualidad;
import Entidades.Reporte;
import Entidades.Residente;
import Utils.EntidadSerializableUtils;
import Entidades.Recordatorio;

import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.mapdb.*;
import com.thoughtworks.xstream.XStream;
import org.mapdb.DBMaker;
import Utils.BDUtils;
import Utils.ResidenteUtils;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.*;
import java.util.concurrent.ConcurrentMap;

public class main {
    public static void main(String[] args) throws IOException {
        ResidenteUtils.altaMasiva("src/altaMasiva.csv");
        BDUtils db = new BDUtils("residentes.db");
        System.out.println((String) db.getObject("Adan Villarreal"));
        db.closeDB();
    }
}
