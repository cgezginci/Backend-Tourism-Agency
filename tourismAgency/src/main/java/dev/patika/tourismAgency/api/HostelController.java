package dev.patika.tourismAgency.api;

import dev.patika.tourismAgency.bussiness.abstracts.IHostelService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.config.utilies.Msg;
import dev.patika.tourismAgency.core.config.utilies.ResultHelper;
import dev.patika.tourismAgency.core.result.ListResult;
import dev.patika.tourismAgency.core.result.Result;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.hostel.SaveHostelRequest;
import dev.patika.tourismAgency.dto.response.FacilityResponse;
import dev.patika.tourismAgency.dto.response.HostelResponse;
import dev.patika.tourismAgency.entities.Facility;
import dev.patika.tourismAgency.entities.Hostel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/hostel")
public class HostelController {

    @Autowired
    private IHostelService hostelService;

    @Autowired
    private IModelMapperService modelMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<HostelResponse> save(@Valid @RequestBody SaveHostelRequest saveHostelRequest) {
        return this.hostelService.save(saveHostelRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<Hostel> findById(@PathVariable long id) {
        return this.hostelService.get2(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<HostelResponse>> findAll() {
        return this.hostelService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        boolean isDelete = this.hostelService.delete(id);
        if (isDelete) {
            return ResultHelper.ok();
        }else {
            return ResultHelper.notFoundError(Msg.NOT_FOUND);
        }

    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Hostel update(@Valid @RequestBody Hostel hostel){
        return this.hostelService.update(hostel);
    }

    @GetMapping("/filter/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<Hostel> findByHostelName(@PathVariable String name){
        return this.hostelService.findByHostelName(name);
    }
}


