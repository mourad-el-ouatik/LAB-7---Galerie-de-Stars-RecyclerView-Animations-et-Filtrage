package com.example.starsgallery.beans;

public class ClassStart {
    private static int counter = 1;
    private int id;
    private String name;
    private String img;
    private float rating;

    public ClassStart(String name, String img, float rating) {
        this.id = counter++;
        this.name = name;
        this.img = img;
        this.rating = rating;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getImg() { return img; }
    public float getRating() { return rating; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setImg(String img) { this.img = img; }
    public void setRating(float rating) { this.rating = rating; }
}