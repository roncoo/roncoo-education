package com.roncoo.education.app.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class IndexController {

    @GetMapping("/")
    public Mono<String> index() {
        return Mono.just("SUCCESS");
    }
}
