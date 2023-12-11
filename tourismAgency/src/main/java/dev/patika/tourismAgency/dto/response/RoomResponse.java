package dev.patika.tourismAgency.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.patika.tourismAgency.entities.Hotel;
import dev.patika.tourismAgency.entities.RoomFeatures;
import dev.patika.tourismAgency.entities.RoomTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {

    private long id; // room_id
    private RoomTypeResponse roomType;
    private int stock; // room_stock
    private int squareMeter; // room_square_meter
    private String personType; // room_person_type
    private int bedNumber; // room_bed_number
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private int price; // room_price
    private List<RoomFeatures> roomFeatures;
    private String hotelName;

}
