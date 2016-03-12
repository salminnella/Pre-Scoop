package com.example.anthony.prescoop.models;

/**
 * Created by anthony on 3/12/16.
 */
public class PreSchool {
    private String name;
    private String address;
    private double cost;
    private String description;
    private String rating;

    public PreSchool() {

    }

    public PreSchool(String name) {
        this.name = name;
    }

    public PreSchool(String name, String address, double cost, String description, String rating) {
        this.name = name;
        this.address = address;
        this.cost = cost;
        this.description = description;
        this.rating = rating;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
