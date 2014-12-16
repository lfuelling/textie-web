package net.k40s;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.net.UnknownHostException;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
@Path("auth")
public class Auth {

  @POST
  @Path("restore")
  @Consumes("text/plain")
  @Produces("text/plain")
  public String restore(String input){ // TODO: Input = USERNAME&PASSENC (Wie im Beispiellogin)
    //TODO: Datenbankverbindung
    return ""; // TODO: Soll Token zurückgeben
  }

  @POST
  @Path("login")
  @Consumes("text/plain")
  @Produces("text/plain")
  public String login(String input) { // TODO: Input = USERNAME&PASSENC (Wie im Beispiellogin)
    //TODO: Datenbankverbindung
    return ""; // TODO: Soll Token zurückgeben.
  }


  @POST
  @Path("logout")
  @Consumes("text/plain")
  @Produces("text/plain")
  public String logout(String input) { //TODO: Input = Token&ID
    //TODO: Datenbankverbindung
    return ""; // TODO: Soll True/False zurückgeben.
  }

  @POST
  @Path("register")
  @Consumes("text/plain")
  @Produces("text/plain")
  public String register(String input) { // TODO: Input = USERNAME&PASSENC (Wie im Beispiellogin)
    //TODO: Datenbankverbindung
    return ""; // TODO: Soll Token zurückgeben.
  }


}
