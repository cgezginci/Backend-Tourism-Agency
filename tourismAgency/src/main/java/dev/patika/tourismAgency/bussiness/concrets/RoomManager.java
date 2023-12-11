package dev.patika.tourismAgency.bussiness.concrets;

import dev.patika.tourismAgency.bussiness.abstracts.IRoomService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.config.utilies.Msg;
import dev.patika.tourismAgency.core.config.utilies.ResultHelper;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dao.HotelRepo;
import dev.patika.tourismAgency.dao.RoomFeaturesRepo;
import dev.patika.tourismAgency.dao.RoomRepo;
import dev.patika.tourismAgency.dao.RoomTypesRepo;
import dev.patika.tourismAgency.dto.request.room.SaveRoomRequest;
import dev.patika.tourismAgency.dto.request.room.UpdateRoomRequest;
import dev.patika.tourismAgency.dto.response.RoomResponse;
import dev.patika.tourismAgency.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoomManager implements IRoomService {
    @Autowired
    private RoomTypesRepo roomTypesRepo;

    @Autowired
    private IModelMapperService modelMapper;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private RoomFeaturesRepo roomFeaturesRepo;

    @Autowired
    private HotelRepo hotelRepo;

    @Override
    public ResultData<RoomResponse> save(SaveRoomRequest saveRoomRequest) {
        try {
            Room room = modelMapper.forRequest().map(saveRoomRequest, Room.class);

            List<RoomFeatures> roomFeaturesList = saveRoomRequest.getRoomFeatures().stream()
                    .map(roomFeaturesId -> roomFeaturesRepo.findById(roomFeaturesId).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            room.setRoomFeatures(roomFeaturesList);

            RoomTypes roomTypes = roomTypesRepo.findById(saveRoomRequest.getRoomType().getId()).orElse(null);
            room.setRoomType(roomTypes);

            Hotel hotel = hotelRepo.findById(saveRoomRequest.getHotel().getId()).orElse(null);
            room.setHotel(hotel);

            roomRepo.save(room);

            RoomResponse roomResponse = modelMapper.forResponse().map(room, RoomResponse.class);
            return ResultHelper.success(roomResponse);
        } catch (Exception e) {
            return ResultData.error(Msg.VALIDATE_ERROR, "500");
        }
    }


    @Override
    public Room get(long id) {
        return this.roomRepo.findById(id).orElseThrow();
    }

    @Override
    public ResultData<List<RoomResponse>> getRoomsByHotelName(String hotelName) {
        try {
            List<Room> roomList = roomRepo.findByHotel_NameIgnoreCase(hotelName);

            if (roomList.isEmpty()) {
                return ResultData.error("Otel bulunamadı.", "404");
            }

            List<RoomResponse> roomResponseList = roomList.stream()
                    .map(room -> modelMapper.forResponse().map(room, RoomResponse.class))
                    .collect(Collectors.toList());

            return ResultHelper.success(roomResponseList);
        } catch (Exception e) {
            return ResultData.error("İşlem sırasında bir hata oluştu.", "500");
        }
    }

    @Override
    public ResultData<RoomResponse> update(UpdateRoomRequest updateRoomRequest) {
        Long id = updateRoomRequest.getId();
        try {
            Room response = this.roomRepo.findById(id).orElseThrow();
            this.modelMapper.forRequest().map(updateRoomRequest, response);

            RoomTypes roomTypes = roomTypesRepo.findById(updateRoomRequest.getRoomType().getId()).orElse(null);
            response.setRoomType(roomTypes);

            Hotel hotel = hotelRepo.findById(updateRoomRequest.getHotel().getId()).orElse(null);
            response.setHotel(hotel);

            List<RoomFeatures> roomFeaturesList = updateRoomRequest.getRoomFeatures().stream()
                    .map(roomFeaturesId -> roomFeaturesRepo.findById(roomFeaturesId).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            response.setRoomFeatures(roomFeaturesList);

            this.roomRepo.save(response);
            return ResultHelper.success(modelMapper.forResponse().map(response, RoomResponse.class));

        }catch (Exception e){
            return ResultData.error(Msg.VALIDATE_ERROR, "500");
        }
    }

    @Override
    public ResultData<List<RoomResponse>> getRoomsByDate(LocalDate startDate, LocalDate endDate) {
        try {
            List<Room> availableRooms = roomRepo.findByPeriodStartAfterAndPeriodEndBefore(startDate, endDate);

            if (availableRooms.isEmpty()) {
                return ResultData.error(Msg.NOT_FOUND, "404");
            }

            List<RoomResponse> roomResponseList = availableRooms.stream()
                    .map(room -> modelMapper.forResponse().map(room, RoomResponse.class))
                    .collect(Collectors.toList());

            return ResultHelper.success(roomResponseList);
        } catch (Exception e) {
            return ResultData.error(Msg.VALIDATE_ERROR, "500");
        }
    }



    @Override
    public boolean delete(long id) {
        try {
            Room room = this.get(id);
            this.roomRepo.delete(room);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public ResultData<RoomResponse> findById(long id) {
        try {
            Room room = this.get(id);
            RoomResponse roomResponse = modelMapper.forResponse().map(room, RoomResponse.class);
            return ResultHelper.success(roomResponse);

        }catch (Exception e){
            return ResultData.error(Msg.NOT_FOUND, "404");
        }
    }


}
