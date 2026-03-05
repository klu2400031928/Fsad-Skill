package com.inventory.entity;

import jakarta.persistence.*;

/**
 * Product Entity with IDENTITY Generation Strategy
 * ID values are auto-incremented by the database (MySQL AUTO_INCREMENT)
 */
@Entity
@Table(name = "products_identity")
public class ProductIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    public ProductIdentity() {
    }

    public ProductIdentity(String name, String description, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "ProductIdentity{id=" + id + ", name='" + name + "', price=" + price + ", quantity=" + quantity + "}";
    }
}

