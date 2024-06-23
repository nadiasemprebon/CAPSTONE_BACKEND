package it.epicode.DiVino.Wineries;


import it.epicode.DiVino.TravelPackages.TravelPackageDTORequest;
import it.epicode.DiVino.TravelPackages.TravelPackageResponsePrj;
import it.epicode.DiVino.TravelPackages.TravelPackageService;
import it.epicode.DiVino.TravelPackages.TravelPackagesDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/wineries")
public class WineryController {
    @Autowired
    WineryService service;

    @GetMapping("/{id}")
    public ResponseEntity<WineryDTOResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));

    }


    @GetMapping
    public ResponseEntity<List<WineryResponsePrj>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }


    @PostMapping
    public ResponseEntity<WineryDTOResponse> create(@RequestBody WineryDTORequest request){
        return ResponseEntity.ok(service.create(request));

    }

    @PutMapping("/{id}")
    public ResponseEntity<WineryDTOResponse> modify(@PathVariable Long id, @RequestBody WineryDTORequest request){
        return ResponseEntity.ok(service.modify(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }
}

