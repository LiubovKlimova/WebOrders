package com.WebOrders;

import com.github.javafaker.Faker;
import com.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Add_Added_Order extends TestBase {
    Faker faker = new Faker();
    Random random = new Random();
    int quantity;
    String fakeName;

    public void login() {
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test" + Keys.ENTER);
    }

    @Test
    public void Products() throws Exception{
        login();
     driver.findElement(By.xpath("//*[.='View all products']")).click();

     //find  productName
        List<WebElement> cellNames = driver.findElements(By.xpath("//table[@class='ProductsTable']//tbody/tr/td[1]"));

        List<String> products = new ArrayList<String>();

        for (WebElement cell: cellNames) {
            products.add(cell.getText());
        }


        driver.findElement(By.xpath("//*[.='View all orders']")) .click();

        List<WebElement> cellNames2 = driver.findElements(By.xpath("//table[@class='SampleTable']//tbody/tr[2]/td[3]"));

        Thread.sleep(1000);

        for (WebElement c: cellNames2) {
            Assert.assertTrue(products.contains(c.getText()));
        }
    }
    @Test
    public void createOrder() throws Exception{
        List<String> userInfo = new ArrayList<String>();
        login();
        driver.findElement(By.xpath("//*[.='Order']")).click();


        //select different product
        WebElement selectElement = driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));



        //2. create select object using the webelement
        Select list = new Select(selectElement);
        List<WebElement> options = list.getOptions();
        int size = options.size();
        System.out.println(size);
        for (WebElement option:options) {
            System.out.println(option.getText());
        }

        Thread.sleep(500);

        Random randomProduct = new Random();


        list.selectByIndex(randomProduct.nextInt(size));

        //quantity
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).click();
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).clear();
        Thread.sleep(1000);
        quantity = random.nextInt(100)+1;
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(""+quantity);
        Thread.sleep(500);

        //name
        WebElement fullname = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName"));
        String fakeName = faker.name().fullName();
        fullname.sendKeys(fakeName);
        userInfo.add(fakeName);
        Thread.sleep(500);
        //street
        WebElement street = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2"));
        String fakeStreet = faker.address().streetAddress();
        street.sendKeys(fakeStreet);
        userInfo.add(fakeStreet);
        Thread.sleep(500);
        //city
        WebElement city = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3"));
        String fakeCity = faker.address().cityName();
        city.sendKeys(fakeCity);
        userInfo.add(fakeCity);
        Thread.sleep(500);
        //state
        WebElement state = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4"));
        String fakeState = faker.address().state();
        state.sendKeys(fakeState);
        userInfo.add(fakeState);
        Thread.sleep(500);
        //zipcode
        WebElement zip = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5"));
        String fakeZip = faker.address().zipCode().substring(0,5);
        zip.sendKeys(fakeZip);
        userInfo.add(fakeZip);
        Thread.sleep(500);

        //cards type

        List <WebElement> cards = driver.findElements(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_cardList\"]/tbody/tr/td"));
        //*[@id="ctl00_MainContent_fmwOrder_cardList"]/tbody/tr/td
        int cardTypes = cards.size();//3
        System.out.println(cardTypes);
        int optionCard = random.nextInt(cardTypes-1);
        System.out.println(optionCard);
        userInfo.add(driver.findElement(By.xpath("//*[@id='ctl00_MainContent_fmwOrder_cardList_"+(optionCard)+"']"))
                .getAttribute("value"));


                //(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_cardList\"]/tbody/tr/td["+(optionCard)+"]")).getText());


        driver.findElement(By.xpath("//*[@id='ctl00_MainContent_fmwOrder_cardList_"+(optionCard)+"']")).click();
        Thread.sleep(500);

        //card number
        WebElement card = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6"));
        String fakeCard = faker.business().creditCardNumber().replace("-","");
        card.sendKeys(fakeCard);
        userInfo.add(fakeCard);
        //expiration
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys("12/23");
        String expiration = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).getText();
        //userInfo.add(expiration);

        //click proceed
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
        Thread.sleep(500);
        //click View all orders
        driver.findElement(By.xpath("//*[.='View all orders']//a")).click();
        Thread.sleep(500);
        //verify that all the order details are correct

        List<WebElement> row = driver.findElements(By.xpath("//table[@id=\"ctl00_MainContent_orderGrid\"]/tbody/tr[2]/td"));


        int valid = 0;

        for(WebElement cell: row){
            for(String info : userInfo){
                if(cell.getText().equals(info)){
                    valid++;
                    System.out.println(info);
                }
            }
        }
        Thread.sleep(1000);
        System.out.println(valid);
        Assert.assertEquals(7,valid);
        //(//input[@type='checkbox'])[1]

    }
    @Test
    public void delete() throws Exception{
        login();
        List<WebElement> allRows = driver.findElements(By.xpath("(//input[@type='checkbox'])"));
        int rows = allRows.size();//8
        System.out.println(rows+"***");
        int randomRow = random.nextInt(rows)+1;
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//input[@type='checkbox'])["+(randomRow)+"]")).click();
        Thread.sleep(1000);


        String deletedName = driver.findElement
                (By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr["+(randomRow+1)+"]/td[2]")).getText();
        System.out.println(deletedName);


        driver.findElement(By.id("ctl00_MainContent_btnDelete")).click();
        List<WebElement> allRows2 = driver.findElements
                (By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr"));//9 cuz it prints headers as well
        int rows2 = allRows2.size();//8
        System.out.println("rows: "+rows+" rows2: "+rows2);
        Assert.assertEquals(rows,rows2);

        //verify Name column does not contain deleted person's name
        List<WebElement> namesColumn = driver.findElements
                (By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr/td[2]"));
        for (WebElement names : namesColumn) {
            Assert.assertFalse(deletedName.equals(names.getText()));
        }
    }

    @Test
    public void edit() throws Exception{
        login();
        //edit button
     List<WebElement> editButton = driver.findElements
             (By.xpath("(//input[@type='image'])"));
        Thread.sleep(1000);
     int buttons = editButton.size();

        System.out.println("buttons: "+buttons);//8
     int randomButton = random.nextInt(buttons)+1;
        Thread.sleep(1000);
     driver.findElement(By.xpath("(//input[@type='image'])["+(randomButton)+"]")).click();

        //quantity
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).click();
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).clear();
        Thread.sleep(1000);
        quantity = random.nextInt(100)+1;
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(""+quantity);
        Thread.sleep(500);

        //calculate
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(500);
        //verify quantity
        String verifyQuantity = driver.findElement
                (By.xpath("//input[@name='ctl00$MainContent$fmwOrder$txtQuantity']")).getAttribute("value");
        // System.out.println(verifyQuantity);
        int actualQuantity = Integer.parseInt(verifyQuantity);
        Thread.sleep(1000);
        System.out.println("quantity: "+quantity);
        System.out.println("actualQuantity: "+actualQuantity);
        Assert.assertEquals(quantity,actualQuantity);
        //click update
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_UpdateButton")).click();
        Thread.sleep(1000);

//        List <WebElement> cards = driver.findElements(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_cardList\"]/tbody/tr/td"));
//        int cardTypes = cards.size();//3
//        int optionCard = random.nextInt(cardTypes)+1;

        //verify new quantity is displayed
        List<WebElement> quantit = driver.findElements(By.xpath("//table[@class='SampleTable']//tbody/tr/td[4]"));
        for (WebElement q:quantit) {
            System.out.println(q.getText());
        }
        Thread.sleep(1000);
        System.out.println("@@@"+quantit.get(randomButton-1).getText());
        System.out.println("***"+verifyQuantity);
        Assert.assertEquals(quantit.get(randomButton-1).getText(),actualQuantity);
        Thread.sleep(1000);

    }
}
