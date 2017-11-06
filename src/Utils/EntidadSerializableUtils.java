package Utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class EntidadSerializableUtils {
    public static String getXml(Object object){
        XStream xstream = new XStream(new StaxDriver());
        return xstream.toXML(object).toString();
    }
    public static Object getEntidadFromXml(String xml){
       XStream xstream = new XStream((new StaxDriver()));
        System.out.println("xml"+xml);
       return xstream.fromXML(xml);
    }

}
