package com.web.controller;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@RestController
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private DiscoveryClient discoveryClient;


    @RequestMapping("/hello")
    public String sayhello() {
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("EUREKACLIENT", false);
        System.out.println("服务地址" + instanceInfo.getHomePageUrl());
        List<ServiceInstance> instances = discoveryClient.getInstances("EUREKACLIENT");
        return restTemplate.getForEntity("http://root:root@EurekaClient/hello", String.class).getBody();
    }

}
