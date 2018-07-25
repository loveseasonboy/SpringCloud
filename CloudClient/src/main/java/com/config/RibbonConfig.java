package com.config;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.netflix.ribbon.ZonePreferenceServerListFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 负载均衡的配置-自定义
 */
@Configuration
public class RibbonConfig {

    /**
     * 默认的是轮询模式
     *
     * @return
     */
    @Bean
    public IRule ribbonRule() {
        //return new RandomRule();//随机获取
        return new BestAvailableRule();//并发量
    }

    /**
     * 定义服务的分区获取
     * @return
     */
    public ZonePreferenceServerListFilter serverListFilter() {
        ZonePreferenceServerListFilter filter = new ZonePreferenceServerListFilter();
        filter.setZone("zone_1");//eureka.instance.metadataMap.zone
        return filter;
    }
}
