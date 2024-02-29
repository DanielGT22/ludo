package danielGrujic.ludo.payloads.AsosijacijePayloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AsoRequestDTO(
        @Size(min=3,message="Question is too short")
        @NotNull
        String A1,
        @Size(min=1,message="Too short")
        @NotNull
        String A2,
        @Size(min=1,message="Too short")
        @NotNull
        String A3,
        @Size(min=1,message="Too short")
        @NotNull
        String A4,
        @Size(min=1,message="Too short")
        @NotNull
        String A5,
        @Size(min=1,message="Too short")
        @NotNull
        String A6,
        @Size(min=1,message="Too short")
        @NotNull
        String A7,
        @Size(min=1,message="Too short")
        @NotNull
        String A8,
        @Size(min=1,message="Too short")
        @NotNull
        String A9,
        @Size(min=1,message="Too short")
        @NotNull
        String A10
) {
}
