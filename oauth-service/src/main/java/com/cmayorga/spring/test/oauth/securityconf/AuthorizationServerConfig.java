package com.cmayorga.spring.test.oauth.securityconf;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    
    @Autowired
    private Environment env;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private AdditionalTokenInfo additionalTokenInfo;
    
    /* Este es uno de los metodos mas importantes, ya que es el que genera el token,
     * cada vez que le enviamos un post al endpoint /oauth/token */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(additionalTokenInfo,accessTokenConverter()));
        
        endpoints.authenticationManager(authenticationManager)
                 .tokenStore(tokenStore())
                 .accessTokenConverter(accessTokenConverter())
                 .tokenEnhancer(tokenEnhancerChain);
    }

    /* Este metodo es para configurar los clientes que consumiran este servicio, con clientes
     * me refiero a aplicaciones, ejemplo la aplicacion movil es un cliente y la desktop es otro. */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(env.getProperty("config.security.oauth.client.1.id"))
                          .secret(passwordEncoder.encode(env.getProperty("config.security.oauth.client.1.secret")))
                          .scopes("read", "write")
                          .authorizedGrantTypes("password", "refresh_token")
                          .accessTokenValiditySeconds(3600)
                          .refreshTokenValiditySeconds(3600).
                           and()
                          .withClient(env.getProperty("config.security.oauth.client.2.id"))
                          .secret(passwordEncoder.encode(env.getProperty("config.security.oauth.client.2.secret")))
                          .scopes("read", "write")
                          .authorizedGrantTypes("password", "refresh_token")
                          .accessTokenValiditySeconds(3600)
                          .refreshTokenValiditySeconds(3600);
                          
        
    }
    
    /* Este metodo es para configurar quien puede acceder a la ruta de /oauth/token
     * y poder generar un token siempre y cuando ingrese las credenciales correctas */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }
    
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
    
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(env.getProperty("config.security.oauth.jwt.generalkey"));
        return tokenConverter;
    }
    
    
}
