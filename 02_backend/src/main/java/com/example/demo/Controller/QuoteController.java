package com.example.demo.Controller;

import com.example.demo.Service.QuoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {
    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/getQuote")
    public String getQuote(){
        return "Some Quote...";
    }

    @PostMapping("/setQuote")
    public String setQuote(){
        return "registration finished";
    }
}
