package SimpleStore.controller;

import SimpleStore.relations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class Controller {

    private final Products products = new Products();
    private final Users users = new Users();
    private final Carts carts = new Carts();
    private final CartItems cartItems = new CartItems();
    private final Orders orders = new Orders();
    private final OrderItems orderItems = new OrderItems();

    @GetMapping("/")
    public String helloWorld(){
        return "Hello World!";
    }

    @GetMapping("/init")
    public String init() throws Exception {
        products.createTable();
        users.createTable();
        carts.createTable();
        cartItems.createTable();
        orders.createTable();
        orderItems.createTable();


        // products
        int chocolateId = products.insert("chocolate", 3.99, 5, "https://cdn11.bigcommerce.com/s-qbjojecpaq/images/stencil/1280x1280/products/442/1968/Untitled_design_-_2023-06-12T155310.493__67118.1686599665.png?c=1");
        int shirtId = products.insert("shirt", 4.99, 10, "https://m.media-amazon.com/images/I/81-pkxp9h-L._AC_UY1000_.jpg");
        int sweaterId = products.insert("sweater", 13.99, 6, "https://cdni.llbean.net/is/image/wim/505183_1155_41?hei=1095&wid=950&resMode=sharp2&defaultImage=llbprod/505183_0_44");
        int shoesId = products.insert("shoes", 49.99, 2, "https://www.converse.com/dw/image/v2/BCZC_PRD/on/demandware.static/-/Sites-cnv-master-catalog/default/dw96d51de8/images/a_08/M9160_A_08X1.jpg?sw=406&strip=false");
        int waterId = products.insert("water bottle", 10.99, 100, "https://target.scene7.com/is/image/Target/GUEST_5ad40e9f-b69c-45c5-9d3d-7b2704314d2d");
        int chargerId = products.insert("charger", 14.99, 20, "https://m.media-amazon.com/images/I/41MAlBJ6BhL._AC_UF1000,1000_QL80_.jpg");
        int backpackId = products.insert("backpack", 30.99, 5, "https://solo-ny.com/cdn/shop/files/UBN795-4_HO_887bba68-33a4-48d5-a1b6-b5947c6164ac.jpg?v=1695140976");
        int laptopId = products.insert("laptop", 499.99, 6, "https://i5.walmartimages.com/seo/HP-15-6-Touch-Screen-Laptop-Intel-Core-i3-8GB-Memory-256GB-SSD-Silver-Notebook-PC_435571a0-a644-4f27-9e39-19e6c16f76c0.74549be46a6830f4a58dcf9502abc982.jpeg");
        int tableId =  products.insert("table", 30.99, 5, "https://www.ikea.com/us/en/images/products/mittzon-conference-table-round-birch-veneer-black__1212761_pe910753_s5.jpg");
        int chiikawaId = products.insert("chiikawa", 14.99, 30, "https://cdn11.bigcommerce.com/s-89ffd/images/stencil/1280x1280/products/157207/639533/sun-arrow-chiikawa-potetama-plush-chiikawa__66691.1737445026.jpg?c=2?imbypass=on");
        int notebookId = products.insert("notebook", 4.99, 8, "https://i5.walmartimages.com/seo/Pen-Gear-Wide-Ruled-1-Subject-Notebook-8-x-10-5-Blue-70-Sheets_d212f980-d46b-4e15-b468-f735ba53eb82.9118a770c90e4c2860b4effcfa52399d.jpeg");
        int headphonesId = products.insert("headphones", 19.99, 9, "https://i5.walmartimages.com/seo/VILINICE-Noise-Cancelling-Headphones-Wireless-Bluetooth-Over-Ear-Headphones-with-Microphone-Black-Q8_b994b99c-835f-42fc-8094-9f6be0f9273b.be59955399cdbd1c25011d4a4251ba9b.jpeg");
        int mugId = products.insert("mug", 35.99, 4, "https://cdn11.bigcommerce.com/s-dbdycdvfge/images/stencil/1000x1000/products/1253/1579/079-00072-II-2__69127.1650472389.jpg?c=1");
        int penId = products.insert("pen", 2.99, 40, "https://www.pentel.com/cdn/shop/products/glidewrite-signature-ballpoint-pen-3-pkbx930abp3a-337406.jpg?v=1757189011&width=1946");
        int keychainId = products.insert("keychain", 8.99, 23, "https://omgkawaii.com/cdn/shop/files/omgkawaii-keychains-adorable-bunny-keychain-40751376302293.jpg?v=1701391085&width=900");


        // users
        int mangoId = users.insert("mango", "zest77");
        int sky7Id = users.insert("sky7", "skypass");
        int miloId = users.insert("m1l0", "m1l0pw");
        int silverleafId = users.insert("silverleaf", "tinycloud");
        int nightbyteId = users.insert("nightbyte", "Passw0rd!");
        int u0 = users.insert("u0", "p0");
        int u1 = users.insert("u1", "p1");
        int u2 = users.insert("u2", "p2");
        int u3 = users.insert("u3", "p3");
        int u4 = users.insert("u4", "p4");
        int u5 = users.insert("u5", "p5");
        int u6 = users.insert("u6", "p6");
        int u7 = users.insert("u7", "p7");
        int u8 = users.insert("u8", "p8");
        int u9 = users.insert("u9", "p9");


        // carts
        carts.insert(mangoId);
        carts.insert(sky7Id);
        carts.insert(miloId);
        carts.insert(silverleafId);
        carts.insert(nightbyteId);
        carts.insert(u0);
        carts.insert(u1);
        carts.insert(u2);
        carts.insert(u3);
        carts.insert(u4);
        carts.insert(u5);
        carts.insert(u6);
        carts.insert(u7);
        carts.insert(u8);
        carts.insert(u9);


        // cartItems
        cartItems.insert(u0, chocolateId, 1);
        cartItems.insert(u0, shirtId, 2);
        cartItems.insert(u0, headphonesId, 1);
        cartItems.insert(u0, waterId, 4);

        cartItems.insert(u1, sweaterId, 1);
        cartItems.insert(u1, shoesId, 1);
        cartItems.insert(u1, chargerId, 1);
        cartItems.insert(u1, backpackId, 1);
        cartItems.insert(u1, laptopId, 1);
        cartItems.insert(u1, tableId, 1);
        cartItems.insert(u1, chiikawaId, 3);
        cartItems.insert(u1, keychainId, 3);

        cartItems.insert(u2, penId, 2);
        cartItems.insert(u2, notebookId, 2);

        cartItems.insert(u3, mugId, 1);

//        orders;
//        orderItems;

        StringBuilder sb = new StringBuilder();
        sb.append("Products: ").append(products.getNames()).append("<br><br>");
        sb.append("Users: ").append(users.getUsernames()).append("<br><br>");
        sb.append("Carts: ").append(carts.getUserCart()).append("<br><br>");
        sb.append("CartItems:<br>").append(cartItems.getCartNItems()).append("<br><br>");

        return sb.toString();
    }

    @GetMapping("/delete")
    public String delete() throws Exception {
        orderItems.deleteTable();
        orders.deleteTable();
        cartItems.deleteTable();
        carts.deleteTable();
        products.deleteTable();
        users.deleteTable();
        return "deleted all tables";
    }
}
