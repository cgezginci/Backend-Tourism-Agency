package dev.patika.tourismAgency.bussiness.abstracts;

import dev.patika.tourismAgency.core.result.ListResult;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.roomType.SaveRoomTypesRequest;
import dev.patika.tourismAgency.dto.response.RoomFeaturesResponse;
import dev.patika.tourismAgency.dto.response.RoomTypeResponse;
import dev.patika.tourismAgency.entities.RoomFeatures;
import dev.patika.tourismAgency.entities.RoomTypes;

import java.util.List;

public interface IRoomTypesService {

    ResultData<RoomTypeResponse> save(SaveRoomTypesRequest saveRoomTypesRequest);

    boolean delete(long id);

    ResultData<RoomTypeResponse> findById(long id);

    RoomTypes update(RoomTypes roomTypes);

    ResultData<List<RoomTypeResponse>> findAll();

    RoomTypes get(long id);

    ResultData<RoomTypeResponse> findByName(String name);
}
