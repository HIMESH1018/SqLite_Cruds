package com.example.sqlite_basic.Models;

import android.database.Cursor;

public class User {

    private String id;
    private String email;
    private String password;

    public User(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
