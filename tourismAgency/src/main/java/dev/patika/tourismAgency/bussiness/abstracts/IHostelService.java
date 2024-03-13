package dev.patika.tourismAgency.bussiness.abstracts;

import dev.patika.tourismAgency.core.result.ListResult;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dto.request.hostel.SaveHostelRequest;
import dev.patika.tourismAgency.dto.response.FacilityResponse;
import dev.patika.tourismAgency.dto.response.HostelResponse;
import dev.patika.tourismAgency.entities.Hostel;

import java.util.List;

public interface IHostelService {

    ResultData<HostelResponse> save(SaveHostelRequest saveHostelRequest);

    Hostel get(long id);

    boolean delete(long id);

    Hostel update(Hostel hostel);

    ResultData<Hostel> get2(long id);

    ResultData<List<HostelResponse>> findAll();

    ResultData<Hostel>findByHostelName(String name);
}
