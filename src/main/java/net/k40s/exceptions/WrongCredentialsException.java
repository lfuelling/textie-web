package net.k40s.exceptions;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class WrongCredentialsException extends Exception {
  private String username;
  public WrongCredentialsException(String username) {
    super("User " + username + " entered wrong credentials.");
    this.username = username;
  }

  public String getUsername() {

    return username;
  }
}
