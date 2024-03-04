package danielGrujic.ludo.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Table(name = "items")
@Entity
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid ;
    private String name;
    private double price;
    private String img;


    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Items{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", img='" + img + '\'' +
                '}';
    }
}
