package dev.patika.tourismAgency.api;

import dev.patika.tourismAgency.bussiness.abstracts.IHostelService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.hostel.SaveHostelRequest;
import dev.patika.tourismAgency.dto.response.HostelResponse;
import dev.patika.tourismAgency.entities.Hostel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
