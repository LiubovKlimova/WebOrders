package com.utilities;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestBase {

        protected WebDriver driver;
        protected Actions actions;
        protected SoftAssert softAssert;
        protected Random random = new Random();


        @BeforeMethod
        public void setUpMethod() {

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            //driver = Driver.getDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            actions = new Actions(driver);
            softAssert = new SoftAssert();
            driver.manage().window().maximize();
            Faker faker = new Faker();


        }

        @AfterMethod
        public void tearDownMethod() {
            //Driver.closeDriver();

//            try{
//                Thread.sleep(7000);
//            }
//            catch(InterruptedException ie){
//            }
//             driver.quit();
            softAssert.assertAll();
        }
    }

