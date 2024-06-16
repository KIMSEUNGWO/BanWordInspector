package ban.inspector.service;

import ban.inspector.dto.Response;

import java.util.Optional;
public interface BanWordService {

    Optional<Response> valid(String word);
}
