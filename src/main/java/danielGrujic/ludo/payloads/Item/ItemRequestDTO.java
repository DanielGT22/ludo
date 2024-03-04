package danielGrujic.ludo.payloads.Item;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ItemRequestDTO(
        @Size(min=3,message="")
        @NotNull
        String name,
        @Size(min=3,message="")
        @NotNull
        double price,
        @Size(min=3,message="")
        @NotNull
        String img
) {


}
