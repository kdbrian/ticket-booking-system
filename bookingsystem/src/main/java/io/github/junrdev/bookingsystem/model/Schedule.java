package io.github.junrdev.bookingsystem.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Route> routes;

    @Column(nullable = false)
    private String phone;

    public Schedule() {
    }

    public Schedule(Long id, Company company, List<Route> routes, String phone) {
        this.id = id;
        this.company = company;
        this.routes = routes;
        this.phone = phone;
    }

    public Schedule(Company company, List<Route> routes, String phone) {
        this.company = company;
        this.routes = routes;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", company=" + company +
                ", routes=" + routes +
                ", phone='" + phone + '\'' +
                '}';
    }
}
