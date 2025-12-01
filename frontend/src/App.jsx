import { useState } from 'react'
import { Route, Routes } from 'react-router-dom';
import Products from "./pages/Products.jsx";
import Home from "./pages/Home.jsx";
import Cart from "./pages/Cart.jsx";
import Signup from "./pages/Signup.jsx";
import Login from "./pages/Login.jsx";

function App() {
  // Cart holds items {product, qty}
  const [cart, setCart] = useState([]);
  const [userId, setUserId] = useState(() => {
      const savedId = localStorage.getItem("userId")
      return savedId ? JSON.parse(savedId) : null
  });

  // Add product to cart with selected quantity
  //If it exists alert user
  function addToCart(product, qty){
    setCart(prev => {
      for(let i = 0; i< prev.length; i++){
        if (prev[i].product.product_id == product.product_id){
          alert("Product is already in cart");
          return prev;
        }
      }
      return [...prev, { product, qty }];
    });
  }

  async function addToCartDB(productId, quantity) {
      try {
          const response = await fetch("http://localhost:8080/api/carts", {
              method: "POST",
              headers: {"Content-Type": "application/json"},
              body: JSON.stringify({userId: userId, productId: productId, quantity: quantity})
          })
      }
      catch(error) {
          console.error('Failed to get login', error)
      }
  }

  return (
    <>
    <Routes>
      {/* Homepage Route */}
      <Route path='/' element={<Home/>} />

      {/* Signup Route*/}
      <Route path='/signup' element={<Signup/>}/>

      {/* Login Route*/}
      <Route path='/login' element={<Login/>}/>

      {/* Products Route uses addToCart function to handle clicks */}
      <Route path='/products' element={<Products addToCart={addToCart} addToCartDB={addToCartDB}/>} />

      {/* Cart Route takes Cart State and displays items + total */}
      <Route path='/cart' element={<Cart userId={userId} cart={cart}/>} />
    </Routes>
    </>
  )
}

export default App
