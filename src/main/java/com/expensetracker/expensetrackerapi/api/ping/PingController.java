package com.expensetracker.expensetrackerapi.api.ping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/api/ping")
    String getPing() {
        return "pong";
    }
}
