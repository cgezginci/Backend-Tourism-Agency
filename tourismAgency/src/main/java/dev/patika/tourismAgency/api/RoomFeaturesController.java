package dev.patika.tourismAgency.api;

import dev.patika.tourismAgency.bussiness.abstracts.IRoomFeaturesService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.RoomFeatures.SaveRoomFeaturesRequest;
import dev.patika.tourismAgency.dto.request.hostel.SaveHostelRequest;
import dev.patika.tourismAgency.dto.response.HostelResponse;
import dev.patika.tourismAgency.dto.response.RoomFeaturesResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
