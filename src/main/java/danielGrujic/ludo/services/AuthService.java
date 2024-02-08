package danielGrujic.ludo.services;


import danielGrujic.ludo.entities.Rank;
import danielGrujic.ludo.entities.User;
import danielGrujic.ludo.exceptions.EmailAlreadyInDbException;
import danielGrujic.ludo.exceptions.UnauthorizedException;
import danielGrujic.ludo.payloads.AuthPayloads.AuthRequestDTO;
import danielGrujic.ludo.payloads.AuthPayloads.TokenResponseDTO;
import danielGrujic.ludo.payloads.AuthPayloads.UtenteUpdateRequestDto;
import danielGrujic.ludo.payloads.UtentePayloads.UtenteRequestDto;
import danielGrujic.ludo.payloads.UtentePayloads.UtenteRespondDto;
import danielGrujic.ludo.repositories.UserRepository;
import danielGrujic.ludo.security.JWTTtools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTTtools jwtTtools;

    public TokenResponseDTO authenticateUser(AuthRequestDTO body){
        User user=  userService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), user.getPassword())) {
            return new TokenResponseDTO(jwtTtools.createToken(user));
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }

    public UtenteRespondDto save(UtenteRequestDto body){
        User user    = new User();
        Optional<User> checkEmail= userRepository.findByEmail(body.email());
        if(checkEmail.isEmpty()){
            user.setUsername(body.username());
            user.setEmail(body.email());
            user.setPassword(bcrypt.encode(body.password()));
            user.setRank(Rank.ADMIN);
            userRepository.save(user);
            return new UtenteRespondDto(user.getUuid(),user.getUsername(), user.getEmail());
        }else{
            throw new EmailAlreadyInDbException(body.email());
        }
    }

    public UtenteRespondDto put(UtenteUpdateRequestDto body, UUID uuid) {
        User user = userService.findByUUID(uuid);
        if (body.username() != null) user.setUsername(body.username());

        userRepository.save(user);

        return new UtenteRespondDto(user.getUuid(), user.getUsername(), user.getEmail());
    }
}
