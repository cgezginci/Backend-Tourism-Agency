package dev.patika.tourismAgency.api;

import dev.patika.tourismAgency.bussiness.abstracts.IFacilityService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.facility.SaveFacilityRequest;
import dev.patika.tourismAgency.dto.response.FacilityResponse;
import dev.patika.tourismAgency.entities.Facility;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/facility")
public class FacilityController {

   @Autowired
   private IFacilityService facilityService;

    @Autowired
    private IModelMapperService modelMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<FacilityResponse> save(@Valid @RequestBody SaveFacilityRequest saveFacilityRequest) {
        return this.facilityService.save(saveFacilityRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<Facility> findById(@PathVariable long id) {
        return this.facilityService.get2(id);
    }

}
