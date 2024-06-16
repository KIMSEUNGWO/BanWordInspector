package ban.inspector.service;

import ban.inspector.domain.BanWord;
import ban.inspector.domain.ExceptWord;
import ban.inspector.dto.Response;
import ban.inspector.repository.JpaBanWordRepository;
import ban.inspector.repository.JpaExceptWordRepository;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.RequiredTypes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BanWordServiceTest {


    @Autowired private BanWordService service;
    @Autowired private JpaBanWordRepository banWordRepository;
    @Autowired private JpaExceptWordRepository exceptWordRepository;


    @BeforeEach
    public void setUp() {
        List<BanWord> banWords = List.of(new BanWord("졸라"), new BanWord("애미"), new BanWord("어미"));
        List<ExceptWord> exceptWords = List.of(new ExceptWord("고르곤졸라"), new ExceptWord("어미새"));
        banWordRepository.saveAll(banWords);
        exceptWordRepository.saveAll(exceptWords);
    }

    @Test
    void name() {
        Optional<Response> result = service.valid("졸라배고프다");
        Assertions.assertThat(result).isNotEmpty();
    }
}