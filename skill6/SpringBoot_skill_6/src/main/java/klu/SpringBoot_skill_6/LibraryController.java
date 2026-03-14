package klu.SpringBoot_skill_6;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {

    @Autowired
    private BookRepository bookRepository;

    // 2. /welcome
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Online Library System with MySQL!";
    }

    // 3. /count
    @GetMapping("/count")
    public long getTotalBooks() {
        return bookRepository.count();
    }

    // 4. /price
    @GetMapping("/price")
    public double getBookPrice() {
        // Just a dummy logic or get the average/first price
        return 700;
    }

    // 5. /books
    @GetMapping("/books")
    public List<String> getBooks() {
        return bookRepository.findAll().stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    // 6. /books/{id}
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookRepository.findById(id).orElse(null);
    }

    // 7. /search?title=...
    @GetMapping("/search")
    public String searchBook(@RequestParam String title) {
        return "You searched for book: " + title;
    }

    // 8. /author/{name}
    @GetMapping("/author/{name}")
    public String getAuthor(@PathVariable String name) {
        return "Books written by author: " + name;
    }

    // 9. /addbook
    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        bookRepository.save(book);
        return "Book added to database successfully!";
    }

    // 10. /viewbooks
    @GetMapping("/viewbooks")
    public List<Book> viewBooks() {
        return bookRepository.findAll();
    }
}