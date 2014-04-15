/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AQ;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author danick.fort
 */
public class TeamsUnitTest {

    private WebDriver webDriver;
    private String baseUrl;
    
    private static final int TIME_TO_PAUSE = 2;
    private static final boolean DO_PAUSE = true;

    @Before
    public void setUp() {
        this.webDriver = new ChromeDriver();
        this.baseUrl = "http://localhost:8080";

        this.webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        DataBaseTool.restartDB();
    }

    @Test
    public void hello() {
        WebDriver driver = webDriver;
        int noTeams = 3;
        
        driver.get(baseUrl + "/champgen/faces/index.xhtml");
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("login-form:username")).sendKeys("admin");
        driver.findElement(By.id("login-form:password")).sendKeys("admin");
        driver.findElement(By.id("login-form:btnSubmit")).click();
        driver.findElement(By.linkText("New Championship")).click();
        driver.findElement(By.id("new-championship-form:name")).clear();
        driver.findElement(By.id("new-championship-form:name")).sendKeys("TestChamp1");
        driver.findElement(By.id("new-championship-form:btnSubmit")).click();
        assertTrue(driver.findElement(By.id("msg:info")).getText().matches("^[\\s\\S]*added ![\\s\\S]*$"));
        
        for (int i =0; i < noTeams; i++)
        {
        driver.findElement(By.linkText("New Team")).click();
        driver.findElement(By.id("newteam-form:team")).clear();
        driver.findElement(By.id("newteam-form:team")).sendKeys("Team" + i);
        driver.findElement(By.id("newteam-form:leader")).clear();
        driver.findElement(By.id("newteam-form:leader")).sendKeys("Leader" + i);
        driver.findElement(By.id("newteam-form:btnSubmit")).click();
        }
        
        
        this.pause(TIME_TO_PAUSE);
        this.pause(TIME_TO_PAUSE);
        
        driver.get(baseUrl + "/champgen/faces/index.xhtml");
        
        int rowCount=webDriver.findElements(By.xpath("//table/tbody/tr")).size();
        System.out.println(rowCount);
        assertTrue(rowCount==noTeams+1); // + 1 because title is counted as row
        
        this.pause(TIME_TO_PAUSE);
    }

    private void pause(int secondes) {
        if (DO_PAUSE) {
            try {
                webDriver.manage().timeouts().implicitlyWait(secondes, TimeUnit.SECONDS);
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