package danielGrujic.ludo.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Table(name = "quizz")
@Entity
public class Quizz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid ;

    private String question;
    private String answer;

    private String decoy1;
    private String decoy2;
    private String decoy3;


    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }



    public void setDecoy1(String decoy1) {
        this.decoy1 = decoy1;
    }

    public void setDecoy2(String decoy2) {
        this.decoy2 = decoy2;
    }

    public void setDecoy3(String decoy3) {
        this.decoy3 = decoy3;
    }

    @Override
    public String toString() {
        return "Quizz{" +
                "uuid=" + uuid +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", decoy1='" + decoy1 + '\'' +
                ", decoy2='" + decoy2 + '\'' +
                ", decoy3='" + decoy3 + '\'' +
                '}';
    }
}
