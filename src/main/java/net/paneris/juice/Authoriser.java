package net.paneris.juice;

/**
 * @author timp
 * @since 2016-04-16
 */
public final class Authoriser {

  public boolean ok(String username, String password) {
    if ("Tim".equalsIgnoreCase(username)
        && "Password".equalsIgnoreCase(password)) {
      return true;
    }
    return false;
  }
}
