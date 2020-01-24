package com.cmayorga.spring.test.oauth.service;

import com.cmayorga.spring.test.common.model.entity.User;

public interface IUserLoginService {
    
    public User findByUsername(String name);

}
