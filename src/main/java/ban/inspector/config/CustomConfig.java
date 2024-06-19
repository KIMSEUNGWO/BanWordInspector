package ban.inspector.config;

import ban.inspector.customConfig.BanWordFactoryImpl;
import ban.inspector.customConfig.ExceptWordFactoryImpl;
import ban.inspector.customConfig.WordFactory;
import ban.inspector.customConfig.InspectConfig;
import ban.inspector.domain.BanWord;
import ban.inspector.domain.ExceptWord;
import ban.inspector.repository.JpaBanWordRepository;
import ban.inspector.repository.JpaExceptWordRepository;
import ban.inspector.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CustomConfig implements InspectConfig {

    private final JpaBanWordRepository jpaBanWordRepository;
    private final JpaExceptWordRepository jpaExceptWordRepository;

    @Bean
    BanWordUtil banWordUtil() {
        return new BanWordUtilImpl();
    }

    @Bean
    ExceptWordUtil exceptWordUtil() {
        return new ExceptWordUtilImpl();
    }

    @Bean
    WordFactory banWordFactory() {
        return new BanWordFactoryImpl(banWordUtil());
    }

    @Bean
    WordFactory exceptWordFactory() {
        return new ExceptWordFactoryImpl(exceptWordUtil());
    }

    @Override
    public void addBanWords(WordFactory factory) {
        List<String> words = jpaBanWordRepository.findAll().stream().map(BanWord::getWord).toList();
        factory.add(words);
    }

    @Override
    public void addExceptWords(WordFactory factory) {
        List<String> words = jpaExceptWordRepository.findAll().stream().map(ExceptWord::getWord).toList();
        factory.add(words);
    }
}
