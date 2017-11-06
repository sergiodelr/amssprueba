/*package Entidades;

import Utils.BDUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapdb.DB;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

class EventualidadTest {
    Eventualidad eventualidad = new Eventualidad();
    Eventualidad eventualidad1 = new Eventualidad();
    @Mock
    BDUtils db;
    @BeforeEach
    void setUp() {
        eventualidad.setEncargado("Javier");
        eventualidad.setDescripcion("Caida");
        eventualidad.setResidente("Adrian Silva");
        eventualidad.setFechaDeEventualidad(new Date(5,5,5));
    }

    @AfterEach
    void tearDown() {
        try {
            Files.delete(new File("testReportes.db").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void insertToMapWithoutDataTest(){
       // when(db.getObject(anyString())).thenThrow(new NullPointerException());
        Reporte reporte = eventualidad.insertToMap("testReportes.db");
        assertEquals(reporte.getDate(), eventualidad.getFechaDeEventualidad());
        assertEquals(eventualidad, reporte.getEventualidads().get(0));
    }
    @Test
    public void insertToMapWithDataTest(){
        // when(db.getObject(anyString())).thenThrow(new NullPointerException());
        eventualidad1.setFechaDeEventualidad(new Date(5,5,5));
        eventualidad1.setResidente("Adan Villarreal");
        eventualidad1.setDescripcion("Caida");
        eventualidad1.setEncargado("Javier de la Garza");
        Reporte reporte = eventualidad.insertToMap("testReportes.db");
        assertEquals(reporte.getDate(), eventualidad.getFechaDeEventualidad());
        Reporte reporte1 = eventualidad1.insertToMap("testReportes.db");
       // System.out.println(reporte1.getEventualidads().get(0).getResidente());
       // System.out.println(reporte1.getEventualidads().get(1).getResidente());
        assertEquals(eventualidad.getResidente(), reporte1.getEventualidads().get(0).getResidente());
        assertEquals(eventualidad1.getResidente(), reporte1.getEventualidads().get(1).getResidente());
    }


}*/