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

    @NotEmpty
    @Column(name = "person_name")
    private String personName; // person_name

    @NotEmpty
    @Column(name = "person_surname")
    private String personSurname; // person_surname

    @NotEmpty
    @Column(name = "person_phone")
    private String personPhone; // person_phone

    @NotEmpty
    @Column(name = "person_tc")
    private String personTc; // person_tc

    @NotNull
    @Column(name = "start_date" , nullable = false)
    private LocalDate startDate; // start_date

    @NotNull
    @Column(name = "end_date" , nullable = false)
    private LocalDate endDate; // end_date

    @NotNull
    @Column(name = "person_count")
    private int personCount; // person_count

    @NotNull
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
