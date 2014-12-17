package net.k40s;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class StringUtils {
  public static String[] parseLogin(String input){
  String[] parsedInput = input.split("&");
  return parsedInput;
  }
}
