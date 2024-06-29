package it.epicode.DiVino.TravelPackages;

import lombok.Data;
import java.util.Date;

@Data
public class TravelPackageDTORequest {
    private Long wineryId;
    private String travelPackageName;
    //private String imageUrl;
    private Date startDate;
    private Date endDate;
    private double price;
}

