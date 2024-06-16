package ban.inspector.config;

import ban.inspector.domain.BanWord;
import ban.inspector.domain.ExceptWord;
import ban.inspector.repository.JpaBanWordRepository;
import ban.inspector.repository.JpaExceptWordRepository;
import ban.inspector.utils.BanWordUtil;
import ban.inspector.utils.ExceptWordUtil;
import ban.inspector.utils.WordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class Config implements InspectConfig {

    private final JpaBanWordRepository jpaBanWordRepository;
    private final JpaExceptWordRepository jpaExceptWordRepository;

    @Override
    public List<String> initBanWords() {
        return jpaBanWordRepository.findAll().stream().map(BanWord::getWord).toList();
    }

    @Override
    public List<String> initExceptWords() {
        return jpaExceptWordRepository.findAll().stream().map(ExceptWord::getWord).toList();
    }

    @Bean
    WordUtil banWordUtil() {
        return new BanWordUtil();
    }

    @Bean
    ExceptWordUtil exceptWordUtil() {
        return new ExceptWordUtil();
    }
}
