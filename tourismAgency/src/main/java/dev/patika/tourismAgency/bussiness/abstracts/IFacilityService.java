package dev.patika.tourismAgency.bussiness.abstracts;

import dev.patika.tourismAgency.core.result.ListResult;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.facility.SaveFacilityRequest;
import dev.patika.tourismAgency.dto.response.FacilityResponse;
import dev.patika.tourismAgency.entities.Facility;

import java.util.List;

public interface IFacilityService {

    ResultData<FacilityResponse> save(SaveFacilityRequest saveFacilityRequest);

     Facility get(long id);

    boolean delete(long id);

    Facility update(Facility facility);

    ResultData<Facility> get2(long id);

    ResultData<List<FacilityResponse>> findAll();

    ResultData<Facility>findByFacilityName(String facilityName);
}
