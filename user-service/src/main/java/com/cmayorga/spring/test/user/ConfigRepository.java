package com.cmayorga.spring.test.user;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.cmayorga.spring.test.common.model.entity.User;
import com.cmayorga.spring.test.common.model.entity.Role;;


@Configuration
public class ConfigRepository implements RepositoryRestConfigurer{

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(User.class, Role.class);
    }
    
    
    
}
