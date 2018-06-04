package com.uxpsystems.assignment.dao;


public class UserBuilder {
    long id;
    String username;
    String password;
    String status;

    public UserBuilder id(long id) {
        this.id = id;
        return this;
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder status(String status) {
        this.status = status;
        return this;
    }

    public User build() {
        return new User(this);
    }
}
