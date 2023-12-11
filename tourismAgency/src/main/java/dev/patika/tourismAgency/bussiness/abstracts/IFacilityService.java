package dev.patika.tourismAgency.bussiness.abstracts;

import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.facility.SaveFacilityRequest;
import dev.patika.tourismAgency.dto.response.FacilityResponse;
import dev.patika.tourismAgency.entities.Facility;

public interface IFacilityService {

    ResultData<FacilityResponse> save(SaveFacilityRequest saveFacilityRequest);

     Facility get(long id);

    boolean delete(long id);

    Facility update(Facility facility);

    ResultData<Facility> get2(long id);
}
