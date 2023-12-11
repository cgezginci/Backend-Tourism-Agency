package dev.patika.tourismAgency.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "hostels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hostel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hostel_id" , columnDefinition = "serial")
    private long id; // hostel_id

    @Column(name = "hostel_name")
    private String name; // hostel_name

    @JsonIgnore
    @ManyToMany(mappedBy = "hostels" , cascade = CascadeType.REMOVE)
    private List<Hotel> hotels;

    @Override
    public String toString() {
        return "Hostel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
