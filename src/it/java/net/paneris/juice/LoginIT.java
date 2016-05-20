package net.paneris.juice;

import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.fail;

/**
 * @author timp
 * @since 2016-04-17
 */
public class LoginIT {

  protected static WebDriver driver;
  protected static WebDriverBackedSelenium selenium;
  protected static String baseUrl = "http://127.0.0.1:8080";

  @Rule
  public ScreenshotFailedTestRule screenshotTestRule = new ScreenshotFailedTestRule(driver);

  @BeforeClass
  public static void setup() {
    driver = new FirefoxDriver();
    selenium = new WebDriverBackedSelenium(driver, getBaseUrl());
  }

  public static String getBaseUrl() {
    String setUrl = System.getProperty("JUICE_URL");
    if (setUrl == null) {
      setUrl = System.getenv("JUICE_URL");
    }
    if (setUrl != null) {
      baseUrl = setUrl;
    }
    System.out.println("Testing " + baseUrl);
    return baseUrl;
  }

  @AfterClass
  public static void destroy() {
    driver.quit();
  }

  @Test
  public void loginTest() {
    submit("Tim", "password", "Welcome");
    submit("Tim", "bad", "Try again");
    //Leave a gap in coverage to demonstrate aggregation
    //submit("unknown", "password", "Try again");
    //submit("unknown", "bad", "Try again");
  }

  private void submit(String username, String password, String result) {
    selenium.open("/");
    driver.findElement(By.id("username")).sendKeys(username);
    driver.findElement(By.id("password")).sendKeys(password);
    driver.findElement(By.id("submitLogin")).click();
    if(!selenium.isTextPresent(result)) {
      fail("Text not found:'" + result + "', see screenshot.");
    }
  }
}
