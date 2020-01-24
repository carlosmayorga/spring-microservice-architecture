package com.cmayorga.spring.test.zuul.oauth;


import java.util.Arrays;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /* Para inyectar properties, se puede usar environment como en el 
     * jar de oauth-service o con @value como aqui */
    @Value("${config.security.oauth.jwt.generalkey}")
    private String jwtKey;
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/v1/secure/login/oauth/token").permitAll()
                                .antMatchers(HttpMethod.GET, "/v1/item/list", "v1/product/list").permitAll()
                                .antMatchers(HttpMethod.GET,"/v1/item/{id}/amount/{amount}").hasAnyRole("PROFILE01","PROFILE05")
                                .anyRequest().authenticated()
                                /* anyRequest().authenticated() quiere decir, que cualquier ruta que no colocamos arriba, 
                                 * solicitara autenticacion */
                                .and().cors().configurationSource(corsConfigurationSource());
    }
    
    /* Bean para manejar el CORS */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("*"));
        corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE"));
        corsConfig.setAllowCredentials(true);
        corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        /* Se coloca "/**" para que se aplique a todas las rutas de los microservicios */
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }
     

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
    
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(jwtKey);
        return tokenConverter;
    }

}
