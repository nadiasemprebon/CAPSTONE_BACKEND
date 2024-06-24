package it.epicode.DiVino.Wineries;

import it.epicode.DiVino.TravelPackages.TravelPackagesDTOResponse;
import lombok.Data;
import java.util.List;

@Data
public class WineryDTOResponse {
    private Long id;
    private String wineryName;
    private String location;
    private String description;
    private String tastingExperience;
    private String imageUrl;
    private List<TravelPackagesDTOResponse> travelPackages;
}
