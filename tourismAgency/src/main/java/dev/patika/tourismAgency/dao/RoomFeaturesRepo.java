package dev.patika.tourismAgency.dao;

import dev.patika.tourismAgency.entities.RoomFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomFeaturesRepo extends JpaRepository<RoomFeatures,Long> {
}
