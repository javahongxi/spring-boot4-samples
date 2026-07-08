package org.hongxi.boot4.webmvc;

import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SampleController {

    private final RestTemplate restTemplate;

    public SampleController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello " + name;
    }

    @GetMapping("/rest")
    public String hi(String name) {
        return restTemplate.getForObject("http://localhost:8080/hello/{name}", String.class, name);
    }
}
