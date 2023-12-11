package dev.patika.tourismAgency.dao;

import dev.patika.tourismAgency.entities.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostelRepo extends JpaRepository<Hostel,Long> {
}
