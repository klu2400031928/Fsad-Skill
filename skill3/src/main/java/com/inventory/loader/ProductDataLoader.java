package com.inventory.loader;

import com.inventory.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.inventory.util.HibernateUtil;

/**
 * ProductDataLoader - Inserts sample product data into the database
 * Used for Skill 3 HQL operations
 */
public class ProductDataLoader {

    /**
     * Loads 8 sample products into the database
     * @param session Hibernate session
     */
    public static void loadSampleProducts(Session session) {
        Transaction transaction = session.beginTransaction();

        // Sample products
        session.save(new Product("Laptop", 899.99, 15, "Electronics"));
        session.save(new Product("Mouse", 25.50, 50, "Electronics"));
        session.save(new Product("Keyboard", 45.00, 30, "Electronics"));
        session.save(new Product("Monitor", 299.99, 20, "Electronics"));
        session.save(new Product("Desk Chair", 150.00, 0, "Furniture"));
        session.save(new Product("Desk Lamp", 35.75, 25, "Furniture"));
        session.save(new Product("Notebook", 5.99, 100, "Stationery"));
        session.save(new Product("Pen Set", 12.50, 75, "Stationery"));

        transaction.commit();
        System.out.println("Sample products added successfully!");
    }

    /**
     * Main method to execute data loading
     */
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        ProductDataLoader.loadSampleProducts(session);

        session.close();
        factory.close();
    }
}

