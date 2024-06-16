package ban.inspector.service;

import ban.inspector.dto.Response;

import java.util.Optional;
public interface Inspector {

    Response valid(String word);
}
