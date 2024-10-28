package com.example.mobileproject;

import java.util.Objects;

public class User {
    public String name;
    public String email;
    public String id;

    public User() {
    }


    public User(String id) {
        this.id = id;
    }
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(String name, String email, String id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, id);
    }
}

