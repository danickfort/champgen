/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AQ;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author matthieu.rossier
 */
public class RoleUnitTest {

    private WebDriver webDriver;
    private String baseUrl;
    
    private static final int TIME_TO_PAUSE = 2;
    private static final boolean DO_PAUSE = true;

    @Before
    public void setUp() {
        this.webDriver = new ChromeDriver();
        this.baseUrl = "http://localhost:8080/champgen";

        this.webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void hello() {
        this.webDriver.get(this.baseUrl);
        this.pause(TIME_TO_PAUSE);
        this.webDriver.get(this.baseUrl + "/faces/newchampionship.xhtml");
        this.pause(TIME_TO_PAUSE);
        
        String currentUrl = this.webDriver.getCurrentUrl();
        org.junit.Assert.assertTrue(currentUrl.endsWith("login.xhtml"));
        
        // login
        this.webDriver.findElement(By.id("login-form:username")).sendKeys("admin");
        this.webDriver.findElement(By.id("login-form:password")).sendKeys("admin");
        
        this.pause(TIME_TO_PAUSE);
        
        this.webDriver.findElement(By.id("login-form:btnSubmit")).click();
        
        this.pause(TIME_TO_PAUSE);
        
        this.webDriver.get(this.baseUrl + "/faces/newchampionship.xhtml");
        this.pause(TIME_TO_PAUSE);
        
        currentUrl = this.webDriver.getCurrentUrl();
        org.junit.Assert.assertTrue(currentUrl.endsWith("newchampionship.xhtml"));
        
        this.pause(TIME_TO_PAUSE);
    }

    private void pause(int secondes) {
        if (DO_PAUSE) {
            try {
                Thread.sleep(secondes * 1000);
            } catch (Exception e) {
            }
        }
    }

    @After
    public void tearDown() {
        this.webDriver.quit();
    }
}