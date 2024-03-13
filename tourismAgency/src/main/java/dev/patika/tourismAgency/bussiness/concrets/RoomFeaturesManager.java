package dev.patika.tourismAgency.bussiness.concrets;

import dev.patika.tourismAgency.bussiness.abstracts.IRoomFeaturesService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.config.utilies.Msg;
import dev.patika.tourismAgency.core.config.utilies.ResultHelper;
import dev.patika.tourismAgency.core.result.ListResult;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dao.RoomFeaturesRepo;
import dev.patika.tourismAgency.dao.RoomTypesRepo;
import dev.patika.tourismAgency.dto.request.RoomFeatures.SaveRoomFeaturesRequest;
import dev.patika.tourismAgency.dto.response.FacilityResponse;
import dev.patika.tourismAgency.dto.response.HostelResponse;
import dev.patika.tourismAgency.dto.response.RoomFeaturesResponse;
import dev.patika.tourismAgency.entities.Facility;
import dev.patika.tourismAgency.entities.Hostel;
import dev.patika.tourismAgency.entities.RoomFeatures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public RoomFeatures get(long id) {
        return this.roomFeaturesRepo.findById(id).orElseThrow();
    }

    @Override
    public boolean delete(long id) {
        try {
            RoomFeatures roomFeatures = this.get(id);
            this.roomFeaturesRepo.delete(roomFeatures);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public ResultData<RoomFeaturesResponse> findById(long id) {
        return null;
    }

    @Override
    public RoomFeatures update(RoomFeatures roomFeatures) {
        this.get(roomFeatures.getId());
        return roomFeaturesRepo.save(roomFeatures);
    }

    @Override
    public ResultData<List<RoomFeaturesResponse>> findAll() {
        List<RoomFeatures> roomFeaturesList = this.roomFeaturesRepo.findAll();

        try {
            List<RoomFeaturesResponse> responseList = new ArrayList<>();
            for (RoomFeatures roomFeatures : roomFeaturesList) {
                RoomFeaturesResponse response = this.modelMapper.forResponse().map(roomFeatures, RoomFeaturesResponse.class);
                responseList.add(response);
            }
            return ResultHelper.success(responseList);
        } catch (Exception e) {
            return ResultData.error(Msg.VALIDATE_ERROR, "500");
        }
    }

    @Override
    public ResultData<RoomFeatures> findByName(String name) {
        try {
            RoomFeatures roomFeatures = this.roomFeaturesRepo.findByNameIgnoreCase(name);
            if (roomFeatures != null) {
                return ResultHelper.success(roomFeatures);
            } else {
                return ResultData.error(Msg.NOT_FOUND, "404");
            }
        } catch (Exception e) {
            return ResultData.error(Msg.NOT_FOUND, "404");
        }
    }
}
