package dev.patika.tourismAgency.bussiness.abstracts;

import dev.patika.tourismAgency.core.result.ListResult;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.RoomFeatures.SaveRoomFeaturesRequest;
import dev.patika.tourismAgency.dto.response.RoomFeaturesResponse;
import dev.patika.tourismAgency.entities.Facility;
import dev.patika.tourismAgency.entities.RoomFeatures;

import java.util.List;

public interface IRoomFeaturesService {

    ResultData<RoomFeaturesResponse> save(SaveRoomFeaturesRequest saveRoomFeaturesRequest);

    boolean delete(long id);

    RoomFeatures update(RoomFeatures roomFeatures);


    ResultData<List<RoomFeaturesResponse>> findAll();

    RoomFeatures get(long id);

    ResultData<RoomFeaturesResponse> findById(long id);

    ResultData<RoomFeatures> findByName(String name);
}
