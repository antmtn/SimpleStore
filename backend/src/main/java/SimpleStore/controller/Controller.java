package SimpleStore.controller;

import SimpleStore.relations.Users;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import SimpleStore.relations.Products;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class Controller {

    private final Products products = new Products();
    private final Users users = new Users();

    @GetMapping("/")
    public String helloWorld(){
        return "Hello World!";
    }

    @GetMapping("/init")
    public String init() throws Exception {
        products.createTable();
        users.createTable();
        products.insert("chocolate", 3.99, 5, "https://cdn11.bigcommerce.com/s-qbjojecpaq/images/stencil/1280x1280/products/442/1968/Untitled_design_-_2023-06-12T155310.493__67118.1686599665.png?c=1");
        products.insert("shirt", 4.99, 2, "https://m.media-amazon.com/images/I/81-pkxp9h-L._AC_UY1000_.jpg");
        products.insert("sweater", 13.99, 5, "https://cdni.llbean.net/is/image/wim/505183_1155_41?hei=1095&wid=950&resMode=sharp2&defaultImage=llbprod/505183_0_44");
        products.insert("shoes", 49.99, 2, "https://www.converse.com/dw/image/v2/BCZC_PRD/on/demandware.static/-/Sites-cnv-master-catalog/default/dw96d51de8/images/a_08/M9160_A_08X1.jpg?sw=406&strip=false");
        products.insert("water bottle", 10.99, 5, "https://target.scene7.com/is/image/Target/GUEST_5ad40e9f-b69c-45c5-9d3d-7b2704314d2d");
        products.insert("charger", 14.99, 2, "https://m.media-amazon.com/images/I/41MAlBJ6BhL._AC_UF1000,1000_QL80_.jpg");
        products.insert("backpack", 30.99, 5, "https://solo-ny.com/cdn/shop/files/UBN795-4_HO_887bba68-33a4-48d5-a1b6-b5947c6164ac.jpg?v=1695140976");
        products.insert("laptop", 499.99, 2, "https://i5.walmartimages.com/seo/HP-15-6-Touch-Screen-Laptop-Intel-Core-i3-8GB-Memory-256GB-SSD-Silver-Notebook-PC_435571a0-a644-4f27-9e39-19e6c16f76c0.74549be46a6830f4a58dcf9502abc982.jpeg");
        products.insert("table", 30.99, 5, "https://www.ikea.com/us/en/images/products/mittzon-conference-table-round-birch-veneer-black__1212761_pe910753_s5.jpg");
        products.insert("chiikawa", 14.99, 2, "https://cdn11.bigcommerce.com/s-89ffd/images/stencil/1280x1280/products/157207/639533/sun-arrow-chiikawa-potetama-plush-chiikawa__66691.1737445026.jpg?c=2?imbypass=on");

        return products.getNames();
    }
}
