package net.paneris.juice;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileOutputStream;

/**
 * See http://blogs.steeplesoft.com/posts/2012/grabbing-screenshots-of-failed-selenium-tests.html
 *
 * @author pizeyt
 * @since 2016-04-19
 */
public class ScreenshotFailedTestRule implements MethodRule {

  WebDriver driver;

  public ScreenshotFailedTestRule(WebDriver driver) {
    this.driver = driver;
  }

  public Statement apply(final Statement statement,
                         final FrameworkMethod frameworkMethod, final Object o) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        try {
          statement.evaluate();
        } catch (Throwable t) {
          captureScreenshot(frameworkMethod.getName());
          throw t; // rethrow to allow the failure to be reported to JUnit
        }
      }

      public void captureScreenshot(String fileName) {
        try {
          new File("target/screenshots/").mkdirs();
          FileOutputStream out = new FileOutputStream("target/screenshots/screenshot-"
              + fileName + ".png");
          out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
          out.close();
        } catch (Exception e) {
          // No need to crash the tests if the screenshot fails
          e.printStackTrace();
        }
      }
    };
  }
}