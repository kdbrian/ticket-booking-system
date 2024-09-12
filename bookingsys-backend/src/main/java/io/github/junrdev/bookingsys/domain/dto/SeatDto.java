package io.github.junrdev.bookingsys.domain.dto;

public class SeatDto {

    private String seatNo;
    private Boolean isOccupied;

    public SeatDto() {
    }

    public SeatDto(String seatNo, Boolean isOccupied) {
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
        return "SeatDto{" +
                "seatNo='" + seatNo + '\'' +
                ", isOccupied=" + isOccupied +
                '}';
    }
}
