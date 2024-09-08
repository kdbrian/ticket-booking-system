package io.github.junrdev.bookingsystem.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Seat {

    private String seatNo;
    private Boolean isOccupied = false;

    public Seat() {
    }

    public Seat(String seatNo, Boolean isOccupied) {
        this.seatNo = seatNo;
        this.isOccupied = isOccupied;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public Boolean getOccupied() {
        return isOccupied;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatNo='" + seatNo + '\'' +
                ", isOccupied=" + isOccupied +
                '}';
    }
}
