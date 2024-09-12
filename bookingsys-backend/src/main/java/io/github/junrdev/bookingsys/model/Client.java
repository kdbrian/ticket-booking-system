package io.github.junrdev.bookingsys.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Client {

    @Id
    private String id;

    private String fullName;

    private String identification;

    private Kyc kyc;

    private LocalDate dateJoined;

    private String email;

    private String phone;

    private Location location;

    private Boolean isActive;

    private CLIENT_TYPE clientType;

    public CLIENT_TYPE getClientType() {
        return clientType;
    }

    public void setClientType(CLIENT_TYPE clientType) {
        this.clientType = clientType;
    }

    private enum CLIENT_TYPE {
        STAFF, USER
    }

    public Client() {
        this.isActive = true;
        this.clientType = CLIENT_TYPE.USER;
        this.dateJoined = LocalDate.now();
    }

    public Client(String id, String fullName, String identification, Kyc kyc, LocalDate dateJoined, String email, String phone, Location location, Boolean isActive, CLIENT_TYPE clientType) {
        this.id = id;
        this.fullName = fullName;
        this.identification = identification;
        this.kyc = kyc;
        this.dateJoined = dateJoined;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.isActive = isActive;
        this.clientType = clientType;
    }

    public Client(String fullName, String identification, Kyc kyc, LocalDate dateJoined, String email, String phone, Location location, Boolean isActive) {
        this.fullName = fullName;
        this.identification = identification;
        this.kyc = kyc;
        this.dateJoined = dateJoined;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.isActive = isActive;
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

    public Kyc getKyc() {
        return kyc;
    }

    public void setKyc(Kyc kyc) {
        this.kyc = kyc;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", identification='" + identification + '\'' +
                ", kyc=" + kyc +
                ", dateJoined=" + dateJoined +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", location=" + location +
                ", isActive=" + isActive +
                '}';
    }
}
