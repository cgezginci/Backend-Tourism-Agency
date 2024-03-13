package dev.patika.tourismAgency.api;

import dev.patika.tourismAgency.bussiness.abstracts.IRoomFeaturesService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.config.utilies.Msg;
import dev.patika.tourismAgency.core.config.utilies.ResultHelper;
import dev.patika.tourismAgency.core.result.ListResult;
import dev.patika.tourismAgency.core.result.Result;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.RoomFeatures.SaveRoomFeaturesRequest;
import dev.patika.tourismAgency.dto.request.hostel.SaveHostelRequest;
import dev.patika.tourismAgency.dto.response.FacilityResponse;
import dev.patika.tourismAgency.dto.response.HostelResponse;
import dev.patika.tourismAgency.dto.response.RoomFeaturesResponse;
import dev.patika.tourismAgency.entities.Facility;
import dev.patika.tourismAgency.entities.RoomFeatures;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/room-features")
public class RoomFeaturesController {

    @Autowired
    private IRoomFeaturesService roomFeaturesService;

    @Autowired
    private IModelMapperService modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<RoomFeaturesResponse> save(@Valid @RequestBody SaveRoomFeaturesRequest saveRoomFeaturesRequest) {
        return this.roomFeaturesService.save(saveRoomFeaturesRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public RoomFeatures update(@Valid @RequestBody RoomFeatures roomFeatures){
        return this.roomFeaturesService.update(roomFeatures);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<RoomFeaturesResponse>> findAll() {
        return this.roomFeaturesService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        boolean isDelete = this.roomFeaturesService.delete(id);
        if (isDelete) {
            return ResultHelper.ok();
        }else {
            return ResultHelper.notFoundError(Msg.NOT_FOUND);
        }
    }

    @GetMapping("/filter/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<RoomFeatures> findByName(@PathVariable("name") String name) {
        return this.roomFeaturesService.findByName(name);
    }
}
