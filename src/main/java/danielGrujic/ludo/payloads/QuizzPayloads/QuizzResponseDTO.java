package danielGrujic.ludo.payloads.QuizzPayloads;

import java.util.UUID;

public record QuizzResponseDTO(
        UUID uuid,
        String question,
        String answer,
        String decoy1,
        String decoy2,
        String decoy3
) {

}









