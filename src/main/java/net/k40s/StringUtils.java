package net.k40s;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class StringUtils {
  public static String[] parseLogin(String input){
  String[] parsedInput = input.split("&");
  parsedInput[0].replaceAll("%40", "@"); //Fix for the 'unrecognized token: "40"' error.
  return parsedInput;
  }
}
