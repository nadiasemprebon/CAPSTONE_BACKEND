package it.epicode.DiVino.Wineries;


import it.epicode.DiVino.TravelPackages.TravelPackage;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Winery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String tastingExperience;
    private String image;

    @OneToMany(mappedBy = "winery", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TravelPackage> travelPackages;
}
