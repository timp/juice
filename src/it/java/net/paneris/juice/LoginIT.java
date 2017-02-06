package net.paneris.juice;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.fail;

/**
 * @author timp
 * @since 2016-04-17
 */
public class LoginIT {

  protected static WebDriver driver;
  protected static String baseUrl = "http://127.0.0.1:8080";

  @Rule
  public ScreenshotFailedTestRule screenshotTestRule = new ScreenshotFailedTestRule(driver);

  @BeforeClass
  public static void setup() {
    //driver = new FirefoxDriver();
    driver = new ChromeDriver();
  }


  @AfterClass
  public static void destroy() {
    driver.quit();
  }

  @Test
  public void loginTest() {
    submit("Tim", "password", "Welcome");
    //Leave a gap in coverage to demonstrate aggregation
    //submit("Tim", "bad", "Try again");
    //submit("unknown", "password", "Try again");
    //submit("unknown", "bad", "Try again");
  }

  private void submit(String username, String password, String result) {
    driver.get(baseUrl);
    driver.findElement(By.id("username")).sendKeys(username);
    driver.findElement(By.id("password")).sendKeys(password);
    driver.findElement(By.id("submitLogin")).click();
    if(!driver.getPageSource().contains(result)) {
      fail("Text not found:'" + result + "', see screenshot.");
    }
  }
}
