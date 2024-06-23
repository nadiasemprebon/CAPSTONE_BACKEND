package it.epicode.DiVino.Users;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTOResponse findById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User non trovato");
        }
        User entity = userRepository.findById(id).get();
        UserDTOResponse userDTOResponse = new UserDTOResponse();
        BeanUtils.copyProperties(entity, userDTOResponse);
        return userDTOResponse;
    }

    // POST
    @Transactional
    public UserDTOResponse create(UserDTORequest userDTORequest) {
        User entity = new User();
        BeanUtils.copyProperties(userDTORequest, entity);
        entity.setUserName(userDTORequest.getUsername()); // Assicurati di mappare userName
        userRepository.save(entity);
        UserDTOResponse userDTOResponse = new UserDTOResponse();
        BeanUtils.copyProperties(entity, userDTOResponse);
        return userDTOResponse;
    }

    // PUT
    public UserDTOResponse modify(Long id, UserDTORequest userDTORequest) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User non trovato");
        }
        User entity = userRepository.findById(id).get();
        BeanUtils.copyProperties(userDTORequest, entity);
        entity.setUserName(userDTORequest.getUsername()); // Assicurati di mappare userName
        userRepository.save(entity);
        UserDTOResponse userDTOResponse = new UserDTOResponse();
        BeanUtils.copyProperties(entity, userDTOResponse);
        return userDTOResponse;
    }

    // DELETE
    public String delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User non trovato");
        }
        userRepository.deleteById(id);
        return "User eliminato";
    }

    // GET ALL
    public List<UserResponsePrj> findAll() {
        return userRepository.findAllBy();
    }
}