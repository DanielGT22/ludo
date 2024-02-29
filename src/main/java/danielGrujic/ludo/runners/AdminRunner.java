package danielGrujic.ludo.runners;

import danielGrujic.ludo.entities.Rank;
import danielGrujic.ludo.entities.User;
import danielGrujic.ludo.repositories.UserRepository;
import danielGrujic.ludo.services.UserService;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AdminRunner implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcrypt;




    @Override
    public void run(String... args) throws Exception  {
        Optional<User> check = userRepository.findByEmail("utente1@gmail.com");
        if(check.isEmpty())
        {
            User user= new User();
            user.setUsername("utente1");
            user.setEmail("utente1@gmail.com");
            user.setPassword(bcrypt.encode("utente1"));
            user.setRank(Rank.ADMIN);
            userRepository.save(user);
            System.out.println("ok Utente creato");
        }else{
            System.out.println("Dio ha gia creato l'utente");
        }
    }
}
