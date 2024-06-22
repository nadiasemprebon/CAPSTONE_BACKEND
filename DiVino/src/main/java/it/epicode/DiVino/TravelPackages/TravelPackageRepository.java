package it.epicode.DiVino.TravelPackages;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {
    List<TravelPackageResponsePrj> findAllBy();
}

