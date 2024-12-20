package br.com.reboucas.nathalia.aws_project01.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/dog/{name}")
    public ResponseEntity<?> dogTest(@PathVariable String name) {
        LOG.info("Test Controller - name {}", name);
        return ResponseEntity.ok("Name: " + name);
    }

    @GetMapping()
    public ResponseEntity<?> myTest() {
        LOG.info("Chamando endpoint Test");
        return ResponseEntity.ok("Endpoint teste ok");
    }
}
