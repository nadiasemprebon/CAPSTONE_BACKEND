package it.epicode.DiVino.TravelPackages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/travel_packages")
public class TravelPackageController {


      @Autowired
      TravelPackageService service;

      @GetMapping("/{id}")
      public ResponseEntity<TravelPackagesDTOResponse> findById(@PathVariable Long id){
         return ResponseEntity.ok(service.findById(id));
      }


      @GetMapping
      public ResponseEntity<List<TravelPackageResponsePrj>> findAll(){
         return ResponseEntity.ok(service.findAll());
      }

      // Metodo per creare un nuovo autore. Quando si effettua una richiesta POST a /api/autori con i dettagli dell'autore nel corpo della richiesta, questo metodo viene chiamato e crea un nuovo autore.
      @PostMapping
      public ResponseEntity<TravelPackagesDTOResponse> create(@RequestBody TravelPackageDTORequest request){
          return ResponseEntity.ok(service.create(request));

      }

      // Metodo per modificare un autore esistente. Quando si effettua una richiesta PUT a /api/autori/{id} con i nuovi dettagli dell'autore nel corpo della richiesta, questo metodo viene chiamato e modifica l'autore con l'ID specificato.
      @PutMapping("/{id}")
      public ResponseEntity<TravelPackagesDTOResponse> modify(@PathVariable Long id, @RequestBody TravelPackageDTORequest request){
         return ResponseEntity.ok(service.modify(id, request));
      }

      // Metodo per eliminare un autore. Quando si effettua una richiesta DELETE a /api/autori/{id}, questo metodo viene chiamato e elimina l'autore con l'ID specificato.
      @DeleteMapping("/{id}")
      public ResponseEntity<String> delete(@PathVariable Long id){
         return ResponseEntity.ok(service.delete(id));
      }
   }