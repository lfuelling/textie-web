package net.k40s;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.mongodb.*;
import de.micromata.azubi.Textie;
import de.micromata.azubi.model.Dungeon;

import java.net.UnknownHostException;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("textie")
public class Receiver {
private Dungeon dungeon = Dungeon.createDungeon();
  /**
   * Method handling HTTP GET requests. The returned object will be sent
   * to the client as "text/plain" media type.
   *
   * @return String that will be returned as a text/plain response.
   */
  @POST
  @Consumes("text/plain")
  @Produces("text/plain")
  public String handleInput(String input) {
    connect();
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
    DB db = mongoClient.getDB( "textieWeb" );

    String name = "Test";
    String config = "";
    DBCollection coll = db.getCollection("userConfigs");
    DBObject test = coll.findOne();


  }








}
