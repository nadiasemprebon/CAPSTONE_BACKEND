package it.epicode.DiVino.TravelPackages;

import lombok.Data;
import java.util.Date;

@Data
public class TravelPackagesDTOResponse {
    private Long id;
    private Date travelDate;
    private double price;
}
