package dev.patika.tourismAgency.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id" , columnDefinition = "serial")
    private long id; // hotel_id

    @NotEmpty
    @Column(name = "hotel_name")
    private String name; // hotel_name

    @NotEmpty
    @Column(name = "hotel_city")
    private String city; // hotel_city

    @NotEmpty
    @Column(name = "hotel_loc")
    private String loc; // hotel_loc

    @NotEmpty
    @Column(name = "hotel_address")
    private String address; // hotel_address

    @NotEmpty
    @Column(name = "hotel_phone")
    private String phone; // hotel_phone

    @NotEmpty
    @Column(name = "hotel_email")
    private String email; // hotel_email

    @NotNull
    @Column(name = "hotel_star")
    private int star; // hotel_star

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
           name = "hotel_hostel",
            joinColumns =
                    {
                            @JoinColumn(name = "hostelhostels_hotel_id")
                    },
    inverseJoinColumns = {
            @JoinColumn(name = "hostelhostels_hostel_id")
    }
    )
    private List<Hostel> hostels;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "hotel_facility",
            joinColumns =
                    {
                            @JoinColumn(name = "hostelfacilities_hotel_id")
                    },
            inverseJoinColumns = {
                    @JoinColumn(name = "hostelfacilities_facilities_id")
            }
    )
    private List<Facility> facilities;


    @OneToMany(mappedBy = "hotel" , cascade = CascadeType.REMOVE)
    private List<Room> rooms;

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", loc='" + loc + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", star=" + star +
                ", hostels=" + hostels +
                ", facilities=" + facilities +
                '}';
    }
}
