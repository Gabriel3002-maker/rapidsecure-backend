package com.rapidsecure.rapidsecure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Sample API", description = "Sample operations for demonstration")
public class SampleController {

    @GetMapping("/hello")
    @Operation(summary = "Say Hello", description = "Returns a greeting message")
    public String sayHello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }

    @GetMapping("/hello/{name}")
    @Operation(summary = "Say Hello with Path Variable", description = "Returns a greeting message with path variable")
    public String sayHelloPath(@PathVariable String name) {
        return String.format("Hello, %s!", name);
    }
}