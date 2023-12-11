package dev.patika.tourismAgency.bussiness.abstracts;

import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.RoomFeatures.SaveRoomFeaturesRequest;
import dev.patika.tourismAgency.dto.response.RoomFeaturesResponse;

public interface IRoomFeaturesService {

    ResultData<RoomFeaturesResponse> save(SaveRoomFeaturesRequest saveRoomFeaturesRequest);

    boolean delete(long id);


    ResultData<RoomFeaturesResponse> findById(long id);
}
