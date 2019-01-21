package com.utilities;

import org.testng.Assert;

public class BrowserUtils {

    public static void verifyTextMatches(String one, String two){
        Assert.assertEquals(one,two);
        }
    public static void verifyTextContains(String a, String b){
        Assert.assertTrue(a.contains(b));
        }
}
