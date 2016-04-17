package net.paneris.juice;

/**
 * @author timp
 * @since 2016-04-16
 */
public final class Authoriser {
  public static boolean ok(String username, String password) {
    if (username.equalsIgnoreCase(("Tim"))) {
      if (password.equalsIgnoreCase("Password")) {
        return true;
      }
    }
    return false;
  }
}
