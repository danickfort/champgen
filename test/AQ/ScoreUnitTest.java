/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AQ;

/**
 *
 * @author garynietlispach
 */

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ScoreUnitTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    DataBaseTool.restartDB();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testJunit() throws Exception {
    driver.get(baseUrl + "/champgen/faces/index.xhtml");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("login-form:username")).clear();
    driver.findElement(By.id("login-form:username")).sendKeys("admin");
    driver.findElement(By.id("login-form:password")).clear();
    driver.findElement(By.id("login-form:password")).sendKeys("admin");
    driver.findElement(By.id("login-form:btnSubmit")).click();
    driver.findElement(By.linkText("New Championship")).click();
    driver.findElement(By.id("new-championship-form:name")).clear();
    driver.findElement(By.id("new-championship-form:name")).sendKeys("championship");
    driver.findElement(By.id("new-championship-form:btnSubmit")).click();
    driver.findElement(By.linkText("New Team")).click();
    driver.findElement(By.id("newteam-form:team")).clear();
    driver.findElement(By.id("newteam-form:team")).sendKeys("team1");
    driver.findElement(By.id("newteam-form:leader")).clear();
    driver.findElement(By.id("newteam-form:leader")).sendKeys("leader1");
    driver.findElement(By.id("newteam-form:btnSubmit")).click();
    driver.findElement(By.id("newteam-form:team")).clear();
    driver.findElement(By.id("newteam-form:team")).sendKeys("team2");
    driver.findElement(By.id("newteam-form:leader")).clear();
    driver.findElement(By.id("newteam-form:leader")).sendKeys("leader2");
    driver.findElement(By.id("newteam-form:btnSubmit")).click();
    driver.findElement(By.id("newteam-form:team")).clear();
    driver.findElement(By.id("newteam-form:team")).sendKeys("team3");
    driver.findElement(By.id("newteam-form:leader")).clear();
    driver.findElement(By.id("newteam-form:leader")).sendKeys("leader3");
    driver.findElement(By.id("newteam-form:btnSubmit")).click();
    driver.findElement(By.linkText("Champgen")).click();
    driver.findElement(By.cssSelector("input.btn.btn-default")).click();
    driver.findElement(By.linkText("Logout")).click();
    driver.findElement(By.cssSelector("a.btn.btn-warning")).click();
    driver.findElement(By.id("login-form:username")).clear();
    driver.findElement(By.id("login-form:username")).sendKeys("leader1");
    driver.findElement(By.id("login-form:password")).clear();
    driver.findElement(By.id("login-form:password")).sendKeys("password");
    driver.findElement(By.id("login-form:btnSubmit")).click();
    driver.findElement(By.linkText("Championship")).click();
    driver.findElements(By.tagName("input")).get(1).clear();
    driver.findElements(By.tagName("input")).get(1).sendKeys("1");
    driver.findElements(By.tagName("input")).get(2).clear();
    driver.findElements(By.tagName("input")).get(2).sendKeys("2");
    driver.findElements(By.tagName("input")).get(3).clear();
    driver.findElements(By.tagName("input")).get(3).sendKeys("13-04-2014");
    driver.findElements(By.tagName("input")).get(4).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*1 - 2, has been played 2014-04-13[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}