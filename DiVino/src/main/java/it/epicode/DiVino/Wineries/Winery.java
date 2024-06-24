package it.epicode.DiVino.Wineries;


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

    //@ManyToOne
    //@JoinColumn(name = "admin_id", nullable = false)
    //private User admin;

    @OneToMany(mappedBy = "winery", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TravelPackage> travelPackages;
}
