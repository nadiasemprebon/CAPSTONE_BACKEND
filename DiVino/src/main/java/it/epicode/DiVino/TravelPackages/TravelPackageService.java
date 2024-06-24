package it.epicode.DiVino.TravelPackages;

import it.epicode.DiVino.Wineries.Winery;
import it.epicode.DiVino.Wineries.WineryRepository;
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

    @Autowired
    private WineryRepository wineryRepository;  // Repository per Winery

    public TravelPackagesDTOResponse findById(Long id) {
        if (!travelPackageRepository.existsById(id)) {
            throw new EntityNotFoundException("TravelPackage non trovato");
        }
        TravelPackage entity = travelPackageRepository.findById(id).get();
        TravelPackagesDTOResponse travelPackagesDTOResponse = new TravelPackagesDTOResponse();
        BeanUtils.copyProperties(entity, travelPackagesDTOResponse);
        return travelPackagesDTOResponse;
    }

    // POST
    @Transactional
    public TravelPackagesDTOResponse create(TravelPackageDTORequest travelPackageDTORequest) {
        TravelPackage entity = new TravelPackage();
        BeanUtils.copyProperties(travelPackageDTORequest, entity);

        // Imposta il winery
        Winery winery = wineryRepository.findById(travelPackageDTORequest.getWineryId())
                .orElseThrow(() -> new EntityNotFoundException("Winery non trovato"));
        entity.setWinery(winery);

        travelPackageRepository.save(entity);

        TravelPackagesDTOResponse travelPackagesDTOResponse = new TravelPackagesDTOResponse();
        BeanUtils.copyProperties(entity, travelPackagesDTOResponse);
        return travelPackagesDTOResponse;
    }

    // PUT
    public TravelPackagesDTOResponse modify(Long id, TravelPackageDTORequest travelPackageDTORequest) {
        if (!travelPackageRepository.existsById(id)) {
            throw new EntityNotFoundException("TravelPackage non trovato");
        }
        TravelPackage entity = travelPackageRepository.findById(id).get();
        BeanUtils.copyProperties(travelPackageDTORequest, entity);

        // Imposta il winery
        Winery winery = wineryRepository.findById(travelPackageDTORequest.getWineryId())
                .orElseThrow(() -> new EntityNotFoundException("Winery non trovato"));
        entity.setWinery(winery);

        travelPackageRepository.save(entity);

        TravelPackagesDTOResponse travelPackageDTOResponse = new TravelPackagesDTOResponse();
        BeanUtils.copyProperties(entity, travelPackageDTOResponse);
        return travelPackageDTOResponse;
    }

    // DELETE
    public String delete(Long id) {
        if (!travelPackageRepository.existsById(id)) {
            throw new EntityNotFoundException("TravelPackage non trovato");
        }
        travelPackageRepository.deleteById(id);
        return "TravelPackage eliminato";
    }

    // GET ALL
    public List<TravelPackageResponsePrj> findAll() {
        return travelPackageRepository.findAllBy();
    }
}