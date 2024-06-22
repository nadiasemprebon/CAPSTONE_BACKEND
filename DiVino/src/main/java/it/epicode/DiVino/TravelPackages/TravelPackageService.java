package it.epicode.DiVino.TravelPackages;

import it.epicode.DiVino.Exceptions.EntityNotFoundException;
import it.epicode.DiVino.Users.User;
import it.epicode.DiVino.Users.UserDTORequest;
import it.epicode.DiVino.Users.UserDTOResponse;
import it.epicode.DiVino.Users.UserResponsePrj;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class TravelPackageService {

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
    public TravelPackagesDTOResponse create(TravelPackagesDTOResponse travelPackageDTORequest) {
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





