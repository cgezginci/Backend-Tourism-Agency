package dev.patika.tourismAgency.api;

import dev.patika.tourismAgency.bussiness.abstracts.IRoomService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.config.utilies.Msg;
import dev.patika.tourismAgency.core.config.utilies.ResultHelper;
import dev.patika.tourismAgency.core.result.Result;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.room.SaveRoomRequest;
import dev.patika.tourismAgency.dto.request.room.UpdateRoomRequest;
import dev.patika.tourismAgency.dto.response.RoomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/room")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IModelMapperService modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<RoomResponse> save(@Valid @RequestBody SaveRoomRequest saveRoomRequest) {
        return this.roomService.save(saveRoomRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        boolean isDelete = this.roomService.delete(id);

        if (isDelete) {
            return ResultHelper.ok();
        }else {
            return ResultHelper.notFoundError(Msg.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<RoomResponse> get(@PathVariable("id") long id) {
            return this.roomService.findById(id);
    }

    @GetMapping("/filter/{hotelName}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<RoomResponse>> findByHotelName(@PathVariable("hotelName") String hotelName) {
            return this.roomService.getRoomsByHotelName(hotelName);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<RoomResponse> update(@Valid @RequestBody UpdateRoomRequest updateRoomRequest) {
        return this.roomService.update(updateRoomRequest);
    }

    @GetMapping("/date")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<RoomResponse>> findByDate(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return this.roomService.getRoomsByDate(startDate, endDate);
    }


}
