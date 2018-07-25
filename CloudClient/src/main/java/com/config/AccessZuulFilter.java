package com.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * zuul 过滤器
 */
@Configuration
public class AccessZuulFilter extends ZuulFilter {

    @Bean("accessFilter")
    public ZuulFilter accessZuulFilter() {
        return new AccessZuulFilter();
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 只有带有token的参数可以通过
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object object = request.getParameter("token");
        if (object == null) {
            ctx.setResponseStatusCode(401);
            ctx.set("sendZuulResponse", false);//不启用路由
            //ctx.sendZuulResponse();//启动路由调用其他的serviceId 中的数据
            try {
                ctx.getResponse().getWriter().write("nothing to doing");
            } catch (IOException e) {
                e.printStackTrace();
                throw new ZuulException(e.getMessage(), 401, "IO exception");
            }
            return null;
        }
        return null;
    }
}
