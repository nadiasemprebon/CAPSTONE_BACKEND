package it.epicode.DiVino.TravelPackages;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.util.List;
@Service
@Validated
public class TravelPackageService {
    @Autowired
    private TravelPackageRepository travelPackageRepository;

    public TravelPackagesDTOResponse findById(Long id) {
        if (!travelPackageRepository.existsById(id)) {
            throw new EntityNotFoundException("TravelPackage non trovato");

        }
        TravelPackage entity = travelPackageRepository.findById(id).get();
        TravelPackagesDTOResponse travelpackagesDTOResponse = new TravelPackagesDTOResponse();
        BeanUtils.copyProperties(entity, travelpackagesDTOResponse);
        return travelpackagesDTOResponse;

    }

    //POST
    @Transactional
    public TravelPackagesDTOResponse create(TravelPackageDTORequest travelPackageDTORequest) {
        TravelPackage entity = new TravelPackage();
        BeanUtils.copyProperties(travelPackageDTORequest, entity);
        TravelPackagesDTOResponse travelPackagesDTOResponse = new TravelPackagesDTOResponse();
        BeanUtils.copyProperties(entity, travelPackagesDTOResponse);
        travelPackageRepository.save(entity);
        return travelPackagesDTOResponse;

    }

    //PUT
    public TravelPackagesDTOResponse modify(Long id, TravelPackageDTORequest travelPackageDTORequest) {
        if (!travelPackageRepository.existsById(id)) {
            throw new EntityNotFoundException("TravelPackage non trovato");


        }
        TravelPackage entity = travelPackageRepository.findById(id).get();
        BeanUtils.copyProperties(travelPackageDTORequest, entity);
        travelPackageRepository.save(entity);
        TravelPackagesDTOResponse travelPackageDTOResponse = new TravelPackagesDTOResponse();
        BeanUtils.copyProperties(entity, travelPackageDTOResponse);
        return travelPackageDTOResponse;
    }
    //DELETE
    public String delete(Long id) {
        if (!travelPackageRepository.existsById(id)) {
            throw new EntityNotFoundException("TravelPackage non trovato");

        }
        travelPackageRepository.deleteById(id);
        return "TravelPackage eliminato";

    }//GET ALL

    public List<TravelPackageResponsePrj> findAll() {
        return travelPackageRepository.findAllBy();

    }
}





