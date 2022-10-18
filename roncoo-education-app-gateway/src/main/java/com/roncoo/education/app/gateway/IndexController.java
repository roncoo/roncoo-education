package com.roncoo.education.app.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author fengyw
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public Mono<String> index() {
        String html = "<center>Gateway Run Success</center>";
        return Mono.just(html);
    }
}
