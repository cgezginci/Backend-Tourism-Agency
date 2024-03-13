package dev.patika.tourismAgency.api;

import dev.patika.tourismAgency.bussiness.abstracts.IRoomTypesService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.config.utilies.Msg;
import dev.patika.tourismAgency.core.config.utilies.ResultHelper;
import dev.patika.tourismAgency.core.result.ListResult;
import dev.patika.tourismAgency.core.result.Result;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.hostel.SaveHostelRequest;
import dev.patika.tourismAgency.dto.request.roomType.SaveRoomTypesRequest;
import dev.patika.tourismAgency.dto.response.HostelResponse;
import dev.patika.tourismAgency.dto.response.RoomFeaturesResponse;
import dev.patika.tourismAgency.dto.response.RoomTypeResponse;
import dev.patika.tourismAgency.entities.RoomFeatures;
import dev.patika.tourismAgency.entities.RoomTypes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/room-types")
public class RoomTypesController {

    @Autowired
    private IRoomTypesService roomTypesService;

    @Autowired
    private IModelMapperService modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<RoomTypeResponse> save(@Valid @RequestBody SaveRoomTypesRequest saveRoomTypesRequest) {
        return this.roomTypesService.save(saveRoomTypesRequest);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public RoomTypes update(@Valid @RequestBody RoomTypes roomTypes){
        return this.roomTypesService.update(roomTypes);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<RoomTypeResponse>> findAll() {
        return this.roomTypesService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        boolean isDelete = this.roomTypesService.delete(id);
        if (isDelete) {
            return ResultHelper.ok();
        }else {
            return ResultHelper.notFoundError(Msg.NOT_FOUND);
        }
    }

    @GetMapping("/filter/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<RoomTypeResponse> findByName(@PathVariable("name") String name) {
        return this.roomTypesService.findByName(name);
    }
}
