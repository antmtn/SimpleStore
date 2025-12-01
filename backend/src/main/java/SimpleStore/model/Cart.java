package SimpleStore.model;

import jakarta.persistence.*;
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cart_id;
    private int user_id;

    public Cart(int cart_id, int user_id) {
        this.cart_id = cart_id;
        this.user_id = user_id;
    }

    public Cart() {}

    public int getUser_id() {
        return user_id;
    }

    public int getCart_id() {
        return cart_id;
    }
}
