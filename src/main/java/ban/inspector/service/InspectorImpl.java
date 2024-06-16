package ban.inspector.service;

import ban.inspector.dto.BanWordListResponse;
import ban.inspector.dto.Response;
import ban.inspector.dto.WordDto;
import ban.inspector.inspector.BanWordInspector;
import ban.inspector.inspector.ExceptWordInspector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static ban.inspector.config.Constant.SUCCESS;

@Service
@RequiredArgsConstructor
public class InspectorImpl implements Inspector {

    private final BanWordInspector banInspector;
    private final ExceptWordInspector exceptInspector;

    @Override
    public Response valid(String word) {
        String newWord = removeNotKorean(word);
        List<WordDto> badWords = banInspector.inspect(newWord);
        List<String> badWords2 = exceptInspector.inspect(newWord, badWords);
        return (badWords2.isEmpty()) ? new Response(SUCCESS) : new BanWordListResponse(badWords2);
    }

    private String removeNotKorean(String word) {
        return word.replaceAll("[^가-힣]", "");
    }
}