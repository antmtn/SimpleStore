import { useState } from 'react'
import { Route, Routes } from 'react-router-dom';
import Products from "./pages/Products.jsx";
import Home from "./pages/Home.jsx";
import Cart from "./pages/Cart.jsx";

function App() {
  const [cart, setCart] = useState([]);

  function addToCart(product){
    setCart(prev => [...prev, product]);
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
