package net.paneris.juice;

import org.junit.Test;

import static net.paneris.juice.Login.header;
import static org.junit.Assert.assertEquals;

/**
 * @author timp
 * @since 2016-04-16
 */
public class LoginTest {
  Authoriser authoriser = new Authoriser();
  @Test
  public void testSuccessResponse() {
    assertEquals("Welcome",
        Login.response(authoriser.ok("Tim", "password")));
  }

  @Test
  public void testFailurePath() {
    assertEquals("<a href=\"/\">Try again</a>",
            Login.response(authoriser.ok("Tim", "bad password")));
    assertEquals("<a href=\"/\">Try again</a>",
            Login.response(authoriser.ok("unknown", "password")));
  }

  @Test
  public void testHeader() {
    assertEquals("<h1></h1>\n", header(""));
    assertEquals("<h1>null</h1>\n", header(null));
    assertEquals("<h1>heading</h1>\n", header("heading"));
  }
}
