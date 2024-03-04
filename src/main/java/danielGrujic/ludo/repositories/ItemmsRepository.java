package danielGrujic.ludo.repositories;

import danielGrujic.ludo.entities.Asosijacija;
import danielGrujic.ludo.entities.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ItemmsRepository  extends JpaRepository<Items, UUID> {
    Optional<Items> findByUuid(UUID uuid);
}
