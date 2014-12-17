package net.k40s;


import de.micromata.azubi.Consts;
import net.k40s.exceptions.UsernameAlreadyTakenException;
import net.k40s.exceptions.WrongCredentialsException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.util.Date;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
@Path("auth")
public class Auth {

  /**
   * Checks if the session token is valid.
   * @param token The token
   * @param req
   * @return true or false
   */
  @POST
  @Path("restore")
  @Consumes("text/plain")
  @Produces("text/plain")
  public String restore(String token, @Context HttpServletRequest req) {
    if(token.equals(req.getSession().getAttribute("token"))){
      return "true";
    } else {
      return "false";
    }
  }

  /**
   * logs you in.
   * @param input
   * @param req
   * @return
   */
  @POST
  @Path("login")
  @Consumes("text/plain")
  @Produces("text/plain")
  public String login(String input, @Context HttpServletRequest req) {

    String username;
    String pass;
    String token;

    String[] pi = StringUtils.parseLogin(input);
    username = pi[0];
    pass = pi[1];

    try {
      token = DBUtils.login(username, pass);
    } catch(WrongCredentialsException e) {
      System.out.println(e.getMessage());
      return "false";
    }
    req.getSession().setAttribute("token", token);
    req.getSession().setAttribute("user", username);
    return token;

  }

  /**
   * Logs you out.
   * @param input
   * @param req
   * @return Always true.
   */
  @GET
  @Path("logout")
  @Produces("text/plain")
  public String logout(@Context HttpServletRequest req) {
    req.getSession().invalidate();
    return "true";
  }

  /**
   * Registers a new user.
   * @param input
   * @return true or false
   */
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
