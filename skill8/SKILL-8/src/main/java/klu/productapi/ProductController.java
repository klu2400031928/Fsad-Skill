package klu.productapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    
    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return productRepository.findByCategory(category);
    }
 
    @GetMapping("/filter")
    public List<Product> getByPriceRange(@RequestParam double min, @RequestParam double max) {
        return productRepository.findByPriceBetween(min, max);
    }
  
    @GetMapping("/sorted")
    public List<Product> getSortedByPrice() {
        return productRepository.getProductsSortedByPrice();
    }

    @GetMapping("/expensive/{price}")
    public List<Product> getExpensiveProducts(@PathVariable double price) {
        return productRepository.getProductsAbovePrice(price);
    }
}
