package com.sk.skala.walktogether;  // 패키지는 실제 경로에 맞춰 변경

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String root() {
        return "project is ready";
    }
}