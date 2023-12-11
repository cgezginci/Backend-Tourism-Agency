package dev.patika.tourismAgency.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id" , columnDefinition = "serial")
    private long id; // reservation_id

    @Column(name = "person_name")
    private String personName; // person_name

    @Column(name = "person_surname")
    private String personSurname; // person_surname

    @Column(name = "person_phone")
    private String personPhone; // person_phone

    @Column(name = "person_tc")
    private String personTc; // person_tc

    @Column(name = "start_date")
    private LocalDate startDate; // start_date

    @Column(name = "end_date")
    private LocalDate endDate; // end_date

    @Column(name = "person_count")
    private int personCount; // person_count

    @Column(name = "reservation_price")
    private int reservationPrice; // reservation_price

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_room_id" , referencedColumnName = "room_id")
    private Room room;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", personName='" + personName + '\'' +
                ", personSurname='" + personSurname + '\'' +
                ", personPhone='" + personPhone + '\'' +
                ", personTc='" + personTc + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", personCount=" + personCount +
                ", reservationPrice=" + reservationPrice +
                ", room=" + room +
                '}';
    }
}
