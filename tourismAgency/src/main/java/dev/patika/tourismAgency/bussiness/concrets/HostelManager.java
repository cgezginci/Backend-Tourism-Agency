package dev.patika.tourismAgency.bussiness.concrets;

import dev.patika.tourismAgency.bussiness.abstracts.IHostelService;
import dev.patika.tourismAgency.core.config.modelMapper.IModelMapperService;
import dev.patika.tourismAgency.core.config.utilies.Msg;
import dev.patika.tourismAgency.core.config.utilies.ResultHelper;
import dev.patika.tourismAgency.core.result.ResultData;
import dev.patika.tourismAgency.dao.HostelRepo;
import dev.patika.tourismAgency.dto.request.hostel.SaveHostelRequest;
import dev.patika.tourismAgency.dto.response.HostelResponse;
import dev.patika.tourismAgency.entities.Hostel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HostelManager implements IHostelService {
    @Autowired
    private HostelRepo hostelRepo;

    @Autowired
    private IModelMapperService modelMapper;


    @Override
    public ResultData<HostelResponse> save(SaveHostelRequest saveHostelRequest) {
        try {
            Hostel hostel = modelMapper.forRequest().map(saveHostelRequest, Hostel.class);
            this.hostelRepo.save(hostel);

           return ResultHelper.created(this.modelMapper.forResponse().map(hostel, HostelResponse.class));
        } catch (Exception e) {
            return  ResultData.error(Msg.VALIDATE_ERROR , "500");
        }
    }

    @Override
    public Hostel get(long id) {
        return this.hostelRepo.findById(id).orElseThrow();
    }

    @Override
    public ResultData<Hostel> get2(long id) {
        try {
            Hostel hostel = this.hostelRepo.findById(id).orElseThrow();
            return ResultHelper.success(hostel);
        } catch (Exception e) {
            return ResultData.error(Msg.NOT_FOUND, "404");
        }




    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Hostel update(Hostel hostel) {
        return null;
    }


}
