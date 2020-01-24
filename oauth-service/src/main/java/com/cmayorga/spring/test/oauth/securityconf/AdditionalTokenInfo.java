package com.cmayorga.spring.test.oauth.securityconf;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.cmayorga.spring.test.common.model.entity.User;
import com.cmayorga.spring.test.oauth.service.IUserLoginService;

@Component
public class AdditionalTokenInfo implements TokenEnhancer{

    @Autowired
    private IUserLoginService iUserLoginService;
    
    /* Esta clase y metodo son unicamente para agregarle informacion extra al token jwt */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> tokenInfo = new HashMap<String, Object>();
        
        User user = iUserLoginService.findByUsername(authentication.getName());
        
        tokenInfo.put("username", user.getUsername());
        tokenInfo.put("lastname", user.getLastname());
        tokenInfo.put("email", user.getEmail());
        
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(tokenInfo);
        
        return accessToken;
    }

}
