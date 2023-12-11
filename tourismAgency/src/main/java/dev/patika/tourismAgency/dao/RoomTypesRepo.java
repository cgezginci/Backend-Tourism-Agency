package dev.patika.tourismAgency.dao;

import dev.patika.tourismAgency.entities.RoomTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypesRepo extends JpaRepository<RoomTypes , Long> {
}
