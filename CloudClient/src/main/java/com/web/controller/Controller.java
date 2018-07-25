package com.web.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * 熔断器的使用如下。对于统一的处理来说不太方便
 */
@RestController
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @RequestMapping("/hello")
    @HystrixCommand(fallbackMethod = "executeError")
    public String sayhello() {
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("EUREKACLIENT", false);
        System.out.println("服务地址" + instanceInfo.getHomePageUrl());
        List<ServiceInstance> instances = discoveryClient.getInstances("EUREKACLIENT");
        return restTemplate.getForEntity("http://root:root@EurekaClient/hello", String.class).getBody();
    }

    private String executeError() {
        return "启动熔断器了";
    }


    @RequestMapping("/ribbon")
    public String changeURI() {
        ServiceInstance instance = loadBalancerClient.choose("stores");
        URI storeURi = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));
        System.out.println(storeURi);
        return "static";
    }

    @RequestMapping("/myuser/u1")
    public String myUser(){
        return "zuul";
    }
}
