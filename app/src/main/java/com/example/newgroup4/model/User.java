package com.example.newgroup4.model;

public class User {
    private int UserID;
    private String Name;
    private String email;
    private String role;
    private String password;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //To string method
    @Override
    public String toString() {
        return "User{" +
                "UserID=" + UserID +
                ", Name='" + Name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' + '}';
    }
}
