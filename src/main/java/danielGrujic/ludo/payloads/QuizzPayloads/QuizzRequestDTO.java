package danielGrujic.ludo.payloads.QuizzPayloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record QuizzRequestDTO(
        @Size(min=3,message="Question is too short")
        @NotNull(message="Cant have a quizz without a question")
        String question,

        @Size(min=1,message="Too short")
        @NotNull(message="Cant have a quizz without a answer")
        String answer,
        @Size(min=1,message="Too short")
        @NotNull(message="Cant have a quizz without a fake answer")
        String decoy1,
        @Size(min=1,message="Too short")
        @NotNull(message="Cant have a quizz without a fake answer")
        String decoy2,
        @Size(min=1,message="Too short")
        @NotNull(message="Cant have a quizz without a fake answer")
        String decoy3


) {

}
