package com.tests;

import com.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WONegativeLoginTests extends BrowserUtils {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/\n" +
                "login.aspx");

    }

    @AfterMethod
    public void cleanUp() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
@Test
public void Negative_Login_Test_Wrong_Username () {
    //Checking title
    String expectedTitle = "Web Orders Login";
    String actualTitle = driver.getTitle();
    System.out.println("Expected Title: " + expectedTitle + " Actual Title: " + actualTitle);
    verifyTextMatches(expectedTitle, actualTitle);
    System.out.println("Step one Title Matched");

    //Getting current URL
    String Current1stURL = driver.getCurrentUrl();

    //Login in and clicking
    driver.findElement(By.xpath("//*[@name='ctl00$MainContent$username']")).sendKeys("Test");
    driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).sendKeys("Test");
    driver.findElement(By.xpath("//input[@name='ctl00$MainContent$login_button']")).click();

    //Checking title
    System.out.println("Expected Title: " + expectedTitle + " Actual Title: " + actualTitle);
    System.out.println("Step two Title Matched");
    verifyTextMatches(Current1stURL, driver.getCurrentUrl());
}
    @Test
    public void NegativeLoginTestWrongPassword () {
        //Checking title
        String expectedTitle = "Web Orders Login";
        String actualTitle = driver.getTitle();
        System.out.println("Expected Title: " + expectedTitle + " Actual Title: " + actualTitle);
        verifyTextMatches(expectedTitle, actualTitle);
        System.out.println("Step one Title Matched");

        //Getting current URL
        String Current1stURL = driver.getCurrentUrl();

        //Login in and clicking
        driver.findElement(By.xpath("//*[@name='ctl00$MainContent$username']")).sendKeys("Tester");
        driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).sendKeys("Tester");
        driver.findElement(By.xpath("//input[@name='ctl00$MainContent$login_button']")).click();

        //Checking title
        System.out.println("Expected Title: " + expectedTitle + " Actual Title: " + actualTitle);
        System.out.println("Step two Title Matched");
        verifyTextMatches(Current1stURL, driver.getCurrentUrl());
    }
    @Test
    public void NegativeLoginTestBlankUsername() {
        //Checking title
        String expectedTitle = "Web Orders Login";
        String actualTitle = driver.getTitle();
        System.out.println("Expected Title: " + expectedTitle + " Actual Title: " + actualTitle);
        verifyTextMatches(expectedTitle, actualTitle);
        System.out.println("Step one Title Matched");

        //Getting current URL
        String Current1stURL = driver.getCurrentUrl();

        //Login in and clicking
        driver.findElement(By.xpath("//*[@name='ctl00$MainContent$username']")).sendKeys("");
        driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).sendKeys("Test");
        driver.findElement(By.xpath("//input[@name='ctl00$MainContent$login_button']")).click();

        //Checking title
        System.out.println("Expected Title: " + expectedTitle + " Actual Title: " + actualTitle);
        System.out.println("Step two Title Matched");
        verifyTextMatches(Current1stURL, driver.getCurrentUrl());
    }
    @Test
    public void NegativeLoginTestBlankPassword () {
        //Checking title
        String expectedTitle = "Web Orders Login";
        String actualTitle = driver.getTitle();
        System.out.println("Expected Title: " + expectedTitle + " Actual Title: " + actualTitle);
        verifyTextMatches(expectedTitle, actualTitle);
        System.out.println("Step one Title Matched");

        //Getting current URL
        String Current1stURL = driver.getCurrentUrl();

        //Login in and clicking
        driver.findElement(By.xpath("//*[@name='ctl00$MainContent$username']")).sendKeys("Tester");
        driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).sendKeys("");
        driver.findElement(By.xpath("//input[@name='ctl00$MainContent$login_button']")).click();

        //Checking title
        System.out.println("Expected Title: " + expectedTitle + " Actual Title: " + actualTitle);
        System.out.println("Step two Title Matched");
        verifyTextMatches(Current1stURL, driver.getCurrentUrl());
    }
}
