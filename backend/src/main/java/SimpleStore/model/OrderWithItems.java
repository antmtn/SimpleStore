package SimpleStore.model;

import java.util.List;

public class OrderWithItems {
    private int orderId;
    private int customerId;
    private List<OrderItem> items;

    public OrderWithItems(int orderId, int customerId, List<OrderItem> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
