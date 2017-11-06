package Utils;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.util.concurrent.ConcurrentMap;

public class BDUtils {
    DB db;
    ConcurrentMap map;

    public BDUtils(String file) {
        this.db = DBMaker.fileDB(file).make();
        this.map = db.hashMap("map").createOrOpen();
    }
    public void closeDB(){
        db.close();
    }
    public Object getObject(String key){
        return map.containsKey(key)?map.get(key): null;
    }
    public void insertObject(String key, Object object){
        if(!map.containsKey(key)) {
            map.put(key, object);
        }
    }
    public void deleteObject(String key){
        map.remove(key) ;
    }
    public void replaceObject(String key, Object object){
        map.replace(key, object);
    }
}
