import NavBar from "../components/NavBar.jsx";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import {useEffect, useState} from "react";

function Orders({userId}) {
    // orders fetched from backend API
    const [orders, setOrders] = useState([]);

    // Loads products in the beginning
    useEffect(() => {
      fetchOrders()
    }, []);

    // Fetch to API
    const fetchOrders = async () => {
      try {
          const response = await fetch("http://localhost:8080/api/orders");
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
        </>
    );
}

export default Orders;