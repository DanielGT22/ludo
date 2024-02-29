package danielGrujic.ludo.repositories;

import danielGrujic.ludo.entities.Asosijacija;
import danielGrujic.ludo.entities.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AsosijacijeRepository extends JpaRepository<Asosijacija, UUID> {
    Optional<Asosijacija> findByUuid(UUID uuid);
}