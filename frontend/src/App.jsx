import { useState } from 'react'
import { Route, Routes } from 'react-router-dom';
import Products from "./pages/Products.jsx";
import Home from "./pages/Home.jsx";

function App() {

  return (
    <>
        <Routes>
            <Route path={'/'} element={<Home/>}></Route>
            <Route path={'/products'} element={<Products/>}></Route>
        </Routes>
    </>
  )
}

export default App
