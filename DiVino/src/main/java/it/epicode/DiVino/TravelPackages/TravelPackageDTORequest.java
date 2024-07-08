package it.epicode.DiVino.TravelPackages;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class TravelPackageDTORequest {
    private Long wineryId;
    private String travelPackageName;
    private MultipartFile imageUrl;
    private Date startDate;
    private Date endDate;
    private double price;
}

