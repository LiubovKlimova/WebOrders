package com.tests;
import com.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WOPostiveLoginTests extends BrowserUtils {
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
    public void positiveLoginTest(){
        String actual= driver.getTitle();
        String expected ="Web Orders Login";
        System.out.println("Expected title: "+expected+" Actual title"+ actual);
        verifyTextMatches(expected,actual);
    }
    @Test
    public void enterUserName(){
     driver.findElement(By.xpath("/*[@name='ctl00$MainContent$username']")).sendKeys("Tester");
     driver.findElement(By.xpath("*[@name='ctl00$MainContent$password']")).sendKeys("test"+ Keys.ENTER);
     String expected = "Web Orders";
     String actual = driver.getTitle();
     verifyTextMatches(expected, actual);
        System.out.println("Title matched");
    }
    @Test
    public void verifyUrl(){
        driver.findElement(By.xpath("/*[@name='ctl00$MainContent$username']")).sendKeys("Tester");
        driver.findElement(By.xpath("*[@name='ctl00$MainContent$password']")).sendKeys("test"+ Keys.ENTER);
        verifyTextContains("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/",driver.getCurrentUrl());
        System.out.println("URL matched");
    }
}
