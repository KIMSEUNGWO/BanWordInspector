package ban.inspector.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.Collection;

import static ban.inspector.config.Constant.*;

@Getter
@ToString
public class BanWordListResponse extends Response  {

    private final Collection<String> words;

    public BanWordListResponse(Collection<String> words) {
        super(FAIL);
        this.words = words;
    }
}
