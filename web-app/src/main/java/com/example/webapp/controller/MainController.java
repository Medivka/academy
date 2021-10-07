package com.example.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Sacuta V.A.
 */

@Controller
public class MainController {
    @GetMapping("/")
    public String index() {
        return "main";
    }

    @GetMapping("/reader")
    public String reader() {
        return "reader";
    }

    @GetMapping("/editor")
    public String editor() {
        return "editor";
    }

    @GetMapping("/author")
    public String author() {
        return "author";
    }


}
