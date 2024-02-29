package danielGrujic.ludo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Table(name = "Users")
@Entity
@JsonIgnoreProperties({"password", "authorities", "accountNonExpired",
        "enabled", "accountNonLocked", "credentialsNonExpired"})
public class User  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid ;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rank rank;

    private int vittorieQuizz;
    private int vittorieAssosijacije;
    private int partiteFatteQuizz;
    private int partiteFatteAssosijacije;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<User> friendsList = new ArrayList<>();

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setVittorieQuizz(int vittorieQuizz) {
        this.vittorieQuizz = vittorieQuizz;
    }

    public void setVittorieAssosijacije(int vittorieAssosijacije) {
        this.vittorieAssosijacije = vittorieAssosijacije;
    }

    public void setPartiteFatteQuizz(int partiteFatteQuizz) {
        this.partiteFatteQuizz = partiteFatteQuizz;
    }

    public void setPartiteFatteAssosijacije(int partiteFatteAssosijacije) {
        this.partiteFatteAssosijacije = partiteFatteAssosijacije;
    }

    public void setFriendsList(List<User> friendsList) {
        this.friendsList = friendsList;
    }


    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rank=" + rank +
                ", vittorieQuizz=" + vittorieQuizz +
                ", vittorieAssosijacije=" + vittorieAssosijacije +
                ", partiteFatteQuizz=" + partiteFatteQuizz +
                ", partiteFatteAssosijacije=" + partiteFatteAssosijacije +
                ", friendsList=" + friendsList +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(String.valueOf(this.rank)));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

