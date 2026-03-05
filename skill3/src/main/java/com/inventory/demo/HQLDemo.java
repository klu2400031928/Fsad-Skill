package com.inventory.demo;

import com.inventory.entity.Product;
import com.inventory.loader.ProductDataLoader;
import com.inventory.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;

import java.util.List;

/**
 * HQLDemo - Demonstrates advanced HQL operations
 * Topic: Sorting, Pagination & Aggregates
 */
public class HQLDemo {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {
            // Uncomment to load sample data (run once)
            // ProductDataLoader.loadSampleProducts(session);
            
            // Clear session to avoid caching issues
            session.clear();

            // Task 3a: Sort by price ascending
            sortProductsByPriceAscending(session);

            // Task 3b: Sort by price descending
            sortProductsByPriceDescending(session);

            // Task 4: Sort by quantity
            sortProductsByQuantityDescending(session);

            // Task 5a: First 3 products
            getFirstThreeProducts(session);

            // Task 5b: Next 3 products
            getNextThreeProducts(session);

            // Task 5c: Generic pagination
            getPaginatedProducts(session, 2, 3);

            // Task 6a: Count total products
            countTotalProducts(session);

            // Task 6b: Count products in stock
            countProductsInStock(session);

            // Task 6c: Count by description
            countProductsByDescription(session);

            // Task 6d: Min/Max price
            findMinMaxPrice(session);

            // Task 7: Group by description
            groupProductsByDescription(session);

            // Task 7b: Group with aggregation
            groupProductsWithAggregation(session);

            // Task 8: Price range filter
            filterProductsByPriceRange(session, 20.0, 100.0);

            // Task 9a: Names starting with 'D'
            findProductsStartingWith(session, "D");

            // Task 9b: Names ending with 'p'
            findProductsEndingWith(session, "p");

            // Task 9c: Names containing 'Desk'
            findProductsContaining(session, "Desk");

