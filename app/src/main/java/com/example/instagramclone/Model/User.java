package com.example.instagramclone.Model;

public class User {
    public String fullName;
    public String username;
    public String email;
    public String bio;
    public String imageUri;
    public String userId;

    public User(String fullName, String username, String email, String bio, String imageUri) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.imageUri = imageUri;
    }

    public User(String fullName, String username, String email, String bio, String imageUri, String userId) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.imageUri = imageUri;
        this.userId = userId;
    }

    public User(String fullName, String username, String imageUri) {
        this.fullName = fullName;
        this.username = username;
        this.imageUri = imageUri;
    }

    public User() {

    }

    public User(String fullName, String username, String email, String bio) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.bio = bio;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
