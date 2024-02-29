package danielGrujic.ludo.payloads.UtentePayloads;


import danielGrujic.ludo.entities.Rank;
import danielGrujic.ludo.entities.User;

import java.util.List;
import java.util.UUID;

public record UtenteRespondDto(
        UUID uuid,
        String username,
        String email,
        int vittoriesQuizz,
        int vittoriesAsosijacije,
        int partiteFatteQuizz,
        int partiteFatteAsosijacije,

        List<UUID> friendsList

) {
}
