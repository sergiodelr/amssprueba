/*package Entidades;

import Utils.BDUtils;
import Utils.EntidadSerializableUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapdb.*;
import org.mockito.Mock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class ResidenteTest {
    Residente residente = new Residente();
    Residente residente1 = new Residente();
    final String FILE = "testResidentes.db";
    @Mock
    BDUtils bd;
    @BeforeEach
    void setUp(){
        residente.setNombre("Residente Uno");
        residente.setNumCama(15);
        residente1.setNombre("Residente Dos");
        residente1.setNumSeguro("1234");
    }

    @AfterEach
    void tearDown() {
        try{
            Files.delete(new File(FILE).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void insertToMapTest(){
        residente.insertToMap(FILE);
        residente1.insertToMap(FILE);
        BDUtils db = new BDUtils(FILE);
        Residente resdb = (Residente)EntidadSerializableUtils.
                getEntidadFromXml((String)db.getObject("Residente Uno"));
        Residente res1db = (Residente)EntidadSerializableUtils.
                getEntidadFromXml((String)db.getObject("Residente Dos"));
        assertEquals(residente.getNombre(), resdb.getNombre());
        assertEquals(residente1.getNombre(), res1db.getNombre());
    }
}*/