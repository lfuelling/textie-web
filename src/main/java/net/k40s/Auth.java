package net.k40s;


import de.micromata.azubi.Consts;
import net.k40s.exceptions.UsernameAlreadyTakenException;

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
    DBUtils.login("blablablablasbSDJSKD");             //Namen setzen. Wo wollen wir den Token generieren?
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
    DBUtils.login(email);             //email durch namen ersetzen. Wo wollen wir den Token generieren?

    if(true == false/* TODO: PASSWORT RICHTIG */){
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
    //DBUtils.logout(username);                     der benutzername muss in allen seiten abrufbar sein
    return ""; // TODO: Soll True/False zurückgeben.
  }

  @POST
  @Path("register")
  @Consumes("text/plain")
  @Produces("text/plain")
  public String register(String input) {

    String email;
    String pass;

    String[] pi = StringUtils.parseLogin(input);
    email = pi[0];
    pass = pi[1];

    try {
      DBUtils.createUser(email, pass);
    } catch(UsernameAlreadyTakenException e) {
      System.out.println(e.getMessage());
      return "false";
    }
    return "true";
  }


}
