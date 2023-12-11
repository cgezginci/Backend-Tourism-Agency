package dev.patika.tourismAgency.bussiness.abstracts;

import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.roomType.SaveRoomTypesRequest;
import dev.patika.tourismAgency.dto.response.RoomTypeResponse;

public interface IRoomTypesService {

    ResultData<RoomTypeResponse> save(SaveRoomTypesRequest saveRoomTypesRequest);

    boolean delete(long id);


    ResultData<RoomTypeResponse> findById(long id);
}
