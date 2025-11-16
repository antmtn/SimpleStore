package SimpleStore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import SimpleStore.relations.Products;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class Controller {

    private final Products products = new Products();

    @GetMapping("/")
    public String helloWorld(){
        return "Hello World!";
    }

    @GetMapping("/init")
    public String init() throws Exception {
        products.createTable();
        products.insert("001", "chocolate", 3.99, 5);
        return products.getNames();
    }
}
