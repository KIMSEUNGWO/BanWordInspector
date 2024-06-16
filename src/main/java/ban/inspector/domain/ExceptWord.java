package ban.inspector.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "EXCEPT_WORD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExceptWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    public ExceptWord(String word) {}

}
