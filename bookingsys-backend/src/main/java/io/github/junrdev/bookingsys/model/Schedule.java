package io.github.junrdev.bookingsys.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Schedule {

    @Id
    private String id;

    @DBRef
    private Company company;

    private List<Route> routes = new ArrayList<>();

    @Indexed(unique = true)
    private String phone;

    public Schedule() {
    }

    public Schedule(String id, Company company, List<Route> routes, String phone) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public void addRoute(Route route){
        if (routes==null)
            routes = new ArrayList<>();
        this.routes.add(route);
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
