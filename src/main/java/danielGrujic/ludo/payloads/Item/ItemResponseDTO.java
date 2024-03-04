package danielGrujic.ludo.payloads.Item;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ItemResponseDTO(

        String name,
        double price,
        String img
) {
}
