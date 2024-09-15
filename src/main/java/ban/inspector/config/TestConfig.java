package ban.inspector.config;

import ban.inspector.factory.WordFactory;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TestConfig implements InspectConfig {

    @Override
    public void addBanWords(WordFactory factory) {
        factory.add(List.of(
            "감자", "고구마"
        ));
    }

    @Override
    public void addExceptWords(WordFactory factory) {
        InspectConfig.super.addExceptWords(factory);
    }
}
