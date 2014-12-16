package net.k40s.exceptions;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class DatabaseNotFoundException extends NullPointerException {
  public DatabaseNotFoundException(String message) {

    super(message);
  }
}
