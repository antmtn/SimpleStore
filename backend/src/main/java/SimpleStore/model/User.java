package SimpleStore.model;

import jakarta.persistence.*;
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private boolean is_admin;
    private String username;
    private String password;

    public User(int user_id, boolean is_admin, String username, String password) {
        this.user_id = user_id;
        this.is_admin = is_admin;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isIs_admin() {
        return is_admin;
    }
}