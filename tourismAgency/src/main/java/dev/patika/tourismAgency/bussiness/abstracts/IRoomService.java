package dev.patika.tourismAgency.bussiness.abstracts;

import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.room.SaveRoomRequest;
import dev.patika.tourismAgency.dto.request.room.UpdateRoomRequest;
import dev.patika.tourismAgency.dto.response.RoomResponse;
import dev.patika.tourismAgency.entities.Facility;
import dev.patika.tourismAgency.entities.Room;

import java.time.LocalDate;
import java.util.List;

public interface IRoomService {

    ResultData<RoomResponse> save(SaveRoomRequest saveRoomRequest);

    boolean delete(long id);

    ResultData<RoomResponse> findById(long id);

    Room get(long id);

    ResultData<List<RoomResponse>> getRoomsByHotelName(String hotelName);

    ResultData<RoomResponse> update(UpdateRoomRequest updateRoomRequest);

    ResultData<List<RoomResponse>> getRoomsByDate(LocalDate startDate, LocalDate endDate);


}
