package com.hystrix;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "feign", fallback = HystrixClientFallback.class)
public interface SchedualService {

    @GetMapping("/feign")
    String feignHystrix();


}
