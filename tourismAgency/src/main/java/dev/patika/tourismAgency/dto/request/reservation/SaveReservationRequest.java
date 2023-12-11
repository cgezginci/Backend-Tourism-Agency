package dev.patika.tourismAgency.dto.request.reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.patika.tourismAgency.entities.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveReservationRequest {


    private String personName;

    private String personSurname;

    private String personPhone;

    private String personTc;

    private LocalDate startDate;

    private LocalDate endDate;

    private int personCount;

    private Room room;


}
