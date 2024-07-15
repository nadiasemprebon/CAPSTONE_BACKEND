package it.epicode.DiVino.Wineries;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.epicode.DiVino.TravelPackages.TravelPackage;
import it.epicode.DiVino.Users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wineries")
public class Winery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "winery_name", length = 100, nullable = false)
    private String wineryName;

    @Column(length = 255, nullable = false)
    private String location;

    @Column(length = 500, nullable = false)
    private String description;

    @Column(name = "tasting_experience", length = 500, nullable = false)
    private String tastingExperience;

    //@Column(name = "image_url", nullable = true)
    //private String imageUrl;



    @OneToMany(mappedBy = "winery", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<TravelPackage> travelPackages;
}
