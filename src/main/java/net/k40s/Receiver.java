package net.k40s;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.mongodb.*;
import de.micromata.azubi.Textie;
import de.micromata.azubi.model.Dungeon;

import java.net.UnknownHostException;
import java.sql.Array;


@Path("textie")
public class Receiver {
private Dungeon dungeon = Dungeon.createDungeon();

  @POST
  @Path("command")
  @Consumes("text/plain")
  @Produces("text/plain")
  public String handleInput(String input) {
    try {
      DBUtils.connect();
    } catch(UnknownHostException e) {       //TODO: Brauchen wir die Datenbank hier wirklich?
      e.printStackTrace();
      return "false";
    }
    Textie.diag = false;
    Textie.webapp = true;
    Textie.lastPrintedText = "";
    String[] parsedargs = {""};
    String[] parsedcmd = input.split(" ", 2);
    if(parsedcmd.length > 1) {
      parsedargs = parsedcmd[1].split(" ", 2);
    }
    Textie.executeCommand(parsedcmd,
        parsedargs, dungeon);
    Thread.yield();

    return Textie.lastPrintedText;
  }


  public void connect() {
    MongoClient mongoClient = null;
    try {
      mongoClient = new MongoClient();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    DB db = mongoClient.getDB("textieWeb");

    String name = "Test";
    String config = "";

    BasicDBObject query = new BasicDBObject("user", name);
    DBCollection coll = db.getCollection("userConfigs");


    DBCursor test = coll.find(query);

    DBObject data = test.curr();

    data.get("config");

  }

}
