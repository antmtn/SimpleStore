import Button from "@mui/material/Button";
import NavBar from "../components/NavBar.jsx";
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';

function Home() {
    return (
        <>
            <NavBar />
            <Box sx={{ px: 2 }}>
                <Typography variant="h4" sx={{ mb: 2, fontWeight: 600 }}>
                    Welcome to SimpleStore
                </Typography>
                <Typography variant="body1" sx={{ mb: 2 }}>
                    Browse products using the navigation bar above. This is a simple demo store.
                </Typography>
                <Button variant="contained" component={Button} href={'/products'}>
                    View Products
                </Button>
            </Box>
        </>
    )
}

export default Home