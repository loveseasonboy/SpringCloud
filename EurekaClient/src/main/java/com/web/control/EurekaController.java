package com.web.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaController {

    @RequestMapping("/hello")
    public String home(){
        return "Hello World";
    }
}
