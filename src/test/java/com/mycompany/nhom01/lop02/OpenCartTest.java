/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nhom01.lop02;

import java.util.List;
import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author ADMIN
 */
public class OpenCartTest {
    private WebDriver driver;
    
    @BeforeMethod
    public void setUp() {
        driver = new EdgeDriver();
        driver.get("https://demo.opencart.com/");
    }
    
    @AfterMethod
    public void testHomePageTitle() {
        String expectedTitle = "Open Cart";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }
    
    @AfterClass
    public void closeBrowser() throws Exception {
        driver.quit();
    }
    
    @Test
    public void testSearchProduct() throws InterruptedExceptubion {
        WebElement searchBox = driver.findElement(By.name("search"));
        searchBox.sendKeys("MacBook");
        searchBox.submit();
        
        List<WebElement> products = (List<WebElement>) driver.findElement(By.cssSelector(".product-layout"));
        
        int actualProductCount = products.size();
        
        int expectedProductCount = 1;
        Assert.assertEquals("Số lượng sản phẩm không đúng!", actualProductCount, expectedProductCount);
        Thread.sleep(5000);
    }
}
