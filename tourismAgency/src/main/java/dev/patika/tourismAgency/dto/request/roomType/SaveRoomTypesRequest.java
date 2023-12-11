package dev.patika.tourismAgency.dto.request.roomType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveRoomTypesRequest {

    private String name;
}
