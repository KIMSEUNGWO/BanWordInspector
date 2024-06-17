package ban.inspector.controller;

import ban.inspector.dto.BanWordListResponse;
import ban.inspector.dto.Response;
import ban.inspector.service.Inspector;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static ban.inspector.config.Constant.SUCCESS;

@RestController
@RequiredArgsConstructor
public class BanWordController {

    private final Inspector inspector;

    @GetMapping("/valid")
    public ResponseEntity<Response> valid(@RequestParam(name = "word") String word) {
        List<String> result = inspector.valid(word);
        Response response = (result.isEmpty()) ? new Response(SUCCESS) : new BanWordListResponse(result);
        return ResponseEntity.ok(response);
    }

}
