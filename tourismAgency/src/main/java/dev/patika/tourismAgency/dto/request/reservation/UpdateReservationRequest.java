package dev.patika.tourismAgency.dto.request.reservation;

import dev.patika.tourismAgency.entities.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReservationRequest {

    private long id;
    private String personName;
    private String personSurname;
    private String personPhone;
    private String personTc;
    private LocalDate startDate;
    private LocalDate endDate;
    private int personCount;
    private Room room;

}
