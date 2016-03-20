package com.example.anthony.prescoop.models;

import java.util.ArrayList;

/**
 * Created by anthony on 3/12/16.
 */
public class PreSchool {
    private String name;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String region;
    private String type;
    private String phoneNumber;
    private int description;
    private String photo1Description;
    private String photo2Description;
    private String photo3Description;
    private String photo4Description;
    private String photo5Description;

    private int photo1;
    private int photo2;
    private int photo3;
    private int photo4;
    private int photo5;
    private int rating;
    private int ageGroup;   // TODO: add to database

    private ArrayList<Integer> images;
    private double price;
    private boolean favorite;

    public PreSchool() {

    }

    public PreSchool(String name) {
        this.name = name;
    }

    public PreSchool(String name, String streetAddress, double price, int description, int rating, String region) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.region = region;
    }

    public PreSchool(String name, String streetAddress, String city, String state, String zipCode, String region, String type, String phoneNumber, int description, int rating, double price, ArrayList<Integer> images) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.region = region;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.rating = rating;
        this.price = price;
        this.images = images;
        //this.photo1 = photo1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public Integer getImages(int position) {
        return images.get(position);
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoto1Description() {
        return photo1Description;
    }

    public void setPhoto1Description(String photo1Description) {
        this.photo1Description = photo1Description;
    }

    public String getPhoto2Description() {
        return photo2Description;
    }

    public void setPhoto2Description(String photo2Description) {
        this.photo2Description = photo2Description;
    }

    public String getPhoto3Description() {
        return photo3Description;
    }

    public void setPhoto3Description(String photo3Description) {
        this.photo3Description = photo3Description;
    }

    public String getPhoto4Description() {
        return photo4Description;
    }

    public void setPhoto4Description(String photo4Description) {
        this.photo4Description = photo4Description;
    }

    public String getPhoto5Description() {
        return photo5Description;
    }

    public void setPhoto5Description(String photo5Description) {
        this.photo5Description = photo5Description;
    }

    public int getPhoto1() {
        return photo1;
    }

    public void setPhoto1(int photo1) {
        this.photo1 = photo1;
    }

    public int getPhoto2() {
        return photo2;
    }

    public void setPhoto2(int photo2) {
        this.photo2 = photo2;
    }

    public int getPhoto3() {
        return photo3;
    }

    public void setPhoto3(int photo3) {
        this.photo3 = photo3;
    }

    public int getPhoto4() {
        return photo4;
    }

    public void setPhoto4(int photo4) {
        this.photo4 = photo4;
    }

    public int getPhoto5() {
        return photo5;
    }

    public void setPhoto5(int photo5) {
        this.photo5 = photo5;
    }
}
