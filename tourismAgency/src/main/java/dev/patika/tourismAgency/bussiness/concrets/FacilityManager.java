package dev.patika.tourismAgency.bussiness.concrets;

import dev.patika.tourismAgency.bussiness.abstracts.IFacilityService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.config.utilies.Msg;
import dev.patika.tourismAgency.core.config.utilies.ResultHelper;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dao.FacilityRepo;
import dev.patika.tourismAgency.dto.request.facility.SaveFacilityRequest;
import dev.patika.tourismAgency.dto.response.FacilityResponse;
import dev.patika.tourismAgency.entities.Facility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return false;
    }

    @Override
    public Facility update(Facility facility) {
        return null;
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
}
