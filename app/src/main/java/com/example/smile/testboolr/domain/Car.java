package com.example.smile.testboolr.domain;

/**
 * Created by Smile on 2018/7/3.
 */

public class Car {
    private String name;
    private int price;
    private String image;
    private String author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
