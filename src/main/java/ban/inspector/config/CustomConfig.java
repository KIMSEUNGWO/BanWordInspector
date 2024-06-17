package ban.inspector.config;

import ban.inspector.customConfig.BanWordFactory;
import ban.inspector.customConfig.ExceptWordFactory;
import ban.inspector.customConfig.InspectConfig;
import ban.inspector.domain.BanWord;
import ban.inspector.domain.ExceptWord;
import ban.inspector.repository.JpaBanWordRepository;
import ban.inspector.repository.JpaExceptWordRepository;
import ban.inspector.utils.BanWordUtil;
import ban.inspector.utils.BanWordUtilImpl;
import ban.inspector.utils.ExceptWordUtil;
import ban.inspector.utils.ExceptWordUtilImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Override
    public void addBanWordInspector(BanWordFactory factory) {
        factory
            .add(banWordUtil())
            .initWords(() -> jpaBanWordRepository.findAll().stream().map(BanWord::getWord).toList())
            .build();

    }

    @Override
    public void addExceptWordInspector(ExceptWordFactory factory) {
        factory
            .add(exceptWordUtil())
            .initWords(() -> jpaExceptWordRepository.findAll().stream().map(ExceptWord::getWord).toList())
            .build();
    }
}
