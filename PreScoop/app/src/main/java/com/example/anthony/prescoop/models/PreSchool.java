package com.example.anthony.prescoop.models;

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
    private String ageGroup;

    private int range;
    private int schoolDescription;
    private int rating;
    private int favorite;
    private int price;

    private int[] images;
    private String[] imageDescription;


    public PreSchool() {

    }

    public PreSchool(String name) {
        this.name = name;
    }

    public PreSchool(String name, int schoolDescription, int price, String streetAddress, String city, String state,
                     String zipCode, String phoneNumber, String region, int range, String type, String ageGroup, int rating,
                     int favorite, int[] images, String[] imageDescription) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.region = region;
        this.range = range;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.ageGroup = ageGroup;
        this.schoolDescription = schoolDescription;
        this.rating = rating;
        this.price = price;
        this.favorite = favorite;
        this.images = images;
        this.imageDescription = imageDescription;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSchoolDescription() {
        return schoolDescription;
    }

    public void setSchoolDescription(int schoolDescription) {
        this.schoolDescription = schoolDescription;
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

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
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

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getImageByPostion(int position) {
        return images[position];
    }

    public void setImages(int[] images) {
        this.images = images;
    }

    public String getImageDescriptionByPostion(int position) {
        return imageDescription[position];
    }

    public int[] getImages() {
        return images;
    }

    public String[] getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String[] imageDescription) {
        this.imageDescription = imageDescription;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}
