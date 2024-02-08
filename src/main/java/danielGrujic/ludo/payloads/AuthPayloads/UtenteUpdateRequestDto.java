package danielGrujic.ludo.payloads.AuthPayloads;


import danielGrujic.ludo.entities.Rank;
import jakarta.validation.constraints.Size;

public record UtenteUpdateRequestDto(
        @Size(min = 3, message = "username troppo corto")
        String username
) {
}
