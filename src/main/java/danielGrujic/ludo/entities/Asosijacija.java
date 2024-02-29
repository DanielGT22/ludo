package danielGrujic.ludo.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Table(name = "asosijacije")
@Entity
public class Asosijacija {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid ;

    private String A1;
    private String A2;
    private String A3;
    private String A4;
    private String A5;
    private String A6;
    private String A7;
    private String A8;
    private String A9;
    private String A10;

    @Override
    public String toString() {
        return "Asosijacija{" +
                "uuid=" + uuid +
                ", A1='" + A1 + '\'' +
                ", A2='" + A2 + '\'' +
                ", A3='" + A3 + '\'' +
                ", A4='" + A4 + '\'' +
                ", A5='" + A5 + '\'' +
                ", A6='" + A6 + '\'' +
                ", A7='" + A7 + '\'' +
                ", A8='" + A8 + '\'' +
                ", A9='" + A9 + '\'' +
                ", A10='" + A10 + '\'' +
                '}';
    }

    public void setA1(String a1) {
        A1 = a1;
    }

    public void setA2(String a2) {
        A2 = a2;
    }

    public void setA3(String a3) {
        A3 = a3;
    }

    public void setA4(String a4) {
        A4 = a4;
    }

    public void setA5(String a5) {
        A5 = a5;
    }

    public void setA6(String a6) {
        A6 = a6;
    }

    public void setA7(String a7) {
        A7 = a7;
    }

    public void setA8(String a8) {
        A8 = a8;
    }

    public void setA9(String a9) {
        A9 = a9;
    }

    public void setA10(String a10) {
        A10 = a10;
    }
}
