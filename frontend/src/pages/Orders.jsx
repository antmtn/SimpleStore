import NavBar from "../components/NavBar.jsx";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import {useEffect, useState} from "react";

function Orders({userId}) {
    // orders fetched from backend API
    const [orders, setOrders] = useState({});

    // Loads products in the beginning
    useEffect(() => {
      fetchOrders()
    }, [userId]);

    // Fetch to API
    const fetchOrders = async () => {
    if (!userId) {
        return;
    }
      try {
          const response = await fetch(`http://localhost:8080/api/orders/${userId}`);
          const data = await response.json();
          setOrders(data)

      }
      catch(error) {
          console.error('Failed to get orders', error)
      }
    }

    return (
        <>
        <NavBar />
        <h1 style={{ marginLeft: '16px' }}>Orders</h1>
        {Object.entries(orders).map(([orderId, products]) => (
                <Box key={orderId} sx={{mb: 4, borderBottom: "1px solid #ddd", pb: 2}}>
                <Typography variant="h6" sx={{ fontWeight: "600" }}>
                    Order ID: {orderId}
                </Typography>
                {products.map((product) => (
                    <Box key={product.product_id} sx={{mb: 4}}>
                    <p>Product Name: {product.name}</p>
                    <p>Price: {product.price.toFixed(2)}</p>
                    <p>Quantity: {product.qty}</p>
                    </Box>
                ))}
                    <p><strong>
                        Order Total: ${products.reduce((total, product) => total + (product.price * product.qty), 0).toFixed(2)}
                    </strong></p>
            </Box>
            ))}
        </>
    );
}

export default Orders;