package SimpleStore.controller;

import SimpleStore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import SimpleStore.relations.Products;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private final Products products = new Products();

    @GetMapping
    public List<Product> getAllProducts() throws SQLException {
        return products.getAll();
    }

    @GetMapping("/under")
    public List<Product> getProductsUnderPrice(@RequestParam("maxPrice") double maxPrice)
            throws SQLException {
        return products.getUnderPrice(maxPrice);
    }


    @PostMapping
    public void addProduct(@RequestBody String name, double price, int quantity, String image) throws SQLException {
        products.insert(name, price, quantity, image);
    }

}
