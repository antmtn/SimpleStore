import NavBar from "../components/NavBar.jsx";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";

// Receives array of products and their quantity
function Cart({cart}) {
  // Placeholder for future cart items array

  const isEmpty = cart.length === 0;

  return (
    <>
      <NavBar />
      <Box sx={{ px: 2 }}>
        <Typography variant="h4" sx={{ mb: 2, fontWeight: 600 }}>
          Your Cart
        </Typography>
        {isEmpty && (
          <Typography variant="body1" sx={{ mb: 3 }}>
            Your cart is empty. Add products to see them here.
          </Typography>
        )}
        {isEmpty && (
          <Button variant="contained" href="/products">
            Browse Products
          </Button>
        )}

        {/* Displaying each item in the cart as a Box element */}
         {
          cart.map((item) => (
            <Box key={item.product_id} sx={{mb: 1}}>
              {item.product.name} — Qty: {item.qty} — ${item.product.price} each — Total: $
              {(item.product.price * item.qty).toFixed(2)}
            </Box>
          ))
         }
      </Box>
    </>
  );
}

export default Cart;