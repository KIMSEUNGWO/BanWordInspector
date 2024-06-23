package ban.inspector.controller;

import ban.inspector.dto.BanWordListResponse;
import ban.inspector.dto.Response;
import ban.inspector.dto.Word;
import ban.inspector.inspector.Inspector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static ban.inspector.config.Constant.SUCCESS;

@RestController
public class BanWordController {

    private final Inspector inspector;

    @Autowired
    public BanWordController(Inspector inspector) {
        this.inspector = inspector;
    }

    @GetMapping("/valid")
    public ResponseEntity<Response> valid(@RequestParam(name = "word") String word) {
        List<String> result = inspector.inspect(word).stream().map(Word::getWord).toList();
        Response response = (result.isEmpty()) ? new Response(SUCCESS) : new BanWordListResponse(result);
        return ResponseEntity.ok(response);
    }

}
