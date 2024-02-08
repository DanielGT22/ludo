package danielGrujic.ludo.payloads.AuthPayloads;

public record AuthRequestDTO(
        String email,
        String password
) {
}
