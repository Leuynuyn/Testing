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

    @Test
    public void test_TC_UI_01() {
        String expectedTitle = "Your Store";
        String actualTitle = driver.getTitle();
        Assert.assertEquals("Tiêu đề trang không khớp!", expectedTitle, actualTitle);
    }

    @Test
    public void test_TC_Fn_01() throws InterruptedException {
        WebElement searchBox = driver.findElement(By.name("search"));
        searchBox.sendKeys("MacBook");
        searchBox.submit();

        Thread.sleep(2000); // Chờ trang load

        List<WebElement> products = driver.findElements(By.cssSelector(".product-layout"));
        int actualProductCount = products.size();

        Assert.assertTrue("Không có sản phẩm nào được hiển thị!", actualProductCount > 0);
    }

    @AfterClass
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
