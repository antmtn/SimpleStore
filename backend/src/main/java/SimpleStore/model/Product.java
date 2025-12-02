package SimpleStore.model;

import jakarta.persistence.*;
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;
    private String name;
    private double price;
    private int qty;
    private String image;

    public Product(int productId, String name, double price, int qty, String image) {
        this.product_id = productId;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.image = image;
    }

    public Product() {

    }
    public int getProduct_id() {
        return product_id;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public String getImage() {
        return image;
    }
}