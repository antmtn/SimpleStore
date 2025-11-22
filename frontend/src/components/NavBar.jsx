import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import IconButton from '@mui/material/IconButton'; // (No longer used for Cart but kept if you add other icon actions later)
import { Link } from 'react-router-dom';

// Minimal reusable navigation bar.
// Keep this at the top of every page for consistent navigation.
// Uses MUI AppBar + Buttons wired to react-router for client-side navigation.
// You can add more links later (Cart, About, etc.).
function NavBar() {
  return (
    <AppBar position="static" color="primary" sx={{ mb: 2 }}>
      <Toolbar>
        {/* Brand / Logo */}
        <Typography
          variant="h6"
          component={Link}
          to="/"
          style={{
            textDecoration: 'none',
            color: 'inherit',
            fontWeight: 600,
            marginRight: '1.5rem'
          }}
        >
          SimpleStore
        </Typography>

        {/* Navigation links */}
        <Box sx={{ display: 'flex', gap: 1 }}>
          <Button
            component={Link}
            to="/"
            color="inherit"
            size="small"
          >Home</Button>
          <Button
            component={Link}
            to="/products"
            color="inherit"
            size="small"
          >Products</Button>
        </Box>

        {/* Right side actions: Cart link styled like other nav items */}
        <Box sx={{ marginLeft: 'auto', display: 'flex', gap: 1 }}>
          <Button
            component={Link}
            to="/cart"
            color="inherit"
            size="small"
            startIcon={<span role="img" aria-label="cart">🛒</span>}
          >
            Cart
          </Button>
        </Box>
      </Toolbar>
    </AppBar>
  );
}

export default NavBar;
