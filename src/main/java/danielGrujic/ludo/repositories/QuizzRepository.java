package danielGrujic.ludo.repositories;

import danielGrujic.ludo.entities.Quizz;
import danielGrujic.ludo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuizzRepository extends JpaRepository<Quizz, UUID> {
        Optional<Quizz> findByQuestion(String question);
        Optional<Quizz> findByUuid(UUID uuid);
}
