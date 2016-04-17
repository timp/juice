package net.paneris.juice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author timp
 * @since 2016-04-16
 */
public class Login extends HttpServlet {

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    doGetPostRequest(request, response);
  }

  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
    doGetPostRequest(request, response);
  }

  private void doGetPostRequest(final HttpServletRequest request,
                                final HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
      out.println(header(response(
          Authoriser.ok(
              request.getParameter("username"),
              request.getParameter("password")))));
  }

  public static String header(String heading) {
    return "<h1>" + heading + "</h1>\n";
  }

  public static String link(String text, String url) {
    return "<a href=\"" + url + "\">" + text + "</a>";
  }

  public static String response(boolean authorised) {
    if (authorised) {
      return "Welcome";
    } else {
      return link("Try again", "/");
    }
  }

}
