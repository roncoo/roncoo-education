package com.roncoo.education.gateway;

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
        String html = "<center>Gateway Run Success</center><br/>";
        html = html + "<center>网关地址：http://localhost:8180</center>";
        return Mono.just(html);
    }
}
