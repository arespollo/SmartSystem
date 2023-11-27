package com.mysql.smart.domain;

import com.mysql.smart.util.PwdHash;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false, length = 255)
    private String userName;
    @Column(nullable = false, length = 255)
    private String password;

    private static final PwdHash pwdHash = new PwdHash();

    public User() {
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.userName = username;
        this.password = password;
    }

    public static PwdHash getPwdHash() {
        return pwdHash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
