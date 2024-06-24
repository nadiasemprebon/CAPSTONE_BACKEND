package it.epicode.DiVino.Wineries;

import it.epicode.DiVino.TravelPackages.TravelPackageDTORequest;
import lombok.Data;

import java.util.List;

@Data
public class WineryDTORequest {
    private String wineryName;
    private String location;
    private String description;
    private String tastingExperience;
    //private String imageUrl;
    private List<TravelPackageDTORequest> travelPackages;
}
