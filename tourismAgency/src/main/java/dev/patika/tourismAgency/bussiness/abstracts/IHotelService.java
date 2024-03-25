package dev.patika.tourismAgency.bussiness.abstracts;

import dev.patika.tourismAgency.core.result.ListResult;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.hotel.SaveHotelRequest;
import dev.patika.tourismAgency.dto.request.hotel.UpdateHotelRequest;
import dev.patika.tourismAgency.dto.response.HotelResponse;
import dev.patika.tourismAgency.entities.Hotel;

import java.util.List;

public interface IHotelService {

    ResultData<HotelResponse> save(SaveHotelRequest saveHotelRequest);

    Hotel get(long id);

    boolean delete(long id);

    ResultData<HotelResponse> update(UpdateHotelRequest updateHotelRequest);

    ListResult<Hotel> findByName(String name);

    ResultData<List<HotelResponse>> findByHotelName(String name);

    ResultData<List<HotelResponse>> findAll();
}
