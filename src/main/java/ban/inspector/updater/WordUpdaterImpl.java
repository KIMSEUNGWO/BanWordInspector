package ban.inspector.updater;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class WordUpdaterImpl implements WordUpdater {

    private static final Log logger = LogFactory.getLog(WordUpdater.class);

    @Override
    public void update() {
        logger.info("Checking for Word Updates");
    }

    @Override
    public List<String> getDefaultBanWords() {
        try {
            return new ObjectMapper().readValue(new File("src/main/resources/static/BanWords.json"), new TypeReference<>() {});
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<String> getDefaultExceptWords() {
        try {
            return new ObjectMapper().readValue(new File("src/main/resources/static/ExceptWords.json"), new TypeReference<>() {});
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public record UpdateInfo(String version, List<String> words) { }
}
