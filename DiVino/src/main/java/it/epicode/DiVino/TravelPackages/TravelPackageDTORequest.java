package it.epicode.DiVino.TravelPackages;

import lombok.Data;
import java.util.Date;

@Data
public class TravelPackageDTORequest {
    private Date travelDate;
    private double price;
}
