package net.k40s;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class Secure {
  public static String generateToken(){
    SecureRandom random = new SecureRandom();
    return new BigInteger(130, random).toString(32);
  }
}
