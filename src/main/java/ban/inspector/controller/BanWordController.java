package ban.inspector.controller;

import ban.inspector.dto.Response;
import ban.inspector.service.BanWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static ban.inspector.config.Constant.*;

@RestController
@RequiredArgsConstructor
public class BanWordController {

    private final BanWordService banWordService;

    @GetMapping("/valid")
    public ResponseEntity<Response> valid(@RequestParam(name = "word") String word) {
        Optional<Response> badWordList = banWordService.valid(word);
        return ResponseEntity.ok(badWordList.orElse(new Response(SUCCESS)));
    }

}
