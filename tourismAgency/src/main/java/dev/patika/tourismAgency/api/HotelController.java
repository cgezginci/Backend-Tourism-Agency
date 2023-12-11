package dev.patika.tourismAgency.api;

import dev.patika.tourismAgency.bussiness.abstracts.IHotelService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.config.utilies.Msg;
import dev.patika.tourismAgency.core.config.utilies.ResultHelper;
import dev.patika.tourismAgency.core.result.ListResult;
import dev.patika.tourismAgency.core.result.Result;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dao.HotelRepo;
import dev.patika.tourismAgency.dto.request.hotel.SaveHotelRequest;
import dev.patika.tourismAgency.dto.request.hotel.UpdateHotelRequest;
import dev.patika.tourismAgency.dto.response.HotelResponse;
import dev.patika.tourismAgency.entities.Hotel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/hotel")
public class HotelController {


    private final IHotelService hotelService;


    private final IModelMapperService modelMapper;


    private final HotelRepo hotelRepo;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<HotelResponse> save(@Valid @RequestBody SaveHotelRequest saveHotelRequest) {
        return this.hotelService.save(saveHotelRequest);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
       boolean isDelete = this.hotelService.delete(id);

         if (isDelete) {
              return ResultHelper.ok();
         }else {
             return ResultHelper.notFoundError(Msg.NOT_FOUND);
         }

    }



    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<HotelResponse> findByHotelName(@PathVariable("name") String name) {
        return this.hotelService.findByHotelName(name);
       // return ResponseEntity.badRequest().body(this.hotelService.findByName(name)).getBody();

    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<HotelResponse> update(@Valid @RequestBody UpdateHotelRequest updateHotelRequest) {
        return this.hotelService.update(updateHotelRequest);

    }

}
