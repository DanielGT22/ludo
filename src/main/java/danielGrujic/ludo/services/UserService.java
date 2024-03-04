package danielGrujic.ludo.services;

import danielGrujic.ludo.entities.User;
import danielGrujic.ludo.exceptions.NotFoundException;
import danielGrujic.ludo.payloads.UtentePayloads.UtenteRespondDto;
import danielGrujic.ludo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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


    private UtenteRespondDto convertToUtenteRespondDto(User user) {
        List<UUID> friendsUuids = user.getFriendsList().stream()
                .map(User::getUuid)
                .collect(Collectors.toList());

        return new UtenteRespondDto(
                user.getUuid(),
                user.getUsername(),
                user.getEmail(),
                user.getVittorieQuizz(),
                user.getVittorieAssosijacije(),
                user.getPartiteFatteQuizz(),
                user.getPartiteFatteAssosijacije(),
                friendsUuids // Set the friendsUuids field
        );
    }

    public void addFriend(UUID userUuid, UUID friendUuid) {
        User user = userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new NotFoundException("User not found with UUID: " + userUuid));

        User friend = userRepository.findByUuid(friendUuid)
                .orElseThrow(() -> new NotFoundException("Friend not found with UUID: " + friendUuid));

        List<User> friendsList = user.getFriendsList();
        friendsList.add(friend);
        user.setFriendsList(friendsList);

        userRepository.save(user);
    }
}
