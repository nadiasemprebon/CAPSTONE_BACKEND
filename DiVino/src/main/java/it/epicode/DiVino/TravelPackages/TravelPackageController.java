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


      @PostMapping
      public ResponseEntity<TravelPackagesDTOResponse> create(@RequestBody TravelPackageDTORequest request){
          return ResponseEntity.ok(service.create(request));

      }

      @PutMapping("/{id}")
      public ResponseEntity<TravelPackagesDTOResponse> modify(@PathVariable Long id, @RequestBody TravelPackageDTORequest request){
         return ResponseEntity.ok(service.modify(id, request));
      }


      @DeleteMapping("/{id}")
      public ResponseEntity<String> delete(@PathVariable Long id){
         return ResponseEntity.ok(service.delete(id));
      }
   }