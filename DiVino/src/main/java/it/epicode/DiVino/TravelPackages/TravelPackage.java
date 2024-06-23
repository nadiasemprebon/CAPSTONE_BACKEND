package it.epicode.DiVino.TravelPackages;

import it.epicode.DiVino.Wineries.Winery;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "travel_packages")
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "winery_id", nullable = false)
    private Winery winery;


    @Column(name = "travel_package_name", length = 100, nullable = false)
    private String travelPackageName;


    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;


    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;


    @Column(name = "price", nullable = false)
    private double price;
}
