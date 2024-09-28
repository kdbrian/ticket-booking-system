package io.github.junrdev.bookingsys.domain.dto;

import io.github.junrdev.bookingsys.model.Kyc;
import io.github.junrdev.bookingsys.model.Location;

public class ClientDto {

    private String id;
    private String fullName;
    private String identification;
    private String email;
    private String phone;
    private Location location;
    private Kyc kyc ;
    private String county;
    private String subCounty;

    public ClientDto() {
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

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
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

    public Kyc getKyc() {
        return kyc;
    }

    public void setKyc(Kyc kyc) {
        this.kyc = kyc;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(String subCounty) {
        this.subCounty = subCounty;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", identification='" + identification + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", location=" + location +
                ", kyc=" + kyc +
                ", county='" + county + '\'' +
                ", subCounty='" + subCounty + '\'' +
                '}';
    }
}
