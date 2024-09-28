package io.github.junrdev.bookingsys.model;


public class Seat {

    private String seatNo;
    private Boolean isOccupied;

    public Seat() {
    }

    public Seat(String seatNo) {
        this.seatNo = seatNo;
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

    public void occupy(){
        this.isOccupied = true;
    }

    public void release(){
        this.isOccupied = false;
    }
    @Override
    public String toString() {
        return "Seat{" +
                "seatNo='" + seatNo + '\'' +
                ", isOccupied=" + isOccupied +
                '}';
    }
}
