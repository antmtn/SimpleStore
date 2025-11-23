import { useState } from 'react'
import { Route, Routes } from 'react-router-dom';
import Products from "./pages/Products.jsx";
import Home from "./pages/Home.jsx";
import Cart from "./pages/Cart.jsx";

function App() {
  const [cart, setCart] = useState([]);

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

  return (
    <>
    <Routes>
      <Route path='/' element={<Home/>} />
      <Route path='/products' element={<Products addToCart={addToCart}/>} />
      <Route path='/cart' element={<Cart cart = {cart}/>} />
    </Routes>
    </>
  )
}

export default App
