package dev.patika.tourismAgency.dao;

import dev.patika.tourismAgency.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room , Long> {

    List<Room> findByHotel_NameIgnoreCase(String name);

    List<Room> findByPeriodStartAfterAndPeriodEndBefore(LocalDate periodStart, LocalDate periodEnd);

}
