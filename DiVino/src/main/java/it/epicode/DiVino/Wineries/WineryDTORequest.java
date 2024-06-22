package it.epicode.DiVino.Wineries;

import lombok.Data;

@Data
public class WineryDTORequest {
    private String wineryName;
    private String description;
    private String tastingExperience;
    private String imageUrl;
}
