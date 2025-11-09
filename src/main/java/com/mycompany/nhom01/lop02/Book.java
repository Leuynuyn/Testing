/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nhom01.lop02;

/**
 *
 * @author ADMIN
 */
public class Book {
    private String title;
    private String author;
    private double price;
    private int stock;

    // Constructor
    public Book(String title, String author, double price, int stock) {
        this.title = title.trim(); 
        this.author = author.toUpperCase(); 
        if (price < 0) {
            this.price = 0.0;
        } else {
            this.price = price;
        }
        this.stock = Math.max(stock, 0); 
    }

    // Getter/Setter 
    public String getTitle() {
        return title + " (Book)"; 
    }

    public void setTitle(String title) {
        this.title = title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author.toUpperCase();
    }

    public double getPrice() {
        if (stock > 50) {
            return price * 0.9; 
        }
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        }
    }

    // Phương thức void 
    public void restock(int amount) {
        if (amount > 0) {
            stock += amount;
        }
    }

    public void sell(int quantity) {
        if (quantity <= stock) {
            stock -= quantity;
        } else {
            System.out.println("Không đủ hàng để bán");
        }
    }

    // Phương thức có kiểu trả về 
    public double calculateDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            return price; 
        }
        return price * (1 - percent / 100);
    }

    public boolean isAvailable() {
        return stock > 0; 
    }

    // Phương thức có xử lý Exception 
    public void updatePrice(double newPrice) throws IllegalArgumentException {
        if (newPrice < 0) {
            throw new IllegalArgumentException("Giá sách không được âm!");
        }
        this.price = newPrice;
    }

    public void removeStock(int quantity) throws Exception {
        if (quantity > stock) {
            throw new Exception("Không thể xóa nhiều hơn số lượng tồn kho!");
        }
        stock -= quantity;
    }

    // Phương thức chứa biểu thức điều kiện từ 2 toán hạng trở lên
    public String determineOffer() {
        if (price > 150 && stock > 20) {
            return "Giảm giá 15%";
        }
        else if (price <= 150 && stock > 20) {
            return "Giảm giá 5%";
        }
        else if (price > 150 && stock <= 20) {
            return "Khuyến mại theo nhu cầu";
        }
        else {
            return "Không giảm giá";
        }
    }
}
