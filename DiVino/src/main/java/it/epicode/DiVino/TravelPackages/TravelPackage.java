package it.epicode.DiVino.TravelPackages;


import it.epicode.DiVino.Wineries.Winery;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date travelDate;

    @ManyToOne
    @JoinColumn(name = "winery_id")
    private Winery winery;
}
