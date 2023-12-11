package dev.patika.tourismAgency.dao;

import dev.patika.tourismAgency.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation , Long> {
}
