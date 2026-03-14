package klu.productapi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Derived Query Methods
    List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(double min, double max);

    // JPQL Query - Get products sorted by price
    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> getProductsSortedByPrice();

    // JPQL Query - Get products above a specific price
    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> getProductsAbovePrice(@Param("price") double price);

    // JPQL Query - Get products by category
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> getProductsByCategory(@Param("category") String category);
}
