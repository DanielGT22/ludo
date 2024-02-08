package danielGrujic.ludo.payloads.errors;

import java.util.Date;

public record ErrorsDTO(
        String message,
        Date timestamp) {
    public ErrorsDTO(String message, Date timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}