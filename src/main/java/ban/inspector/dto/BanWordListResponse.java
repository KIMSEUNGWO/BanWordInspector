package ban.inspector.dto;

import java.util.Collection;

import static ban.inspector.config.Constant.*;

public class BanWordListResponse extends Response  {

    private final Collection<Word> words;

    public BanWordListResponse(Collection<Word> words) {
        super(FAIL);
        this.words = words;
    }

    public Collection<Word> getWords() {
        return words;
    }
}
