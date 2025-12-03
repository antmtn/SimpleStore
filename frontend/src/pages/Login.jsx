import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Grid from '@mui/material/Grid';
import {useState} from "react";
import {useNavigate} from "react-router-dom";
import { Link } from "react-router-dom";

function Login({setUserId}) {

    const navigate = useNavigate();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    async function handleLogin(){
        try {
            const response = await fetch("http://localhost:8080/api/users/login", {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({username: username, password: password})
            });
            const data = await response.json();
            if (data > 0) {
                localStorage.setItem("userId", data);
                setUserId(data)
                navigate("/");
                console.log('Login success, navigating home with userId:', data);
            }
        }
        catch(error) {
            console.error('Failed to get login', error)
        }
    }

    return (
        <Grid container sx={{ minHeight: "100vh", flexGrow:1, width:"100%", padding:"20px" }} justifyContent="center" alignItems="center">
            <Paper align="center" sx={{display:"flex", flexDirection:"column", gap:2, padding:3}}>
                <Typography>Log In</Typography>
                <TextField label="Username" value={username} onChange={(e) => setUsername(e.target.value)}/>
                <TextField label="Password" type='password' value={password} onChange={(e) => setPassword(e.target.value)}/>
                <Button variant="contained" onClick={handleLogin}>Log In</Button>

                <Typography variant="body2">
                  Don’t have an account?{" "}
                  <Link to="/signup" style={{ textDecoration: "none", color: "#1976d2" }}>
                    Sign up
                  </Link>
                </Typography>
            </Paper>
        </Grid>
    )

}

export default Login;