package dev.patika.tourismAgency.api;

import dev.patika.tourismAgency.bussiness.abstracts.IReservationService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.config.utilies.Msg;
import dev.patika.tourismAgency.core.config.utilies.ResultHelper;
import dev.patika.tourismAgency.core.result.Result;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.reservation.SaveReservationRequest;
import dev.patika.tourismAgency.dto.request.reservation.UpdateReservationRequest;
import dev.patika.tourismAgency.dto.response.ReservationResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/reservation")
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @Autowired
    private IModelMapperService modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<ReservationResponse> save(@Valid @RequestBody SaveReservationRequest saveReservationRequest) {
        return this.reservationService.save(saveReservationRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<ReservationResponse> get(@PathVariable("id") long id) {
        return this.reservationService.getReservationById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        boolean isDelete = this.reservationService.delete(id);

        if (isDelete) {
            return ResultHelper.ok();
        }else {
            return ResultHelper.notFoundError(Msg.NOT_FOUND);
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<ReservationResponse> update(@Valid @RequestBody UpdateReservationRequest updateReservationRequest) {
        return this.reservationService.update(updateReservationRequest);
    }

}
