package ban.inspector.controller;

import ban.inspector.dto.Response;
import ban.inspector.service.Inspector;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BanWordController {

    private final Inspector inspector;

    @GetMapping("/valid")
    public ResponseEntity<Response> valid(@RequestParam(name = "word") String word) {
        return ResponseEntity.ok(inspector.valid(word));
    }

}
