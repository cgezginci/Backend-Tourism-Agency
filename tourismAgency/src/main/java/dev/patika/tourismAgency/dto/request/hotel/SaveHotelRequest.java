package dev.patika.tourismAgency.dto.request.hotel;

import dev.patika.tourismAgency.entities.Facility;
import dev.patika.tourismAgency.entities.Hostel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveHotelRequest {


    private String name; // hotel_name

    private String city; // hotel_city

    private String loc; // hotel_loc

    private String address; // hotel_address

    private String phone; // hotel_phone

    private String email; // hotel_email

    private int star; // hotel_star

    private List<Long> hostels;

    private List<Long> facilities;
}
