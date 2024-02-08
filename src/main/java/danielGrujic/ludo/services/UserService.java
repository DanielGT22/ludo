package danielGrujic.ludo.services;

import danielGrujic.ludo.entities.User;
import danielGrujic.ludo.exceptions.NotFoundException;
import danielGrujic.ludo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository;




    public Page<User> getAll(int page, int size, String orderBy) {
        if(size>=20) size=20;
        Pageable pageable= PageRequest.of(page,size, Sort.by(orderBy));
        return userRepository.findAll(pageable);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(username));
    }

    public User findByUUID(UUID uuid) {
        return userRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
    }

    public void delete (UUID uuid){
        userRepository.delete(this.findByUUID(uuid));
    }
}