package net.k40s;


import de.micromata.azubi.Consts;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.util.Date;

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
  public String login(String input) {

    String email;
    String pass;
    String token = "";

    String[] pi = StringUtils.parseLogin(input);
    email = pi[0];
    pass = pi[1];

    //TODO: Database stuff...

    if(/* TODO: PASSWORT RICHTIG */){
      return token;
    } else {
      return "false";
    }

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
    return ""; // TODO: Soll True/False zurück geben zurückgeben.
  }


}
