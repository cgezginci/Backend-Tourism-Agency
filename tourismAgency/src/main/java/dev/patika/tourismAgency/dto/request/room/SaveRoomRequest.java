package dev.patika.tourismAgency.dto.request.room;

import dev.patika.tourismAgency.entities.Hotel;
import dev.patika.tourismAgency.entities.RoomFeatures;
import dev.patika.tourismAgency.entities.RoomTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveRoomRequest {

    private RoomTypes roomType;
    private int stock; // room_stock
    private int squareMeter; // room_square_meter
    private String personType; // room_person_type
    private int bedNumber; // room_bed_number
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private int price; // room_price
    private List<Long> roomFeatures;
    private Hotel hotel;
}
