package it.epicode.DiVino.TravelPackages;

import lombok.Data;
import java.util.Date;

@Data
public class TravelPackagesDTOResponse {
    private Long id;
    private Long wineryId;
    private String travelPackageName;
    private Date startDate;
    private Date endDate;
    private double price;
}
