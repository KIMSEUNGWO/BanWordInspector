package ban.inspector.repository;

import ban.inspector.domain.ExceptWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaExceptWordRepository extends JpaRepository<ExceptWord, Long> {
}
