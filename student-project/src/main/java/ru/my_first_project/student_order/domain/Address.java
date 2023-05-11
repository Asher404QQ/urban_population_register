package ru.my_first_project.student_order.domain;

public class Address {
    private String postIndex;
    private Street street;
    private String building;
    private String apartment;
    private String floor;
    private String extension;


    public Address(String postCode, Street street, String building, String floor, String apartment) {
        this.postIndex = postCode;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
        this.floor = floor;
    }

    public Address(String postCode, String building, String floor, String apartment) {
        this.postIndex = postCode;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
        this.floor = floor;
    }

    public String getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(String postIndex) {
        this.postIndex = postIndex;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "Address{" +
                "postIndex='" + postIndex + '\'' +
                ", street=" + street +
                ", building='" + building + '\'' +
                ", apartment='" + apartment + '\'' +
                ", floor='" + floor + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}
