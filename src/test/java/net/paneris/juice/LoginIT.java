package net.paneris.juice;

import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;


/**
 * @author timp
 * @since 2016-04-17
 */
public class LoginIT {
  protected WebDriver driver;
  protected WebDriverBackedSelenium selenium;
  protected String baseUrl = "http://127.0.0.1:8080";


  @Before
  public void setup() {
    driver = new FirefoxDriver();
    selenium = new WebDriverBackedSelenium(driver, getBaseUrl());
  }

  public String getBaseUrl() {
    String setUrl = System.getenv("JUICE_URL");
    if (setUrl != null) {
      baseUrl = setUrl;
    }
    return baseUrl;
  }

  @After
  public void destroy() {
    driver.quit();
  }

  @Test
  public void loginTest() {
    submit("Tim", "password", "Welcome");
    submit("Tim", "bad", "Try again");
    submit("unknown", "password", "Try again");
    submit("unknown", "bad", "Try again");
  }
  private void submit(String username, String password, String result) {
    selenium.open("/");
    driver.manage().window().maximize();
    WebElement loginText = driver.findElement(By.id("username"));
    loginText.sendKeys(username);
    WebElement passwordText = driver.findElement(By.id("password"));
    passwordText.sendKeys(password);
    driver.findElement(By.id("submitLogin")).click();
    WebDriverWait wait = new WebDriverWait(driver, 3);
    wait.until(ExpectedConditions
            .textMatches(
                    By.ByXPath.xpath("//body"), Pattern.compile(result)));

  }
}
