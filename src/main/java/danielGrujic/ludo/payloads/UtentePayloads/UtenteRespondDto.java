package danielGrujic.ludo.payloads.UtentePayloads;


import danielGrujic.ludo.entities.Rank;

import java.util.UUID;

public record UtenteRespondDto(
        UUID uuid,
        String username,
        String email
) {
}
