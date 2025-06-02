package zeyracakes.co.tz.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/health")
@RestController
@CrossOrigin(origins = "*")
public class HealthCheck {

    @GetMapping
    public ResponseEntity<String> healthCheck()
    {
        return ResponseEntity.ok("Hey Buddy, I am alive...");
    }
}
