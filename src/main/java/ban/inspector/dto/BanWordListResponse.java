package ban.inspector.dto;

import java.util.Collection;

import static ban.inspector.config.Constant.*;

public class BanWordListResponse extends Response  {

    private final Collection<String> words;

    public BanWordListResponse(Collection<String> words) {
        super(FAIL);
        this.words = words;
    }

    public Collection<String> getWords() {
        return words;
    }
}
