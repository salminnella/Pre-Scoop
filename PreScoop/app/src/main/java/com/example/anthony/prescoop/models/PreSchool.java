package com.example.anthony.prescoop.models;

import java.util.ArrayList;

/**
 * Created by anthony on 3/12/16.
 */
public class PreSchool {
    private String name;
    private String address;
    private String region;
    private String type;
    private String phoneNumber;
    private int description;
    private int rating;
    private int ageGroup;
    private ArrayList<Integer> images;
    private double cost;
    private boolean favorite;

    public PreSchool() {

    }

    public PreSchool(String name) {
        this.name = name;
    }

    public PreSchool(String name, String address, double cost, int description, int rating, String region) {
        this.name = name;
        this.address = address;
        this.cost = cost;
        this.description = description;
        this.rating = rating;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(int ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Integer> getImages() {
        return images;
    }

    public void setImages(ArrayList<Integer> images) {
        this.images = images;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void addImage(int image) {
        this.images.add(image);
    }

}
