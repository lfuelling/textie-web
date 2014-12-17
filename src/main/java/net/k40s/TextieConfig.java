package net.k40s;

import java.io.Serializable;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class TextieConfig implements Serializable {
  private String config;

  private String token;

  public TextieConfig() {

  }

  public String getConfig() {

    return config;
  }

  public void setConfig(String config) {

    this.config = config;
  }

  public String getToken() {

    return token;
  }

  public void setToken(String token) {

    this.token = token;
  }
}
