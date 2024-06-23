package it.epicode.DiVino.Users;
import it.epicode.DiVino.TravelPackages.TravelPackageDTORequest;
import it.epicode.DiVino.TravelPackages.TravelPackagesDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users ")
public class UserController {
    @Autowired
    UserService service;

    // Metodo per ottenere un autore specifico in base all'ID. Quando si effettua una richiesta GET a /api/autori/{id}, questo metodo viene chiamato e restituisce l'autore con l'ID specificato.
    @GetMapping("/{id}")
    public ResponseEntity<UserDTOResponse> findById(@PathVariable Long id){

        return ResponseEntity.ok(service.findById(id));
    }

    // Metodo per ottenere tutti gli autori. Quando si effettua una richiesta GET a /api/autori, questo metodo viene chiamato e restituisce una lista di tutti gli autori.
    @GetMapping
    public ResponseEntity<List<UserResponsePrj>>findAll(){

        return ResponseEntity.ok(service.findAll());
    }

    // Metodo per creare un nuovo autore. Quando si effettua una richiesta POST a /api/autori con i dettagli dell'autore nel corpo della richiesta, questo metodo viene chiamato e crea un nuovo autore.
    @PostMapping
    public ResponseEntity<UserDTOResponse> create(@RequestBody UserDTORequest request){
        return ResponseEntity.ok(service.create(request));

    }

    // Metodo per modificare un autore esistente. Quando si effettua una richiesta PUT a /api/autori/{id} con i nuovi dettagli dell'autore nel corpo della richiesta, questo metodo viene chiamato e modifica l'autore con l'ID specificato.
    @PutMapping("/{id}")
    public ResponseEntity<UserDTOResponse> modify(@PathVariable Long id, @RequestBody UserDTORequest request){
        return ResponseEntity.ok(service.modify(id, request));
    }

    // Metodo per eliminare un autore. Quando si effettua una richiesta DELETE a /api/autori/{id}, questo metodo viene chiamato e elimina l'autore con l'ID specificato.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }
}