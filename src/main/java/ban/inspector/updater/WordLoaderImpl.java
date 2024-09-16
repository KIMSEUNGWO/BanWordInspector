package ban.inspector.updater;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static ban.inspector.updater.WordPathConstant.*;

@Component
public class WordLoaderImpl implements WordLoader {

    private final Log logger = LogFactory.getLog(WordLoaderImpl.class);

    @Override
    public List<String> readBanWords() {
        return read(BAN_WORD_PATH);
    }

    @Override
    public List<String> readExceptWords() {
        return read(EXCEPT_WORD_PATH);
    }

    private List<String> read(String path) {
        try {
            return new ObjectMapper().readValue(new File(path), new TypeReference<>() {});
        } catch (IOException e) {
            logger.error(e);
            return Collections.emptyList();
        }
    }
}
