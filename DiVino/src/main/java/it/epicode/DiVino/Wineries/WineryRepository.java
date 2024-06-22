package it.epicode.DiVino.Wineries;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WineryRepository extends JpaRepository<Winery, Long> {

    //get all o tutto ciò che finisce in all non vuole i dto quindi devi crearti
    //l'interfaccia ResponsePrj. Se vuoi dei metodi aggiuntivi che ad esempio trovano
    //una lista di cantine ti cei il metodo nel service e la query nella repository.
    List<WineryResponsePrj>findAllBy();
}
