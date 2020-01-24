package com.cmayorga.spring.test.oauth.rest_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmayorga.spring.test.common.model.entity.User;

@FeignClient("user-service")
public interface IUserRestClient {
    
    @GetMapping("/admin/search/findbyusername")
    public User findByUsername(@RequestParam String username);
    

}
