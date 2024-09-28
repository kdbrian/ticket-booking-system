package io.github.junrdev.bookingsys.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
public class Company {

    @Id
    private String id;

    @Indexed(unique = true)
    private String fullName;

    private Kyc kyc;

    private Date dateJoined = new Date();

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String phone;

    private Location location;

    private String locationArea;

    private Boolean isActive = true;

    private List<String> images;

    @DBRef
    private County county;

    @DBRef
    private SubCounty subCounty;

    public Company() {
    }

    public Company(String id, String fullName, Kyc kyc, Date dateJoined, String email, String phone, Location location, String locationArea, Boolean isActive, List<String> images, County county, SubCounty subCounty) {
        this.id = id;
        this.fullName = fullName;
        this.kyc = kyc;
        this.dateJoined = dateJoined;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.locationArea = locationArea;
        this.isActive = isActive;
        this.images = images;
        this.county = county;
        this.subCounty = subCounty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Kyc getKyc() {
        return kyc;
    }

    public void setKyc(Kyc kyc) {
        this.kyc = kyc;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLocationArea() {
        return locationArea;
    }

    public void setLocationArea(String locationArea) {
        this.locationArea = locationArea;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void addImages(String image) {
        if (images != null)
            images.add(image);
        else {
            this.images = new ArrayList<>();
            images.add(image);
        }
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public SubCounty getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(SubCounty subCounty) {
        this.subCounty = subCounty;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", kyc=" + kyc +
                ", dateJoined=" + dateJoined +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", location=" + location +
                ", locationArea='" + locationArea + '\'' +
                ", isActive=" + isActive +
                ", images=" + images +
                ", county=" + county +
                ", subCounty=" + subCounty +
                '}';
    }
}


