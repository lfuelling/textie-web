package net.k40s;

import java.io.Serializable;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class TextieCommand implements Serializable {

  private String command;

  private String token;

  public TextieCommand() {

  }

  public String getCommand() {

    return command;
  }

  public void setCommand(String command) {

    this.command = command;
  }

  public String getToken() {

    return token;
  }

  public void setToken(String token) {

    this.token = token;
  }
}
