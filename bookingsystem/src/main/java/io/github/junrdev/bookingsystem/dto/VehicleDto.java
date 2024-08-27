    package io.github.junrdev.bookingsystem.dto;

    import io.github.junrdev.bookingsystem.model.Seat;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.sql.Time;
    import java.util.List;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class VehicleDto {
        private Long vehicleId;
        private Long routeId;
        private Double price;
        private Double discount;
        private Time leavingTime;
        private Long seatCount;
        private List<Seat> seats;

    }
