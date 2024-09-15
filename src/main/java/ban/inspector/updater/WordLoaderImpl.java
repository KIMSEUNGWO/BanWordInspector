package ban.inspector.updater;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class WordLoaderImpl implements WordLoader {

    private final ObjectMapper mapper = new ObjectMapper();
    private final Log logger = LogFactory.getLog(WordLoaderImpl.class);

    @Override
    public List<String> readBanWords() {
        return read("static/BanWords.json");
    }

    @Override
    public List<String> readExceptWords() {
        return read("static/ExceptWords.json");
    }

    private List<String> read(String path) {
        try {
            return mapper.readValue(new ClassPathResource(path).getInputStream(), new TypeReference<>() {});
        } catch (IOException e) {
            logger.error(e);
            return new ArrayList<>();
        }
    }

}
