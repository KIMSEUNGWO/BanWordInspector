package ban.inspector.repository;

import ban.inspector.domain.BanWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBanWordRepository extends JpaRepository<BanWord, Long> {
}
