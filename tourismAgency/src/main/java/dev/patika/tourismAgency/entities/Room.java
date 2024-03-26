package dev.patika.tourismAgency.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id" , columnDefinition = "serial")
    private long id; // room_id


    @NotNull
    @Column(name = "room_stock" , nullable = false)
    private int stock; // room_stock


    @NotNull
    @Column(name = "room_square_meter" , nullable = false)
    private int squareMeter; // room_square_meter

    @NotEmpty
    @Column(name = "room_person_type")
    private String personType; // room_person_type


    @NotNull
    @Column(name = "room_bed_number" , nullable = false)
    private int bedNumber; // room_bed_number

    @NotNull
    @Column(name = "room_price" , nullable = false)
    private int price; // room_price


    @NotNull
    @Column(name = "room_period_start" , nullable = false)
    private LocalDate periodStart;

    @NotNull
    @Column(name = "room_period_end" , nullable = false)
    private LocalDate periodEnd;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_room_type_id" , referencedColumnName = "room_type_id")
    private RoomTypes roomType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "room_room_feature",
            joinColumns =
                    {
                            @JoinColumn(name = "room_room_features_room_id")
                    },
            inverseJoinColumns = {
                    @JoinColumn(name = "room_room_features_room_feature_id")
            }
    )
    private List<RoomFeatures> roomFeatures;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_hotel_id" , referencedColumnName = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room" , cascade = CascadeType.REMOVE)
    private List<Reservation> reservations;


    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", stock=" + stock +
                ", squareMeter=" + squareMeter +
                ", personType='" + personType + '\'' +
                ", bedNumber=" + bedNumber +
                ", price=" + price +
                ", roomType=" + roomType +
                ", roomFeatures=" + roomFeatures +
                ", hotel=" + hotel +
                '}';
    }
}
