package com.klaos.TestingSecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String getHello(HttpServletRequest http){
        return "Welcome to Klaos " + http.getSession().getId();
    }
}
