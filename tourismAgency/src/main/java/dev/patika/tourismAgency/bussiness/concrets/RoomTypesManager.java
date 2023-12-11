package dev.patika.tourismAgency.bussiness.concrets;

import dev.patika.tourismAgency.bussiness.abstracts.IRoomTypesService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.config.utilies.Msg;
import dev.patika.tourismAgency.core.config.utilies.ResultHelper;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dao.RoomTypesRepo;
import dev.patika.tourismAgency.dto.request.RoomFeatures.SaveRoomFeaturesRequest;
import dev.patika.tourismAgency.dto.request.roomType.SaveRoomTypesRequest;
import dev.patika.tourismAgency.dto.response.RoomFeaturesResponse;
import dev.patika.tourismAgency.dto.response.RoomTypeResponse;
import dev.patika.tourismAgency.entities.RoomFeatures;
import dev.patika.tourismAgency.entities.RoomTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypesManager implements IRoomTypesService {

    @Autowired
    private RoomTypesRepo roomTypesRepo;

    @Autowired
    private IModelMapperService modelMapper;

    @Override
    public ResultData<RoomTypeResponse> save(SaveRoomTypesRequest saveRoomTypesRequest) {
        try {
            RoomTypes roomTypes = modelMapper.forRequest().map(saveRoomTypesRequest, RoomTypes.class);
            this.roomTypesRepo.save(roomTypes);
            return ResultHelper.created(this.modelMapper.forResponse().map(roomTypes, RoomTypeResponse.class));

        }catch (Exception e){
            return  ResultData.error(Msg.VALIDATE_ERROR , "500");
        }
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public ResultData<RoomTypeResponse> findById(long id) {
        return null;
    }
}
