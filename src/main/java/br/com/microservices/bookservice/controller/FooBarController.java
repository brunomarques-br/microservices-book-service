package br.com.microservices.bookservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/book-service")
public class FooBarController {

    private final Logger logger = LoggerFactory.getLogger(FooBarController.class);

    @GetMapping("/foo-bar")
    //@Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
    // fallback method is called when the circuit is open
    //@CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
    // RateLimiter Defined in application.yml to limit the number of requests 2 per 10 seconds
    //@RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public String hello() {
        logger.info("Request received in FooBarController");
//        var response = new RestTemplate()
//                .getForEntity("http://localhost:8080/foo-bar", String.class);
//        return response.getBody();
        return "Hello from book-service";
    }

    public String fallbackMethod(Exception e) {
        return "Fallback method called due to exception";
    }

}
