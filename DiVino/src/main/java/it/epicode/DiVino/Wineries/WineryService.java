package it.epicode.DiVino.Wineries;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.util.List;

@Service
@Validated
public class WineryService {

    @Autowired
    private WineryRepository wineryRepository;

    //GET PER ID
    public WineryDTOResponse findById(Long id) {
        if(!wineryRepository.existsById(id)) {
            throw new EntityNotFoundException("Winery non trovata");
        }
        Winery entity = wineryRepository.findById(id).get();
        WineryDTOResponse wineryDTOResponse = new WineryDTOResponse();
        BeanUtils.copyProperties(entity, wineryDTOResponse);
        return wineryDTOResponse;
    }
    //POST
    @Transactional
    public WineryDTOResponse create(WineryDTORequest wineryDTORequest) {
        Winery entity = new Winery();
        BeanUtils.copyProperties(wineryDTORequest, entity);

        // Salva l'entità nel repository per ottenere l'ID generato
        entity = wineryRepository.save(entity);

        // Dopo il salvataggio, copia le proprietà dall'entità al DTO di risposta
        WineryDTOResponse wineryDTOResponse = new WineryDTOResponse();
        BeanUtils.copyProperties(entity, wineryDTOResponse);

        return wineryDTOResponse;
    }

    //PUT
    public WineryDTOResponse modify(Long id, WineryDTORequest wineryDTORequest){
        if(!wineryRepository.existsById(id)) {
            throw new EntityNotFoundException("Winery non trovata");


        }
        Winery entity = wineryRepository.findById(id).get();
        BeanUtils.copyProperties(wineryDTORequest, entity);
        wineryRepository.save(entity);
        WineryDTOResponse wineryDTOResponse = new WineryDTOResponse();
        BeanUtils.copyProperties(entity, wineryDTOResponse);
        return wineryDTOResponse;

        }

        //DELETE
        public String delete(Long id){
        if(!wineryRepository.existsById(id)) {
            throw new EntityNotFoundException("Winery non trovata");
        }
        wineryRepository.deleteById(id);
        return "Winery eliminata";

        }

        //GET ALL

        public List<WineryResponsePrj> findAll(){
        return wineryRepository.findAllBy();
        }

}