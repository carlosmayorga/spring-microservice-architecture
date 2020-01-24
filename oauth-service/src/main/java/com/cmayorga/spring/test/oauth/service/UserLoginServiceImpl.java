package com.cmayorga.spring.test.oauth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cmayorga.spring.test.common.model.entity.User;
import com.cmayorga.spring.test.oauth.rest_client.IUserRestClient;

@Service
public class UserLoginServiceImpl implements UserDetailsService, IUserLoginService {

    private Logger log = LoggerFactory.getLogger(UserLoginServiceImpl.class);
    
    @Autowired
    private IUserRestClient iUserRestClient;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserRestClient.findByUsername(username);
        
        if(user == null) {
            log.error("Ups");
            throw new UsernameNotFoundException("Error, el usuario '"+username+"' no existe");
        }
        
        List<GrantedAuthority> authorities = user.getRole()
                                                .stream()
                                                .map(role -> {
                                                    log.info("--> "+ role.getName());
                                                    return new SimpleGrantedAuthority(role.getName());
                                                })
                                                .peek(authority -> {
                                                    log.info("Role: " +authority.getAuthority());
                                                })
                                                .collect(Collectors.toList());
        
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                true, true, true, true, authorities);
                
    }

    @Override
    public User findByUsername(String username) {
        return iUserRestClient.findByUsername(username);
    }

}
