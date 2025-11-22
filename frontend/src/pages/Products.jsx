import {useEffect, useState} from 'react'
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import NavBar from "../components/NavBar.jsx";
import Button from '@mui/material/Button';

function Products({addToCart}) {

    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetchProducts()
    }, []);

    const fetchProducts = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/products");
            const data = await response.json();
            setProducts(data)

        }
        catch(error) {
            console.error('Failed to get products', error)
        }
    }

    return (
        <>
            <NavBar />
            <h1 style={{ marginLeft: '16px' }}>Products</h1>
            <Box elevation={6} variant="outlined" square={true} sx={{ display: 'grid',
                gridTemplateColumns: 'repeat(auto-fill, minmax(140px, 1fr))',
                gap: 2,
                padding: 2}}>
                {products.map((product) => (
                    <Paper key={product.product_id} sx={{
                        padding: 2,
                        margin: 1,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                        gap: .5

                    }}>
                        <div style={{
                            maxWidth: '100%',
                            height: '80px',
                            backgroundColor: '#fff',
                            display: 'flex',
                            alignItems: 'center',
                            justifyContent: 'center',
                            padding: '12px',
                            borderBottom: '1px solid #eee'
                        }}>
                            <img src={product.image} style={{
                                width: '100%',
                                height: 'auto',
                                objectFit: 'contain',
                                borderRadius: '8px'
                            }}>
                            </img></div>
                        <p><strong>{product.name}</strong></p><p>${product.price}</p>
                        <Button
                            variant="contained"
                            size="small"
                            onClick={() => addToCart(product)}
                        >
                            Add to Cart

                        </Button>
                    </Paper>
                ))}

            </Box>
        </>
    )
}

export default Products