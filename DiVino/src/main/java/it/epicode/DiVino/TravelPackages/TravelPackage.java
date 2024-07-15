package it.epicode.DiVino.TravelPackages;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private Winery winery;


    @Column(name = "travel_package_name", length = 100, nullable = false)
    private String travelPackageName;



    @Column(name = "image_url", nullable = true)
    private String imageUrl;



    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;


    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;


    @Column(name = "price", nullable = false)
    private double price;
}
