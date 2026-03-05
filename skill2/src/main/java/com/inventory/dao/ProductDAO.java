package com.inventory.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;

/**
 * Data Access Object for Product entity
 * Implements CRUD operations using Hibernate
 */
public class ProductDAO {

    /**
     * Insert a new product into the database
     * @param product The product object to be saved
     */
    public void saveProduct(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(product);
            tx.commit();
            System.out.println("Product saved successfully: " + product.getName());
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Retrieve a product by its ID
     * @param id The ID of the product to retrieve
     * @return The Product object if found, null otherwise
     */
    public Product getProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product product = null;
        try {
            product = session.get(Product.class, id);
            if (product != null) {
                System.out.println("Product found: " + product);
            } else {
                System.out.println("Product with ID " + id + " not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return product;
    }

    /**
     * Retrieve a product by its ID (using load method - returns proxy)
     * @param id The ID of the product to retrieve
     * @return The Product proxy object
     */
    public Product getProductByLoad(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product product = null;
        try {
            product = session.load(Product.class, id);
            // Note: load() returns a proxy, actual DB query is executed when accessed
            System.out.println("Product loaded (lazy): " + product.getClass().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return product;
    }

    /**
     * Update price and quantity of a product
     * @param id The ID of the product to update
     * @param price The new price
     * @param quantity The new quantity
     */
    public void updateProduct(int id, double price, int quantity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Product product = session.get(Product.class, id);
            if (product != null) {
                product.setPrice(price);
                product.setQuantity(quantity);
                session.update(product);
                tx.commit();
                System.out.println("Product updated successfully - ID: " + id + 
                                   ", New Price: " + price + ", New Quantity: " + quantity);
            } else {
                System.out.println("Product with ID " + id + " not found for update");
                tx.rollback();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Update only the price of a product
     * @param id The ID of the product to update
     * @param price The new price
     */
    public void updatePrice(int id, double price) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Product product = session.get(Product.class, id);
            if (product != null) {
                product.setPrice(price);
                session.update(product);
                tx.commit();
                System.out.println("Price updated successfully for product ID: " + id);
            } else {
                System.out.println("Product with ID " + id + " not found");
                tx.rollback();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Update only the quantity of a product
     * @param id The ID of the product to update
     * @param quantity The new quantity
     */
    public void updateQuantity(int id, int quantity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Product product = session.get(Product.class, id);
            if (product != null) {
                product.setQuantity(quantity);
                session.update(product);
                tx.commit();
                System.out.println("Quantity updated successfully for product ID: " + id);
            } else {
                System.out.println("Product with ID " + id + " not found");
                tx.rollback();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Delete a product by its ID
     * @param id The ID of the product to delete
     */
    public void deleteProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.delete(product);
                tx.commit();
                System.out.println("Product deleted successfully - ID: " + id);
            } else {
                System.out.println("Product with ID " + id + " not found for deletion");
                tx.rollback();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Retrieve all products from the database
     * @return List of all products
     */
    public java.util.List<Product> getAllProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        java.util.List<Product> products = null;
        try {
            products = session.createQuery("FROM Product", Product.class).getResultList();
            System.out.println("Total products found: " + products.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }
}

