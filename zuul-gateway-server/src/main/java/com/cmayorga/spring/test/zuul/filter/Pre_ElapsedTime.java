package com.cmayorga.spring.test.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class Pre_ElapsedTime extends ZuulFilter{

    private static Logger log = LoggerFactory.getLogger(Pre_ElapsedTime.class);
    
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }
    
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("Pre filter activado");
        
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        
        log.info(String.format("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString()));
        
        Long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return null;
    }

}
