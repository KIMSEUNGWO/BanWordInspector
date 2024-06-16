package ban.inspector.service;

import ban.inspector.dto.BanWordListResponse;
import ban.inspector.dto.Response;
import ban.inspector.dto.WordDto;
import ban.inspector.inspector.BanWordInspector;
import ban.inspector.inspector.ExceptWordInspector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BanWordServiceImpl implements BanWordService {

    private final BanWordInspector banInspector;
    private final ExceptWordInspector exceptInspector;

    @Override
    public Optional<Response> valid(String word) {
        String newWord = removeNotHangul(word);
        List<WordDto> badWords = banInspector.inspect(newWord);
        System.out.println("badWords = " + badWords);
        List<String> badWords2 = exceptInspector.inspect(newWord, badWords);
        System.out.println("badWords2 = " + badWords2);
        return (!badWords2.isEmpty()) ?
            Optional.of(new BanWordListResponse(badWords2)) :
            Optional.empty();
    }

    private String removeNotHangul(String word) {
        return word.replaceAll("[^가-힣]", "");
    }
}
