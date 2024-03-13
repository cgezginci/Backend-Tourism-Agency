package dev.patika.tourismAgency.bussiness.concrets;

import dev.patika.tourismAgency.bussiness.abstracts.IRoomTypesService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.config.utilies.Msg;
import dev.patika.tourismAgency.core.config.utilies.ResultHelper;
import dev.patika.tourismAgency.core.result.ListResult;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dao.RoomTypesRepo;
import dev.patika.tourismAgency.dto.request.RoomFeatures.SaveRoomFeaturesRequest;
import dev.patika.tourismAgency.dto.request.roomType.SaveRoomTypesRequest;
import dev.patika.tourismAgency.dto.response.FacilityResponse;
import dev.patika.tourismAgency.dto.response.RoomFeaturesResponse;
import dev.patika.tourismAgency.dto.response.RoomTypeResponse;
import dev.patika.tourismAgency.entities.Facility;
import dev.patika.tourismAgency.entities.Hostel;
import dev.patika.tourismAgency.entities.RoomFeatures;
import dev.patika.tourismAgency.entities.RoomTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        try {
            RoomTypes roomTypes = this.get(id);
            this.roomTypesRepo.delete(roomTypes);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public ResultData<RoomTypeResponse> findById(long id) {
        return null;
    }

    @Override
    public RoomTypes get(long id) {
        return this.roomTypesRepo.findById(id).orElseThrow();
    }

    @Override
    public RoomTypes update(RoomTypes roomTypes) {
        this.get(roomTypes.getId());
        return roomTypesRepo.save(roomTypes);
    }

    @Override
    public ResultData<List<RoomTypeResponse>> findAll() {
        List<RoomTypes> roomTypesList = this.roomTypesRepo.findAll();

        try {
            List<RoomTypeResponse> responseList = new ArrayList<>();
            for (RoomTypes roomTypes : roomTypesList) {
                RoomTypeResponse response = this.modelMapper.forResponse().map(roomTypes, RoomTypeResponse.class);
                responseList.add(response);
            }
            return ResultHelper.success(responseList);
        } catch (Exception e) {
            return ResultData.error(Msg.VALIDATE_ERROR, "500");
        }
    }

    @Override
    public ResultData<RoomTypeResponse> findByName(String name) {
        RoomTypes roomTypes = this.roomTypesRepo.findByNameIgnoreCase(name);
        if (roomTypes == null) {
            return ResultData.error(Msg.NOT_FOUND, "404");
        } else {
            return ResultHelper.success(this.modelMapper.forResponse().map(roomTypes, RoomTypeResponse.class));
        }
    }
}
