package dev.patika.tourismAgency.bussiness.concrets;

import dev.patika.tourismAgency.bussiness.abstracts.IRoomFeaturesService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.config.utilies.Msg;
import dev.patika.tourismAgency.core.config.utilies.ResultHelper;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dao.RoomFeaturesRepo;
import dev.patika.tourismAgency.dao.RoomTypesRepo;
import dev.patika.tourismAgency.dto.request.RoomFeatures.SaveRoomFeaturesRequest;
import dev.patika.tourismAgency.dto.response.HostelResponse;
import dev.patika.tourismAgency.dto.response.RoomFeaturesResponse;
import dev.patika.tourismAgency.entities.RoomFeatures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomFeaturesManager implements IRoomFeaturesService {

    @Autowired
    private RoomFeaturesRepo roomFeaturesRepo;

    @Autowired
    private IModelMapperService modelMapper;

    @Override
    public ResultData<RoomFeaturesResponse> save(SaveRoomFeaturesRequest saveRoomFeaturesRequest) {
        try {
            RoomFeatures roomFeatures = modelMapper.forRequest().map(saveRoomFeaturesRequest, RoomFeatures.class);
            this.roomFeaturesRepo.save(roomFeatures);
            return ResultHelper.created(this.modelMapper.forResponse().map(roomFeatures, RoomFeaturesResponse.class));

        }catch (Exception e){
            return  ResultData.error(Msg.VALIDATE_ERROR , "500");
        }
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public ResultData<RoomFeaturesResponse> findById(long id) {
        return null;
    }
}
