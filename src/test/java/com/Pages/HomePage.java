package com.Pages;

import com.utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(){
        PageFactory.initElements(Driver.getDriver(),this);// this means current object
    }
}
