package ban.inspector.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "BAN_WORD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BanWord {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    public BanWord(String word) {
        this.word = word;
    }
}
