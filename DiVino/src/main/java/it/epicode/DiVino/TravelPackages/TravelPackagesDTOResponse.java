package it.epicode.DiVino.TravelPackages;

import it.epicode.DiVino.Wineries.WineryDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
public class TravelPackagesDTOResponse {
    private Long id;
    private Long wineryId;
    private String travelPackageName;
    //private String imageUrl;
    private Date startDate;
    private Date endDate;
    private double price;
}
