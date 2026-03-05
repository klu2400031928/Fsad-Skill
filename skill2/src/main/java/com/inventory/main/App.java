package com.inventory.main;

import com.inventory.dao.ProductDAO;
import com.inventory.entity.Product;

import java.util.List;

/**
 * Main Application class to demonstrate Hibernate CRUD operations
 * for the Retail Inventory System
 */
public class App {

    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("   Hibernate CRUD Operations Demo");
        System.out.println("   Retail Inventory System");
        System.out.println("===========================================\n");

        ProductDAO dao = new ProductDAO();

        // ============================================
        // CREATE: Insert multiple Product records
        // ============================================
        System.out.println("--- CREATE: Inserting Products ---\n");

        Product p1 = new Product("Laptop", "Gaming Laptop", 75000, 10);
        Product p2 = new Product("Mouse", "Wireless Mouse", 1200, 50);
        Product p3 = new Product("Keyboard", "Mechanical Keyboard", 3500, 25);
        Product p4 = new Product("Monitor", "27-inch 4K Monitor", 25000, 15);
        Product p5 = new Product("Headphones", "Noise Cancelling Headphones", 8000, 30);

        dao.saveProduct(p1);
        dao.saveProduct(p2);
        dao.saveProduct(p3);
        dao.saveProduct(p4);
        dao.saveProduct(p5);

        System.out.println("\n--- All Products After Insert ---\n");
        displayAllProducts(dao);

        // ============================================
        // READ: Retrieve a product using its id
        // ============================================
        System.out.println("\n--- READ: Retrieving Product by ID ---\n");

        Product retrievedProduct = dao.getProduct(1);
        if (retrievedProduct != null) {
            System.out.println("Retrieved Product: " + retrievedProduct);
        }

        // Demonstrate get() vs load() difference
        System.out.println("\n--- Demonstrating get() vs load() ---\n");
        System.out.println("Using get():");
        Product productGet = dao.getProduct(2);
        System.out.println("Result: " + productGet);

        System.out.println("\nUsing load() (returns proxy):");
        Product productLoad = dao.getProductByLoad(3);
        System.out.println("Result: " + productLoad);

        // ============================================
        // UPDATE: Update price and quantity
        // ============================================
        System.out.println("\n--- UPDATE: Updating Product Price and Quantity ---\n");

        // Update both price and quantity
        dao.updateProduct(1, 72000, 8);
        System.out.println("Updated product ID 1 (Laptop) - Price: 72000, Quantity: 8");

        // Update only price
        dao.updatePrice(2, 999);

        // Update only quantity
        dao.updateQuantity(3, 20);

        System.out.println("\n--- All Products After Update ---\n");
        displayAllProducts(dao);

        // ============================================
        // DELETE: Delete a discontinued product
        // ============================================
        System.out.println("\n--- DELETE: Deleting Discontinued Product ---\n");

        // Delete product with ID 5 (Headphones - discontinued)
        dao.deleteProduct(5);
        System.out.println("Deleted product ID 5 (Headphones) - Discontinued");

        System.out.println("\n--- All Products After Delete ---\n");
        displayAllProducts(dao);

        // ============================================
        // Additional Operations
        // ============================================
        System.out.println("\n--- Additional: Retrieving Non-existent Product ---\n");
        Product notFound = dao.getProduct(999);
        if (notFound == null) {
            System.out.println("Product with ID 999 not found (get() returns null)");
        }

        System.out.println("\n===========================================");
        System.out.println("   CRUD Operations Completed Successfully!");
        System.out.println("===========================================");

        // Close SessionFactory
        com.inventory.util.HibernateUtil.shutdown();
    }

    /**
     * Helper method to display all products
     */
    private static void displayAllProducts(ProductDAO dao) {
        List<Product> products = dao.getAllProducts();
        if (products != null && !products.isEmpty()) {
            System.out.println("ID\tName\t\tDescription\t\tPrice\tQuantity");
            System.out.println("----------------------------------------------------------------");
            for (Product p : products) {
                System.out.printf("%d\t%s\t\t%s\t\t%.2f\t%d%n",
                    p.getId(),
                    p.getName(),
                    p.getDescription(),
                    p.getPrice(),
                    p.getQuantity());
            }
        } else {
            System.out.println("No products found in the database.");
        }
    }
}

