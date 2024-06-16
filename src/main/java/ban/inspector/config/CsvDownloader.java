package ban.inspector.config;

import ban.inspector.domain.BanWord;
import ban.inspector.repository.JpaBanWordRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CsvDownloader {

    private final JpaBanWordRepository jpaWordRepository;

//    @PostConstruct
    public void init() {
        String path = "/Users/tmd8635/Downloads/slang.csv";
        List<BanWord> banWords = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] list;
            while ((list = reader.readNext()) != null) {
                if (list[0].matches("[가-힣]+")) {
                    BanWord data = new BanWord(list[0]);
                    banWords.add(data);
                }
            }

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        jpaWordRepository.saveAll(banWords);
    }
}
