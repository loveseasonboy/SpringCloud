package com.hystrix;

import org.springframework.stereotype.Component;

@Component
public class HystrixClientFallback implements SchedualService {
    @Override
    public String feignHystrix() {
        return "fallback";
    }
}
