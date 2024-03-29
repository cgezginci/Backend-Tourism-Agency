package dev.patika.tourismAgency.bussiness.concrets;

import dev.patika.tourismAgency.bussiness.abstracts.IHotelService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.config.utilies.Msg;
import dev.patika.tourismAgency.core.config.utilies.ResultHelper;
import dev.patika.tourismAgency.core.result.ListResult;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dao.FacilityRepo;
import dev.patika.tourismAgency.dao.HostelRepo;
import dev.patika.tourismAgency.dao.HotelRepo;
import dev.patika.tourismAgency.dto.request.hotel.SaveHotelRequest;
import dev.patika.tourismAgency.dto.request.hotel.UpdateHotelRequest;
import dev.patika.tourismAgency.dto.response.FacilityResponse;
import dev.patika.tourismAgency.dto.response.HostelResponse;
import dev.patika.tourismAgency.dto.response.HotelResponse;
import dev.patika.tourismAgency.entities.Facility;
import dev.patika.tourismAgency.entities.Hostel;
import dev.patika.tourismAgency.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class HotelManager implements IHotelService {

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private IModelMapperService modelMapper;

    @Autowired
    private HostelRepo hostelRepo;

    @Autowired
    private FacilityRepo facilityRepo;


    @Override
    public ResultData<HotelResponse> save(SaveHotelRequest saveHotelRequest) {
        try {
            // Hotel sınıfını dönüştür
            Hotel hotel = modelMapper.forRequest().map(saveHotelRequest, Hotel.class);

            List<Long> hostelIds = saveHotelRequest.getHostels();
            List<Long> facilityIds = saveHotelRequest.getFacilities();

            // Tüm hostel ve facility kimliklerinin geçerli olup olmadığını kontrol et
            boolean allHostelsValid = hostelIds.stream()
                    .allMatch(hostelId -> hostelRepo.findById(hostelId).isPresent());

            boolean allFacilitiesValid = facilityIds.stream()
                    .allMatch(facilityId -> facilityRepo.findById(facilityId).isPresent());

            if (!allHostelsValid || !allFacilitiesValid) {
                return ResultData.error(Msg.VALIDATE_ERROR, "One or more hostel/facility IDs are invalid");
            }

            // Hostel ve Facility nesnelerini çek
            List<Hostel> hostelList = saveHotelRequest.getHostels().stream()
                    .map(hostelId -> hostelRepo.findById(hostelId).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            List<Facility> facilityList = saveHotelRequest.getFacilities().stream()
                    .map(facilityId -> facilityRepo.findById(facilityId).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            if (hostelList.isEmpty() || facilityList.isEmpty()) {
                return ResultData.error(Msg.VALIDATE_ERROR, "Hostel or facility list is empty.");
            }

            // Hostel ve Facility listelerini Hotel nesnesine set et
            hotel.setHostels(hostelList);
            hotel.setFacilities(facilityList);

            // Veritabanına kaydet
            hotelRepo.save(hotel);

            // Oluşturulan Hotel nesnesini response olarak dön
            return ResultHelper.created(this.modelMapper.forResponse().map(hotel, HotelResponse.class));
        } catch (Exception e) {
            return ResultData.error(Msg.VALIDATE_ERROR, "500");
        }
    }


    @Override
    public Hotel get(long id) {
        return this.hotelRepo.findById(id).orElseThrow( );
    }

    @Override
    public boolean delete(long id) {
       try {
        Hotel hotel = this.get(id);
        this.hotelRepo.delete(hotel);
        return true;
       }catch (Exception e){
           return false;
       }
    }

    @Override
    public ResultData<HotelResponse> update(UpdateHotelRequest updateHotelRequest) {
        Long HotelId = updateHotelRequest.getId();
        try {
            Hotel response = this.hotelRepo.findById(HotelId).orElseThrow();


            this.modelMapper.forRequest().map(updateHotelRequest, response);

            List<Long> hostelIds = updateHotelRequest.getHostels();
            List<Long> facilityIds = updateHotelRequest.getFacilities();

            boolean allHostelsValid = hostelIds.stream()
                    .allMatch(hostelId -> hostelRepo.findById(hostelId).isPresent());

            boolean allFacilitiesValid = facilityIds.stream()
                    .allMatch(facilityId -> facilityRepo.findById(facilityId).isPresent());

            if (!allHostelsValid || !allFacilitiesValid) {
                return ResultData.error(Msg.VALIDATE_ERROR, "One or more hostel/facility IDs are invalid");
            }

             List<Hostel> hostelList = updateHotelRequest.getHostels().stream()
                    .map(hostelId -> hostelRepo.findById(hostelId).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());



             List<Facility> facilityList = updateHotelRequest.getFacilities().stream().map(facilityId -> facilityRepo.findById(facilityId).orElse(null))
                     .filter(Objects::nonNull)
                     .collect(Collectors.toList());

            if (hostelList.isEmpty() || facilityList.isEmpty()) {
                return ResultData.error(Msg.VALIDATE_ERROR, "Hostel or facility list is empty.");
            }

             response.setFacilities(facilityList);
            response.setHostels(hostelList);

                this.hotelRepo.save(response);

                HotelResponse hotelResponse = this.modelMapper.forResponse().map(response, HotelResponse.class);

                return ResultHelper.success(hotelResponse);

        }catch (Exception e){
            return ResultData.error(Msg.VALIDATE_ERROR, "500");
        }

    }

    @Override
    public ListResult<Hotel> findByName(String name) {
        List<Hotel> hotelList = this.hotelRepo.findByNameIgnoreCase(name);

        if (hotelList.isEmpty()) {
            return ResultHelper.notFoundErrorList(Msg.NOT_FOUND);
        } else {
            return ResultHelper.successList(hotelList);
        }
    }

    @Override
    public ResultData<List<HotelResponse>> findByHotelName(String name) {
        List<Hotel> hotelList = this.hotelRepo.findByNameIgnoreCase(name);

        if (hotelList.isEmpty()) {
            return ResultData.error(Msg.NOT_FOUND, "404");
        }

        try {
            List<HotelResponse> responseList = new ArrayList<>();
            for (Hotel hotel : hotelList) {
                HotelResponse response = this.modelMapper.forResponse().map(hotel, HotelResponse.class);
                responseList.add(response);
            }
            return ResultHelper.success(responseList);
        } catch (Exception e) {
            return ResultData.error(Msg.VALIDATE_ERROR, "500");
        }
    }

    @Override
    public ResultData<List<HotelResponse>> findAll() {
        List<Hotel> hotelList = this.hotelRepo.findAll();

        try {
            List<HotelResponse> responseList = new ArrayList<>();
            for (Hotel hotel : hotelList) {
                HotelResponse response = this.modelMapper.forResponse().map(hotel, HotelResponse.class);
                responseList.add(response);
            }
            return ResultHelper.success(responseList);
        } catch (Exception e) {
            return ResultData.error(Msg.VALIDATE_ERROR, "500");
        }
    }
}
