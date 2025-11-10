/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.nhom01.lop02;

import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * JUnit test Book.java
 */
@RunWith(Parameterized.class)
public class BookTest {
    
    private double inputPrice;
    private int inputStock;
    private double expectedPrice;
    
    private static int runCount = 0;
    private Book book;
    
    // Constructor cho Parameterized Test (TC05, TC06)
    public BookTest(double inputPrice, int inputStock, double expectedPrice) {
        this.inputPrice = inputPrice;
        this.inputStock = inputStock;
        this.expectedPrice = expectedPrice;
    }
    
    // Tạo danh sách data cho Parameterized test
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
            {100, 50, 100},    // stock <= 50
            {100, 60, 90}      // stock > 50
        });
    }
    
    @BeforeClass
    public static void beforeAll(){
        System.out.println("---- START TEST BOOK CLASS ----");
    }
    
    @AfterClass
    public static void afterAll(){
        System.out.println("---- END TEST BOOK CLASS ----");
    }

    @Before
    public void setUp(){
        book = new Book("  Java  ", "James", inputPrice, inputStock);
        runCount++;
        System.out.println("Run test #" + runCount);
    }

    @After
    public void tearDown(){
        book = null;
    }

    //-------Constructor Test---------
    @Test
    public void testTC01() {
        Book b = new Book("  Java  ", "James", 100, 5);
        assertEquals("Java (Book)", b.getTitle());
        assertEquals("JAMES", b.getAuthor());
        assertEquals(100, b.getPrice(), 0.01);
        assertEquals(5, b.getStock());
    }
    
    @Test
    public void testTC02() {
        Book b = new Book("Book", "Ken", -10, 10);
        assertEquals(0.0, b.getPrice(), 0.01);
    }
    
    @Test
    public void testTC03() {
        Book b = new Book("Book", "Ken", 50, -2);
        assertEquals(0, b.getStock());
    }

    // Testcase fail
    @Test
    public void testTC04() {
        Book b = new Book("Book", "Ken", -5, -1);
        assertEquals(5.0, b.getPrice(), 0.01);  
    }

    //-------Getter/Setter(Parameterized)---------
    @Test
    public void testTC05_TC06_GetPrice_Parameterized() {
        assertEquals(expectedPrice, book.getPrice(), 0.01);
    }

    @Test
    public void testTC07_SetAuthor_Uppercase() {
        book.setAuthor("mary");
        assertEquals("MARY", book.getAuthor());
    }

    // Testcase fail
    @Test
    public void testTC08_SetPrice_Negative() {
        book.setPrice(100);
        book.setPrice(-10); 
        assertEquals(-10, book.getPrice(), 0.01);
    }
    
    //-------Method A: restock()---------
    @Test
    public void testTC09_Restock_Valid() {
        book.setStock(10);
        book.restock(5);
        assertEquals(15, book.getStock());
    }

    @Test
    public void testTC10_Restock_Negative() {
        book.setStock(10);
        book.restock(-3);
        assertEquals(10, book.getStock());
    }
    
    //---------Method B: calculateDiscount()----------
    @Test
    public void testTC11_CalculateDiscount_Normal() {
        book.setPrice(100);
        assertEquals(80.0, book.calculateDiscount(20), 0.01);
    }

    @Test
    public void testTC12_CalculateDiscount_NegativePercent() {
        book.setPrice(100);
        assertEquals(100.0, book.calculateDiscount(-5), 0.01);
    }

    // Testcase fail
    @Test
    public void testTC13_CalculateDiscount_Over100Percent() {
        book.setPrice(100);
        assertEquals(50.0, book.calculateDiscount(150), 0.01);
    }
    
    //---------Method C: updatePrice()------------
       @Test
    public void testTC14_UpdatePrice_Valid() {
        book.updatePrice(120);
        assertEquals(120, book.getPrice(), 0.01);
    }

    @Test
    public void testTC15_UpdatePrice_Exception() {
        try {
            book.updatePrice(-10);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Giá sách không được âm!", e.getMessage());
        }
    }
}
