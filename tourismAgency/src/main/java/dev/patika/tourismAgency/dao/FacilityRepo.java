package dev.patika.tourismAgency.dao;

import dev.patika.tourismAgency.entities.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepo extends JpaRepository<Facility,Long> {
}