            // Task 9d: Names with exact length
            findProductsByNameLength(session, 5);
            findProductsByNameLength(session, 7);

        } finally {
            session.close();
            factory.close();
        }
    }

    // ============================================
    // TASK 3: SORTING OPERATIONS
    // ============================================

    /**
     * Task 3a: Sort Products by Price - Ascending Order
     */
    public static void sortProductsByPriceAscending(Session session) {
        String hql = "FROM Product p ORDER BY p.price ASC";
        Query<Product> query = session.createQuery(hql, Product.class);
        List<Product> products = query.list();

        System.out.println("\n=== Products Sorted by Price (Ascending) ===");
        for (Product product : products) {
            System.out.println(product.getName() + " - $" + product.getPrice());
        }
    }

    /**
     * Task 3b: Sort Products by Price - Descending Order
     */
    public static void sortProductsByPriceDescending(Session session) {
        String hql = "FROM Product p ORDER BY p.price DESC";
        Query<Product> query = session.createQuery(hql, Product.class);
        List<Product> products = query.list();

        System.out.println("\n=== Products Sorted by Price (Descending) ===");
        for (Product product : products) {
            System.out.println(product.getName() + " - $" + product.getPrice());
        }
    }

    /**
     * Task 4: Sort Products by Quantity (Highest First)
     */
    public static void sortProductsByQuantityDescending(Session session) {
        String hql = "FROM Product p ORDER BY p.quantity DESC";
        Query<Product> query = session.createQuery(hql, Product.class);
        List<Product> products = query.list();

        System.out.println("\n=== Products Sorted by Quantity (Highest First) ===");
        for (Product product : products) {
            System.out.println(product.getName() + " - Quantity: " + product.getQuantity());
        }
    }

    // ============================================
    // TASK 5: PAGINATION OPERATIONS
    // ============================================

    /**
     * Task 5a: Get First 3 Products
     */
    public static void getFirstThreeProducts(Session session) {
        String hql = "FROM Product p";
        Query<Product> query = session.createQuery(hql, Product.class);
        query.setFirstResult(0);  // Start from index 0
        query.setMaxResults(3);   // Get 3 records

        List<Product> products = query.list();

        System.out.println("\n=== First 3 Products ===");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    /**
     * Task 5b: Get Next 3 Products (starting from index 3)
     */
    public static void getNextThreeProducts(Session session) {
        String hql = "FROM Product p";
        Query<Product> query = session.createQuery(hql, Product.class);
        query.setFirstResult(3);  // Start from index 3 (skip first 3)
        query.setMaxResults(3);   // Get 3 records

        List<Product> products = query.list();

        System.out.println("\n=== Next 3 Products ===");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    /**
     * Task 5c: Generic Pagination Method
     */
    public static void getPaginatedProducts(Session session, int pageNumber, int pageSize) {
        String hql = "FROM Product p";
        Query<Product> query = session.createQuery(hql, Product.class);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);

        List<Product> products = query.list();

        System.out.println("\n=== Page " + pageNumber + " (Size: " + pageSize + ") ===");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    // ============================================
    // TASK 6: AGGREGATE OPERATIONS
    // ============================================

    /**
     * Task 6a: Count Total Number of Products
     */
    public static void countTotalProducts(Session session) {
        String hql = "SELECT COUNT(p) FROM Product p";
        Query<Long> query = session.createQuery(hql, Long.class);
        Long count = query.uniqueResult();

        System.out.println("\n=== Total Number of Products ===");
        System.out.println("Total Products: " + count);
    }

    /**
     * Task 6b: Count Products Where Quantity > 0
     */
    public static void countProductsInStock(Session session) {
        String hql = "SELECT COUNT(p) FROM Product p WHERE p.quantity > 0";
        Query<Long> query = session.createQuery(hql, Long.class);
        Long count = query.uniqueResult();

        System.out.println("\n=== Products in Stock (Quantity > 0) ===");
        System.out.println("Products in Stock: " + count);
    }

    /**
     * Task 6c: Count Products Grouped by Description
     */
    public static void countProductsByDescription(Session session) {
        String hql = "SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description";
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        List<Object[]> results = query.list();

        System.out.println("\n=== Products Grouped by Description ===");
        for (Object[] result : results) {
            String description = (String) result[0];
            Long count = (Long) result[1];
            System.out.println(description + ": " + count + " products");
        }
    }

    /**
     * Task 6d: Find Minimum and Maximum Price
     */
    public static void findMinMaxPrice(Session session) {
        String hql = "SELECT MIN(p.price), MAX(p.price) FROM Product p";
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        Object[] result = query.uniqueResult();

        Double minPrice = (Double) result[0];
        Double maxPrice = (Double) result[1];

        System.out.println("\n=== Price Range ===");
        System.out.println("Minimum Price: $" + minPrice);
        System.out.println("Maximum Price: $" + maxPrice);
    }

    // ============================================
    // TASK 7: GROUP BY OPERATIONS
    // ============================================

    /**
     * Task 7a: Group Products by Description
     */
    public static void groupProductsByDescription(Session session) {
        String hql = "SELECT p.description, p.name, p.price FROM Product p ORDER BY p.description";
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        List<Object[]> results = query.list();

        System.out.println("\n=== Products Grouped by Description ===");
        String currentDescription = "";
        for (Object[] result : results) {
            String description = (String) result[0];
            String name = (String) result[1];
            Double price = (Double) result[2];

            if (!description.equals(currentDescription)) {
                System.out.println("\n" + description + ":");
                currentDescription = description;
            }
            System.out.println("  - " + name + " ($" + price + ")");
        }
    }

    /**
     * Task 7b: Group with Aggregation (COUNT, AVG, SUM)
     */
    public static void groupProductsWithAggregation(Session session) {
        String hql = "SELECT p.description, COUNT(p), AVG(p.price), SUM(p.quantity) " +
                     "FROM Product p GROUP BY p.description";
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        List<Object[]> results = query.list();

        System.out.println("\n=== Product Statistics by Category ===");
        for (Object[] result : results) {
            String description = (String) result[0];
            Long count = (Long) result[1];
            Double avgPrice = (Double) result[2];
            Long totalQuantity = (Long) result[3];

            System.out.println(description + ":");
            System.out.println("  Count: " + count);
            System.out.println("  Avg Price: $" + String.format("%.2f", avgPrice));
            System.out.println("  Total Quantity: " + totalQuantity);
        }
    }

    // ============================================
    // TASK 8: FILTER PRODUCTS WITHIN A PRICE RANGE
    // ============================================

    /**
     * Task 8: Filter Products Within a Price Range
     */
    public static void filterProductsByPriceRange(Session session, double minPrice, double maxPrice) {
        String hql = "FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice";
        Query<Product> query = session.createQuery(hql, Product.class);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);

        List<Product> products = query.list();

        System.out.println("\n=== Products Between $" + minPrice + " and $" + maxPrice + " ===");
        for (Product product : products) {
            System.out.println(product.getName() + " - $" + product.getPrice());
        }
    }

    // ============================================
    // TASK 9: HQL QUERIES USING LIKE
    // ============================================

    /**
     * Task 9a: Names Starting with Certain Letters
     */
    public static void findProductsStartingWith(Session session, String prefix) {
        String hql = "FROM Product p WHERE p.name LIKE :pattern";
        Query<Product> query = session.createQuery(hql, Product.class);
        query.setParameter("pattern", prefix + "%");

        List<Product> products = query.list();

        System.out.println("\n=== Products Starting with '" + prefix + "' ===");
        for (Product product : products) {
            System.out.println(product.getName());
        }
    }

    /**
     * Task 9b: Names Ending with Certain Letters
     */
    public static void findProductsEndingWith(Session session, String suffix) {
        String hql = "FROM Product p WHERE p.name LIKE :pattern";
        Query<Product> query = session.createQuery(hql, Product.class);
        query.setParameter("pattern", "%" + suffix);

        List<Product> products = query.list();

        System.out.println("\n=== Products Ending with '" + suffix + "' ===");
        for (Product product : products) {
            System.out.println(product.getName());
        }
    }

    /**
     * Task 9c: Names Containing a Pattern Anywhere (Substring)
     */
    public static void findProductsContaining(Session session, String substring) {
        String hql = "FROM Product p WHERE p.name LIKE :pattern";
        Query<Product> query = session.createQuery(hql, Product.class);
        query.setParameter("pattern", "%" + substring + "%");

        List<Product> products = query.list();

        System.out.println("\n=== Products Containing '" + substring + "' ===");
        for (Product product : products) {
            System.out.println(product.getName());
        }
    }

    /**
     * Task 9d: Names with an Exact Character Length (using LENGTH)
     */
    public static void findProductsByNameLength(Session session, int length) {
        String hql = "FROM Product p WHERE LENGTH(p.name) = :length";
        Query<Product> query = session.createQuery(hql, Product.class);
        query.setParameter("length", length);

        List<Product> products = query.list();

        System.out.println("\n=== Products with Name Length " + length + " ===");
        for (Product product : products) {
            System.out.println(product.getName() + " (Length: " + product.getName().length() + ")");
        }
    }

    /**
     * Task 9d Alternative: Using LIKE with underscores for exact length
     */
    public static void findProductsByExactLengthPattern(Session session, int length) {
        StringBuilder pattern = new StringBuilder();
        for (int i = 0; i < length; i++) {
            pattern.append("_");
        }

        String hql = "FROM Product p WHERE p.name LIKE :pattern";
        Query<Product> query = session.createQuery(hql, Product.class);
        query.setParameter("pattern", pattern.toString());

        List<Product> products = query.list();

        System.out.println("\n=== Products with Exactly " + length + " Characters ===");
        for (Product product : products) {
            System.out.println(product.getName());
        }
    }
}

