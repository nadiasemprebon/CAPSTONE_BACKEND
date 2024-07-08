package it.epicode.DiVino.TravelPackages;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.DiVino.Wineries.Winery;
import it.epicode.DiVino.Wineries.WineryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Validated
public class TravelPackageService {
    @Autowired
    private TravelPackageRepository travelPackageRepository;

    @Autowired
    private WineryRepository wineryRepository;

    @Autowired
    private Cloudinary cloudinary;

    private static final Logger logger = LoggerFactory.getLogger(TravelPackageService.class);

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

        // Copia le proprietà eccetto l'immagine
        BeanUtils.copyProperties(travelPackageDTORequest, entity, "imageUrl");

        // Recupera l'entità Winery dal repository utilizzando wineryId
        Winery winery = wineryRepository.findById(travelPackageDTORequest.getWineryId())
                .orElseThrow(() -> new EntityNotFoundException("Winery non trovato"));

        // Imposta l'entità Winery sull'entità TravelPackage
        entity.setWinery(winery);

        // Handle image upload to Cloudinary
        if (travelPackageDTORequest.getImageUrl() != null && !travelPackageDTORequest.getImageUrl().isEmpty()) {
            try {
                logger.info("Caricamento dell'immagine su Cloudinary");
                Map uploadResult = cloudinary.uploader().upload(travelPackageDTORequest.getImageUrl().getBytes(),
                        ObjectUtils.emptyMap());
                entity.setImageUrl(uploadResult.get("url").toString());
                logger.info("Caricamento completato, URL: " + uploadResult.get("url").toString());
            } catch (IOException e) {
                logger.error("Errore nel caricamento dell'immagine su Cloudinary", e);
                throw new RuntimeException("Errore nel caricamento dell'immagine su Cloudinary", e);
            }
        }

        // Salva l'entità TravelPackage nel repository
        travelPackageRepository.save(entity);

        // Crea la risposta da restituire
        TravelPackagesDTOResponse travelPackagesDTOResponse = new TravelPackagesDTOResponse();
        BeanUtils.copyProperties(entity, travelPackagesDTOResponse);

        // Assegna l'id della cantina a wineryId nella risposta
        travelPackagesDTOResponse.setWineryId(winery.getId());

        return travelPackagesDTOResponse;
    }

    // PUT
    public TravelPackagesDTOResponse modify(Long id, TravelPackageDTORequest travelPackageDTORequest) {
        if (!travelPackageRepository.existsById(id)) {
            throw new EntityNotFoundException("TravelPackage non trovato");
        }
        TravelPackage entity = travelPackageRepository.findById(id).get();

        // Copia le proprietà eccetto l'immagine
        BeanUtils.copyProperties(travelPackageDTORequest, entity, "imageUrl");

        // Imposta il winery
        Winery winery = wineryRepository.findById(travelPackageDTORequest.getWineryId())
                .orElseThrow(() -> new EntityNotFoundException("Winery non trovato"));
        entity.setWinery(winery);

        // Handle image upload to Cloudinary
        if (travelPackageDTORequest.getImageUrl() != null && !travelPackageDTORequest.getImageUrl().isEmpty()) {
            try {
                logger.info("Caricamento dell'immagine su Cloudinary");
                Map uploadResult = cloudinary.uploader().upload(travelPackageDTORequest.getImageUrl().getBytes(),
                        ObjectUtils.emptyMap());
                entity.setImageUrl(uploadResult.get("url").toString());
                logger.info("Caricamento completato, URL: " + uploadResult.get("url").toString());
            } catch (IOException e) {
                logger.error("Errore nel caricamento dell'immagine su Cloudinary", e);
                throw new RuntimeException("Errore nel caricamento dell'immagine su Cloudinary", e);
            }
        }

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
