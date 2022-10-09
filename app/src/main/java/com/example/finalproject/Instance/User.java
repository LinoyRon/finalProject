package com.example.finalproject.Instance;

public class User {
    private String Email, Password, PhotoPath, FirstName, LastName, Token;
    Boolean isAdmin;

    public User(String email, String password, String photoPath, String firstName, String lastName, Boolean isAdmin) {
        Email = email.trim();
        Password = password.trim();
        PhotoPath = photoPath;
        FirstName = firstName;
        LastName = lastName;
        this.isAdmin = isAdmin;
    }

    public User(String email, String password) {
        Email = email.trim();
        Password = password.trim();
        this.isAdmin = false;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhotoPath() {
        return PhotoPath;
    }

    public void setPhotoPath(String photoPath) {
        PhotoPath = photoPath;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
