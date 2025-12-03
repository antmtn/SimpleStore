package SimpleStore.controller;

import SimpleStore.model.Order;
import SimpleStore.model.OrderItem;
import SimpleStore.model.OrderWithItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import SimpleStore.relations.Orders;
import SimpleStore.relations.OrderItems;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    private final Orders orders = new Orders();
    private final OrderItems orderItems = new OrderItems();

    @GetMapping("/{userId}")
    public List<OrderWithItems> getAllOrdersWithItems(@PathVariable int userId) throws SQLException {
        List<Order> ord = orders.getAllOrders();
        List<OrderWithItems> result = new ArrayList<>();

        for (Order order : ord) {
            List<OrderItem> items = orderItems.getOrderItems(order.getOrderId());
            result.add(new OrderWithItems(order.getOrderId(), order.getCustomerId(), items));
        }

        return result;
    }

    @GetMapping("/ord")
    public List<Order> getAllOrders() throws SQLException {
        return orders.getAllOrders();
    }

    @GetMapping("/items")
    public List<OrderItem> getAllOrderItems() throws SQLException {
        return orderItems.getAllOrderItems();
    }


}