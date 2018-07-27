package com.hystrix;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * serviceID
 */
@FeignClient(value = "EUREKACLIENT", fallback = HystrixClientFallback.class)
public interface SchedualService {

    @GetMapping("/hello")
    String feignHystrix();


}
