package dev.patika.tourismAgency.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "room_features")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomFeatures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_features_id" , columnDefinition = "serial")
    private long id; // room_features_id

    @Column(name = "room_features_name")
    private String name; // room_features_name

    @JsonIgnore
    @ManyToMany(mappedBy = "roomFeatures" , cascade = CascadeType.REMOVE)
    private List<Room> rooms;


    @Override
    public String toString() {
        return "RoomFeatures{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
