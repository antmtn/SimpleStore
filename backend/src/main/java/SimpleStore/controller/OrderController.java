package SimpleStore.controller;

import SimpleStore.model.Order;
import SimpleStore.model.OrderItem;
import SimpleStore.model.OrderWithItems;
import SimpleStore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import SimpleStore.relations.Orders;
import SimpleStore.relations.OrderItems;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    private final Orders orders = new Orders();
    private final OrderItems orderItems = new OrderItems();

//    @GetMapping("/{userId}")
//    public List<OrderWithItems> getAllOrdersWithItems(@PathVariable int userId) throws SQLException {
//        List<Order> ord = orders.getAllOrders();
//        List<OrderWithItems> result = new ArrayList<>();
//
//        for (Order order : ord) {
//            if (order.getCustomerId() == userId) {
//                List<OrderItem> items = orderItems.getOrderItems(order.getOrderId());
//                result.add(new OrderWithItems(order.getOrderId(), order.getCustomerId(), items));
//            }
//        }
//
//        return result;
//    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<Integer, List<Product>>> getAllOrdersWithItems(@PathVariable int userId) throws SQLException {
        List<Order> ord = orders.getAllOrders();
        Map<Integer, List<Product>> result = new HashMap<>();

        for (Order order : ord) {
            if (order.getCustomerId() == userId) {
                List<Product> items = orderItems.getOrderProducts(order.getOrderId());
                result.put(order.getOrderId(), items);
            }
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/ord")
    public List<Order> getAllOrders() throws SQLException {
        return orders.getAllOrders();
    }

    @GetMapping("/items")
    public List<OrderItem> getAllOrderItems() throws SQLException {
        return orderItems.getAllOrderItems();
    }

    @PostMapping
    public int insertOrder(@RequestBody Request request) throws SQLException {
        return orders.insertOrder(request.getUserId());
    }

    @PostMapping("/items")
    public void insertOrderItem(@RequestBody OrderItem orderItem) throws SQLException {
        orderItems.insert(orderItem.getOrderId(), orderItem.getProductId(), orderItem.getQuantity());
    }

    public static class Request {
        private int userId;
        public int getUserId() {
            return userId;
        }
    }

}