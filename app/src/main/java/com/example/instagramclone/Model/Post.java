package com.example.instagramclone.Model;

public class Post {

    String caption,postId,postImage,publisher;

    public Post() {
    }

    public Post(String postImage) {
        this.postImage = postImage;
    }

    public Post(String caption, String postId, String postImage, String publisher) {
        this.caption = caption;
        this.postId = postId;
        this.postImage = postImage;
        this.publisher = publisher;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
