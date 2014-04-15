/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AQ;

import java.io.Console;
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
public class GenerateMatchdaysTest {

    private WebDriver webDriver;
    private String baseUrl;
    
    private static final int TIME_TO_PAUSE = 1;
    private static final boolean DO_PAUSE = true;

    @Before
    public void setUp() {
        this.webDriver = new ChromeDriver();
        this.baseUrl = "http://localhost:8080/champgen";

        this.webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
        try {
            DataBaseTool.executeSQLfromFile("emptyDB.sql");
        }
        catch (Exception e)
        {}
    }

    @Test
    public void generateMatchdays() {
        
        this.webDriver.get(this.baseUrl);
        this.pause(TIME_TO_PAUSE);
        this.webDriver.get(this.baseUrl + "/faces/login.xhtml");
        this.pause(TIME_TO_PAUSE);
        this.webDriver.findElement(By.id("login-form:username")).sendKeys("admin");
        this.webDriver.findElement(By.id("login-form:password")).sendKeys("admin");
        this.pause(TIME_TO_PAUSE);
        this.webDriver.findElement(By.id("login-form:btnSubmit")).click();
        
        this.webDriver.get(this.baseUrl + "/faces/newchampionship.xhtml");
        this.webDriver.findElement(By.id("new-championship-form:name")).sendKeys("c1");
        this.pause(TIME_TO_PAUSE);
        this.webDriver.findElement(By.id("new-championship-form:btnSubmit")).click();
        
        this.webDriver.get(this.baseUrl + "/faces/newteam.xhtml");
        
        this.webDriver.findElement(By.id("newteam-form:team")).clear();
        this.webDriver.findElement(By.id("newteam-form:team")).sendKeys("t1");
        this.webDriver.findElement(By.id("newteam-form:leader")).clear();
        this.webDriver.findElement(By.id("newteam-form:leader")).sendKeys("l1");
        this.pause(TIME_TO_PAUSE);
        this.webDriver.findElement(By.id("newteam-form:btnSubmit")).click();
        
        this.webDriver.findElement(By.id("newteam-form:team")).clear();
        this.webDriver.findElement(By.id("newteam-form:team")).sendKeys("t2");
        this.webDriver.findElement(By.id("newteam-form:leader")).clear();
        this.webDriver.findElement(By.id("newteam-form:leader")).sendKeys("l2");
        this.pause(TIME_TO_PAUSE);
        this.webDriver.findElement(By.id("newteam-form:btnSubmit")).click();
        
        this.webDriver.findElement(By.id("newteam-form:team")).clear();
        this.webDriver.findElement(By.id("newteam-form:team")).sendKeys("t3");
        this.webDriver.findElement(By.id("newteam-form:leader")).clear();
        this.webDriver.findElement(By.id("newteam-form:leader")).sendKeys("l3");
        this.pause(TIME_TO_PAUSE);
        this.webDriver.findElement(By.id("newteam-form:btnSubmit")).click();
        
        this.webDriver.get(this.baseUrl + "/faces/index.xhtml");
        
        this.webDriver.findElement(By.cssSelector("input.btn.btn-default")).click();
        this.pause(TIME_TO_PAUSE * 2);
        
        // recherche de la chaîne de caractère sur la page => "| t3 vs t2 | t1 vs t2 | t1 vs t3 |"
        org.junit.Assert.assertTrue(this.webDriver.findElements(By.tagName("li")).get(4).getText().equals("| t3 vs t2 | t1 vs t2 | t1 vs t3 |"));
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