package SimpleStore.controller;

import SimpleStore.model.Product;
import SimpleStore.relations.CartItems;
import SimpleStore.relations.Carts;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {
    private final CartItems cartItems = new CartItems();
    private final Carts carts = new Carts();

    @GetMapping("/{userId}")
    public List<Product> getAllCartProducts(@PathVariable int userId) throws SQLException {
        return cartItems.getCartItems(carts.findCartId(userId));
    }

    @PostMapping
    public void addToCart(@RequestBody CartProductRequest request) throws SQLException {
        cartItems.insert(request.getUserId(), request.getProductId(), request.getQuantity());
    }

    @PostMapping("/update")
    public void updateCart(@RequestBody CartProductRequest request) throws SQLException {
        cartItems.updateItemQuantity(request.getUserId(), request.getProductId(), request.getQuantity());
    }

    @PostMapping("/delete")
    public void deleteFromCart(@RequestBody CartProductRequest request) throws SQLException {
        cartItems.deleteProduct(request.getUserId(), request.getProductId());
    }

    public static class CartProductRequest {
        private int userId;
        private int productId;
        private int quantity;

        public int getUserId() {
            return userId;
        }

        public int getProductId() {
            return productId;
        }
        public int getQuantity() {
            return quantity;
        }
    }
}
