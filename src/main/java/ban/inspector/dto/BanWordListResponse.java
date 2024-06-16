package ban.inspector.dto;

import lombok.Getter;

import java.util.Collection;

import static ban.inspector.config.Constant.*;

@Getter
public class BanWordListResponse extends Response  {

    private final Collection<String> words;

    public BanWordListResponse(Collection<String> words) {
        super(FAIL);
        this.words = words;
    }
}
