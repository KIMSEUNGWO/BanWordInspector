package ban.inspector.updater;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static ban.inspector.updater.WordType.*;


@Component
public class WordUpdaterImpl implements WordUpdater {

    private final Log logger = LogFactory.getLog(WordUpdater.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<String> getDefaultBanWords() {
        try { return mapper.readValue(getClassPathResource(BAN.getLocalWords()).getInputStream(), new TypeReference<>() {});}
        catch (IOException e) {
            logger.error(e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<String> getDefaultExceptWords() {
        try { return mapper.readValue(getClassPathResource(EXCEPT.getLocalWords()).getInputStream(), new TypeReference<>() {});}
        catch (IOException e) {
            logger.error(e);
            return new ArrayList<>();
        }
    }

    private ClassPathResource getClassPathResource(String classPath) {
        return new ClassPathResource(classPath);
    }


    public static class UpdateInfo {
        private String version;

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
