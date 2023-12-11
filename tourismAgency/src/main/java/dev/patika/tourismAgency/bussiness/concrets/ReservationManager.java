package dev.patika.tourismAgency.bussiness.concrets;

import dev.patika.tourismAgency.bussiness.abstracts.IReservationService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.config.utilies.Msg;
import dev.patika.tourismAgency.core.config.utilies.ResultHelper;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dao.ReservationRepo;
import dev.patika.tourismAgency.dao.RoomRepo;
import dev.patika.tourismAgency.dto.request.reservation.SaveReservationRequest;
import dev.patika.tourismAgency.dto.request.reservation.UpdateReservationRequest;
import dev.patika.tourismAgency.dto.response.ReservationResponse;
import dev.patika.tourismAgency.dto.response.RoomResponse;
import dev.patika.tourismAgency.entities.Reservation;
import dev.patika.tourismAgency.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReservationManager implements IReservationService {

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private IModelMapperService modelMapper;

    @Autowired
    private RoomRepo roomRepo;


    @Override
    public ResultData<ReservationResponse> save(SaveReservationRequest saveReservationRequest) {
        try {
            Reservation reservation = modelMapper.forRequest().map(saveReservationRequest, Reservation.class);
            Room room = roomRepo.findById(saveReservationRequest.getRoom().getId()).orElse(null);

            if (room != null && room.getStock() >0){

                LocalDate startDate = saveReservationRequest.getStartDate();
                LocalDate endDate = saveReservationRequest.getEndDate();
                if (startDate.isAfter(room.getPeriodStart()) && endDate.isBefore(room.getPeriodEnd())){
                    int reservationPrice = room.getPrice() * saveReservationRequest.getPersonCount();
                    reservation.setReservationPrice(reservationPrice);

                    reservation.setRoom(room);
                    room.setStock(room.getStock()-1);
                    roomRepo.save(room);
                    reservationRepo.save(reservation);
                    ReservationResponse reservationResponse = modelMapper.forResponse().map(reservation, ReservationResponse.class);
                    return ResultHelper.success(reservationResponse);
                }else {
                    return ResultData.error("Belirtilen tarih aralığında odaya rezervasyon yapılamaz", "500");
                }

            }else {
                return ResultData.error(Msg.ROOM_STOCK_ERROR, "500");
            }

        } catch (Exception e) {
            return ResultData.error(Msg.VALIDATE_ERROR, "500");
        }
    }

    @Override
    public boolean delete(long id) {
        try {
            Reservation reservation = this.get(id);


            Room room = reservation.getRoom();
            room.setStock(room.getStock() + 1);
            roomRepo.save(room);


            reservationRepo.delete(reservation);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public Reservation get(long id) {
        return this.reservationRepo.findById(id).orElseThrow();
    }

    @Override
    public ResultData<ReservationResponse> getReservationById(long id) {
        try {
            Reservation reservation = this.get(id);
            ReservationResponse reservationResponse = modelMapper.forResponse().map(reservation, ReservationResponse.class);
            return ResultHelper.success(reservationResponse);

        }catch (Exception e){
            return ResultData.error(Msg.NOT_FOUND, "404");
        }
    }

    @Override
    public ResultData<ReservationResponse> update(UpdateReservationRequest updateReservationRequest) {
        Long id = updateReservationRequest.getId();
        try {
            Reservation reservation = this.get(id);
            Room room = roomRepo.findById(updateReservationRequest.getRoom().getId()).orElse(null);
            if (room != null ){
                Room oldRoom = reservation.getRoom();
                if (oldRoom.getId() != (room.getId())){
                    oldRoom.setStock(oldRoom.getStock() + 1);
                    roomRepo.save(oldRoom);

                    room.setStock(room.getStock() - 1);
                    roomRepo.save(room);
                }
                LocalDate startDate = updateReservationRequest.getStartDate();
                LocalDate endDate = updateReservationRequest.getEndDate();
                if (startDate.isAfter(room.getPeriodStart()) && endDate.isBefore(room.getPeriodEnd())){
                    int reservationPrice = room.getPrice() * updateReservationRequest.getPersonCount();
                    reservation.setReservationPrice(reservationPrice);

                    reservation.setRoom(room);
                    reservationRepo.save(reservation);
                    ReservationResponse reservationResponse = modelMapper.forResponse().map(reservation, ReservationResponse.class);
                    return ResultHelper.success(reservationResponse);
                }else {
                    return ResultData.error("Belirtilen tarih aralığında odaya rezervasyon yapılamaz", "500");
                }

            }else {
                return ResultData.error(Msg.ROOM_STOCK_ERROR, "500");
            }

        } catch (Exception e) {
            return ResultData.error(Msg.VALIDATE_ERROR, "500");
        }

    }
}
