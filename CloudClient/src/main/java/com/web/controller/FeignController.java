package com.web.controller;

import com.hystrix.SchedualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FeignController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    SchedualService schedualService;

    @RequestMapping("/feign")
    public String feignHystrix() {
        return schedualService.feignHystrix();//restTemplate.getForEntity("http://root:root@EurekaClient/hello", String.class).getBody();
    }

    @RequestMapping("/hello/h1")
    public String sayhelloTest() {
        return restTemplate.getForEntity("http://root:root@EurekaClient/hello", String.class).getBody();
    }
}
