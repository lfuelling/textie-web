package net.k40s.exceptions;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class UsernameAlreadyTakenException extends Exception {
  String username;
  public UsernameAlreadyTakenException(String username) {

    super("User" + username + " already exists.");
  }
  public String getUserName(){
    return username;
  }
}
