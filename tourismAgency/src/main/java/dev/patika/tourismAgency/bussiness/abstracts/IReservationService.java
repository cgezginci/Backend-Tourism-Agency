package dev.patika.tourismAgency.bussiness.abstracts;

import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.reservation.SaveReservationRequest;
import dev.patika.tourismAgency.dto.request.reservation.UpdateReservationRequest;
import dev.patika.tourismAgency.dto.request.room.UpdateRoomRequest;
import dev.patika.tourismAgency.dto.response.ReservationResponse;
import dev.patika.tourismAgency.entities.Reservation;

public interface IReservationService {

    ResultData<ReservationResponse> save(SaveReservationRequest saveReservationRequest);

    boolean delete(long id);

    Reservation get(long id);

    ResultData<ReservationResponse> getReservationById(long id);

    ResultData<ReservationResponse> update(UpdateReservationRequest updateReservationRequest);


}
