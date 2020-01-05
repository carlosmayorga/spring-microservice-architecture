package com.cmayorga.spring.test.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class Post_ElapsedTime extends ZuulFilter{

    private static Logger log = LoggerFactory.getLogger(Post_ElapsedTime.class);
    
    @Override
    public String filterType() {
        return "post";
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
        
        log.info("Post filter activado");
        
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        
        Long startTime = (Long) request.getAttribute("startTime");
        Long finalTime = System.currentTimeMillis();
        Long elapsedTime = startTime - finalTime;
        
        log.info(String.format("Tiempo del request-response %s seg.", elapsedTime.doubleValue()/1000.00));
        
        return null;
    }

}
