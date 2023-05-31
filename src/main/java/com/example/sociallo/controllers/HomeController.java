package com.example.sociallo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<String> homeWelcome(){
        return ResponseEntity.ok("<h1>Hello, Welcome to our home page</h1>");
    }
}
