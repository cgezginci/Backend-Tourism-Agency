package dev.patika.tourismAgency.bussiness.concrets;

import dev.patika.tourismAgency.bussiness.abstracts.IFacilityService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.config.utilies.Msg;
import dev.patika.tourismAgency.core.config.utilies.ResultHelper;
import dev.patika.tourismAgency.core.result.ListResult;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dao.FacilityRepo;
import dev.patika.tourismAgency.dto.request.facility.SaveFacilityRequest;
import dev.patika.tourismAgency.dto.response.FacilityResponse;
import dev.patika.tourismAgency.dto.response.HotelResponse;
import dev.patika.tourismAgency.entities.Facility;
import dev.patika.tourismAgency.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacilityManager implements IFacilityService {

    @Autowired
    private IModelMapperService modelMapper;

    @Autowired
    private FacilityRepo facilityRepo;

    @Override
    public ResultData<FacilityResponse> save(SaveFacilityRequest saveFacilityRequest) {
        try {
            Facility facility = modelMapper.forRequest().map(saveFacilityRequest, Facility.class);
            this.facilityRepo.save(facility);

            return ResultHelper.created(this.modelMapper.forResponse().map(facility, FacilityResponse.class));
        } catch (Exception e) {
            return  ResultData.error(Msg.VALIDATE_ERROR , "500");
        }
    }

    @Override
    public Facility get(long id) {
        return this.facilityRepo.findById(id).orElseThrow();
    }

    @Override
    public boolean delete(long id) {
        try {
            Facility facility = this.get(id);
            this.facilityRepo.delete(facility);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Facility update(Facility facility) {
        this.get(facility.getId());
        return facilityRepo.save(facility);
    }


    @Override
    public ResultData<Facility> get2(long id) {
        try {
            Facility facility = this.facilityRepo.findById(id).orElseThrow();
            return ResultHelper.success(facility);
        } catch (Exception e) {
            return ResultData.error(Msg.NOT_FOUND, "404");
        }
    }

    @Override
    public ResultData<List<FacilityResponse>> findAll() {
        List<Facility> facilities = this.facilityRepo.findAll();

        try {
            List<FacilityResponse> responseList = new ArrayList<>();
            for (Facility facility : facilities) {
                FacilityResponse response = this.modelMapper.forResponse().map(facility, FacilityResponse.class);
                responseList.add(response);
            }
            return ResultHelper.success(responseList);
        } catch (Exception e) {
            return ResultData.error(Msg.VALIDATE_ERROR, "500");
        }
    }

    @Override
    public ResultData<Facility> findByFacilityName(String facilityName) {
        try {
            Facility facility = this.facilityRepo.findByNameIgnoreCase(facilityName);
            if (facility != null) {
                return ResultHelper.success(facility);
            } else {
                return ResultData.error(Msg.NOT_FOUND, "404");
            }
        } catch (Exception e) {
            return ResultData.error(Msg.NOT_FOUND, "404");
        }
    }

}
