package dev.patika.tourismAgency.api;

import dev.patika.tourismAgency.bussiness.abstracts.IFacilityService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.config.utilies.Msg;
import dev.patika.tourismAgency.core.config.utilies.ResultHelper;
import dev.patika.tourismAgency.core.result.ListResult;
import dev.patika.tourismAgency.core.result.Result;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.facility.SaveFacilityRequest;
import dev.patika.tourismAgency.dto.response.FacilityResponse;
import dev.patika.tourismAgency.entities.Facility;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public  ResultData<List<FacilityResponse>> findAll() {
        return this.facilityService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        boolean isDelete = this.facilityService.delete(id);
        if (isDelete) {
            return ResultHelper.ok();
        }else {
            return ResultHelper.notFoundError(Msg.NOT_FOUND);
        }

    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Facility update(@Valid @RequestBody Facility facility){
        return this.facilityService.update(facility);
    }

    @GetMapping("/filter/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<Facility> findByFacilityName(@PathVariable String name){
        return this.facilityService.findByFacilityName(name);
    }

}
