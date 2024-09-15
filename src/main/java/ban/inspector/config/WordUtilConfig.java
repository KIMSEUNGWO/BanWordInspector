package ban.inspector.config;

import ban.inspector.utils.AhoCorasickWordUtil;
import ban.inspector.utils.WordUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WordUtilConfig {

    @Bean("ban")
    WordUtil banWordUtil() {
        return new AhoCorasickWordUtil();
    }
    @Bean("except")
    WordUtil exceptWordUtil() {
        return new AhoCorasickWordUtil();
    }
}
