package backlog.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRestController {

    @GetMapping("/")
    public String index() {
        return "Tesing one two three";
    }
    
}