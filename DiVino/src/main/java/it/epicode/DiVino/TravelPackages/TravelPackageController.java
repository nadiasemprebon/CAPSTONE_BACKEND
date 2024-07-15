package it.epicode.DiVino.TravelPackages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/travel_packages")
public class TravelPackageController {

    @Autowired
    TravelPackageService service;

    @GetMapping("/{id}")
    public ResponseEntity<TravelPackagesDTOResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TravelPackageResponsePrj>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<TravelPackagesDTOResponse> create(
            @RequestParam("wineryId") Long wineryId,
            @RequestParam("travelPackageName") String travelPackageName,
            @RequestParam("imageUrl") MultipartFile imageUrl,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam("price") double price) {

        TravelPackageDTORequest request = new TravelPackageDTORequest();
        request.setWineryId(wineryId);
        request.setTravelPackageName(travelPackageName);
        request.setImageUrl(imageUrl);
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        request.setPrice(price);

        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelPackagesDTOResponse> modify(
            @PathVariable Long id,
            @RequestParam("wineryId") Long wineryId,
            @RequestParam("travelPackageName") String travelPackageName,
            @RequestParam("imageUrl") MultipartFile imageUrl,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam("price") double price) {

        TravelPackageDTORequest request = new TravelPackageDTORequest();
        request.setWineryId(wineryId);
        request.setTravelPackageName(travelPackageName);
        request.setImageUrl(imageUrl);
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        request.setPrice(price);

        return ResponseEntity.ok(service.modify(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // Restituisce 204 No Content
    }
}