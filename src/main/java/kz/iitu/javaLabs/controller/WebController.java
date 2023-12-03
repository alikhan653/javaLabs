package kz.iitu.javaLabs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/location")
    public String getLocationPage() {
        return "test"; // Return the name of your HTML file without the extension
    }
}