import NavBar from "../components/NavBar.jsx";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import {useEffect, useState} from "react";

// Receives array of products and their quantity
function Cart({userId, cart}) {
  // Placeholder for future cart items array

  // const isEmpty = cart.length === 0;

  const [savedCart, setSavedCart] = useState([]);

  const isEmpty = savedCart.length === 0;

    useEffect(() => {
        fetchSavedItems()
    }, [userId]);

    async function fetchSavedItems() {
        if (!userId) {
            return;
        }
        try {
            const response = await fetch(`http://localhost:8080/api/carts/${userId}`);
            const data = await response.json();
            setSavedCart(data);
        }
        catch(error) {
            console.error('Failed to save cart', error)
        }
    }

    async function deleteFromSavedCart(productId){
      if (!userId) return;
      try {
        const response = await fetch("http://localhost:8080/api/carts/delete", {
              method: "POST",
              headers: {"Content-Type": "application/json"},
              body: JSON.stringify({userId: userId, productId: productId})
          });
        // Refresh list
        await fetchSavedItems();
      } catch (err) {
        console.error('Failed to delete cart item', err);
      }
    }

    async function updateFromSavedCart(productId, quantity){
      if (!userId) return;
      try {
        //alert("Quantity:" + quantity);
        const response = await fetch("http://localhost:8080/api/carts/update", {
              method: "POST",
              headers: {"Content-Type": "application/json"},
              body: JSON.stringify({userId: userId, productId: productId, quantity: quantity})
          });
        // Refresh list
        await fetchSavedItems();
      } catch (err) {
        console.error('Failed to update cart item', err);
      }
    }

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
          {!isEmpty &&
              (savedCart.map((item) => (
                  <Box key={item.product_id} sx={{mb: 1}}>
                      {item.name} — Qty: {item.qty} — ${item.price} each — Total: $
                      {(item.price * item.qty).toFixed(2)}
                      <select 
                            value={item.qty}
                            onChange={e =>
                                updateFromSavedCart(item.product_id, e.target.value)
                            }>
                            {[1,2,3,4,5,6,7,8,9,10].map(n => (
                            <option key={n} value={n}>{n}</option>
                            ))}
                        </select>
                      
                      <button onClick={e => deleteFromSavedCart(item.product_id)}>Delete From Cart</button>
                  </Box>
              )))
          }

         {/*{*/}
         {/* cart.map((item) => (*/}
         {/*   <Box key={item.product_id} sx={{mb: 1}}>*/}
         {/*     {item.product.name} — Qty: {item.qty} — ${item.product.price} each — Total: $*/}
         {/*     {(item.product.price * item.qty).toFixed(2)}*/}
         {/*   </Box>*/}
         {/* ))*/}
         {/*}*/}
         {!isEmpty && (
             <Button variant="contained">Order Products</Button>
         )}
      </Box>
    </>
  );
}

export default Cart;