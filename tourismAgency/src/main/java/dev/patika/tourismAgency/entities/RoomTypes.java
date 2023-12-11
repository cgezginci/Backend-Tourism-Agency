package dev.patika.tourismAgency.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "room_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_id" , columnDefinition = "serial")
    private long id; // room_type_id

    @Column(name = "room_type_name")
    private String name; // room_type_name


    @OneToMany(mappedBy = "roomType" , cascade = CascadeType.REMOVE)
    private List<Room> rooms;

    @Override
    public String toString() {
        return "RoomTypes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
