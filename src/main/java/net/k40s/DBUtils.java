package net.k40s;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * Hier sind alle Datenbankfunktionen drin.
 *
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class DBUtils {

  public static void connect() throws UnknownHostException{
    MongoClient mongoClient = null;
    mongoClient = new MongoClient();
    DB db = mongoClient.getDB( "textieWeb" );

    String name = "Test";
    String config = "";
    DBCollection coll = db.getCollection("userConfigs");
    DBObject test = coll.findOne();
  }


}
