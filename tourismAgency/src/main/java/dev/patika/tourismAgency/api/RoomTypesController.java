package dev.patika.tourismAgency.api;

import dev.patika.tourismAgency.bussiness.abstracts.IRoomTypesService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.hostel.SaveHostelRequest;
import dev.patika.tourismAgency.dto.request.roomType.SaveRoomTypesRequest;
import dev.patika.tourismAgency.dto.response.HostelResponse;
import dev.patika.tourismAgency.dto.response.RoomTypeResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
