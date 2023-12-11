package dev.patika.tourismAgency.dto.response;

import dev.patika.tourismAgency.entities.Facility;
import dev.patika.tourismAgency.entities.Hostel;
import dev.patika.tourismAgency.entities.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponse {

    private long id;
    private String name; // hotel_name

    private String city; // hotel_city

    private String loc; // hotel_loc

    private String address; // hotel_address

    private String phone; // hotel_phone

    private String email; // hotel_email

    private int star; // hotel_star

    private List<Hostel> hostels;

    private List<Facility> facilities;




}
