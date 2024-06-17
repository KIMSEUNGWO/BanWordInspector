package ban.inspector;

import ban.inspector.config.InspectConfig;
import ban.inspector.domain.BanWord;
import ban.inspector.domain.ExceptWord;
import ban.inspector.inspector.BanWordInspector;
import ban.inspector.repository.JpaBanWordRepository;
import ban.inspector.repository.JpaExceptWordRepository;
import ban.inspector.utils.BanWordUtil;
import ban.inspector.utils.BanWordUtilImpl;
import ban.inspector.utils.ExceptWordUtil;
import ban.inspector.utils.ExceptWordUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@TestConfiguration
public class TestConfig implements InspectConfig {

    private JpaBanWordRepository jpaBanWordRepository;
    private JpaExceptWordRepository jpaExceptWordRepository;

    @Autowired
    public TestConfig(JpaBanWordRepository jpaBanWordRepository, JpaExceptWordRepository jpaExceptWordRepository) {
        this.jpaBanWordRepository = jpaBanWordRepository;
        this.jpaExceptWordRepository = jpaExceptWordRepository;
    }

    @Override
    public List<String> initBanWords() {
        System.out.println("initBanWords 실행000000");
        List<String> list = jpaBanWordRepository.findAll().stream().map(BanWord::getWord).toList();
        System.out.println("list = " + list);
        return list;
    }

    @Override
    public List<String> initExceptWords() {
        System.out.println("initExceptWords 실행0000000");
        return jpaExceptWordRepository.findAll().stream().map(ExceptWord::getWord).toList();
    }

    @Bean
    BanWordInspector banWordInspector() {
        System.out.println("TestConfig.banWordInspector");
        return new BanWordInspector(this, banWordUtil());
    }

    @Bean
    BanWordUtil banWordUtil() {
        return new BanWordUtilImpl();
    }

    @Bean
    ExceptWordUtil exceptWordUtil() {
        return new ExceptWordUtilImpl();
    }


}
