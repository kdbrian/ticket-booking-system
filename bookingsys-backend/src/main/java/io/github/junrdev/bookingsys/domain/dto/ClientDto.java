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

    public ClientDto() {
    }

    public ClientDto(String id, String fullName, String identification, String email, String phone, Location location, Kyc kyc) {
        this.id = id;
        this.fullName = fullName;
        this.identification = identification;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.kyc = kyc;
    }

    public ClientDto(String fullName, String identification, String email, String phone, Location location, Kyc kyc) {
        this.fullName = fullName;
        this.identification = identification;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.kyc = kyc;
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
                '}';
    }
}
