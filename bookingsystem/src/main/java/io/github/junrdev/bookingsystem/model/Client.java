    package io.github.junrdev.bookingsystem.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String identification;

    @Embedded
    private Kyc kyc;

    @Temporal(TemporalType.DATE)
    private Date dateJoined;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Embedded
    private Location location;

    private Boolean isActive;

    public Client() {
    }

    public Client(Long id, String fullName, String identification, Kyc kyc, Date dateJoined, String email, String phone, Location location, Boolean isActive) {
        this.id = id;
        this.fullName = fullName;
        this.identification = identification;
        this.kyc = kyc;
        this.dateJoined = dateJoined;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.isActive = isActive;
    }

    public Client(String fullName, String identification, Kyc kyc, Date dateJoined, String email, String phone, Location location, Boolean isActive) {
        this.fullName = fullName;
        this.identification = identification;
        this.kyc = kyc;
        this.dateJoined = dateJoined;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
