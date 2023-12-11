package dev.patika.tourismAgency.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "facilities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_id" , columnDefinition = "serial")
    private long id; // facility_id

    @Column(name = "facility_name")
    private String name; // facility_name

    @JsonIgnore
    @ManyToMany(mappedBy = "facilities" , cascade = CascadeType.REMOVE)
    private List<Hotel> hotels;


    @Override
    public String toString() {
        return "Facility{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
