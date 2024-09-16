package ban.inspector.updater;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static ban.inspector.updater.WordPathConstant.*;

@Component
public class WordUpdaterImpl implements WordUpdater {

    private final Log log = LogFactory.getLog(WordUpdaterImpl.class);
    private final ObjectMapper mapper;

    @Autowired
    public WordUpdaterImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void update() {
        log.info("Checking for Word Updates");

        Optional<WordJsonData> localBan = read(BAN_WORD_PATH);
        Optional<WordJsonData> remoteBan = readURL(REMOTE_BAN_WORD_PATH);

        Optional<WordJsonData> localExcept = read(EXCEPT_WORD_PATH);
        Optional<WordJsonData> remoteExcept = readURL(REMOTE_EXCEPT_WORD_PATH);

        if (remoteBan.isPresent()) {
            execute("Ban Word", BAN_WORD_PATH, localBan, remoteBan);
        }

        if (remoteExcept.isPresent()) {
            execute("Except Word", EXCEPT_WORD_PATH, localExcept, remoteExcept);
        }
    }

    private void execute(String message, String path, Optional<WordJsonData> local, Optional<WordJsonData> remote) {

        if (local.isEmpty() || !local.get().getVersion().equals(remote.get().getVersion())) {
            log.info("Updating " + message + " from " + local.orElse(new WordJsonData("Version Not Found")).getVersion() + " to " + remote.get().getVersion());

            try {
                mapper.writeValue(new File(path), remote.get());
                log.info("Successfully updated " + message);
            } catch (IOException e) {
                log.error("Failed to update words", e);
            }
        }
    }


    private Optional<WordJsonData> read(String path) {
        try {
            return Optional.of(mapper.readValue(new File(path), WordJsonData.class));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private Optional<WordJsonData> readURL(String path) {
        try {
            // URL에서 InputStream을 가져옴
            URL url = new URL(path);
            try (InputStream inputStream = url.openStream()) {
                // ObjectMapper로 JSON 데이터를 Java 객체로 변환
                ObjectMapper objectMapper = new ObjectMapper();
                WordJsonData data = objectMapper.readValue(inputStream, WordJsonData.class);
                return Optional.of(data);
            }
        } catch (IOException e) {
            log.error("Failed to read from URL", e);
            return Optional.empty();
        }
    }
}
