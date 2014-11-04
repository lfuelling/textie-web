package net.k40s;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import de.micromata.azubi.Textie;
import de.micromata.azubi.model.Dungeon;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("textie")
public class Receiver {
private Dungeon dungeon;
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
    dungeon = Dungeon.getDungeon();
    Textie.diag = false;
    Textie.webapp = true;
    Textie.lastPrintedText = "";
    String[] parsedargs = {""};
    String[] parsedcmd = input.split(" ", 2);
    if(parsedcmd.length > 1) {
      parsedargs = parsedcmd[1].split(" ", 2);
    }
    Textie.executeCommand(parsedcmd,
        parsedargs);
    Thread.yield();

    return Textie.lastPrintedText;
  }
}
