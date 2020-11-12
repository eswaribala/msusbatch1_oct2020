package com.buddhi.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

//http://localhost:8100/oauth/token?grant_type=client_credentials
//Basic Authorization username client_a, password_a
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	 @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	        clients
	                .inMemory()
	            .withClient("client_a")
	            .secret(passwordEncoder().encode("password_a"))
	            .authorities("ROLE_A")
	            .scopes("all")
	            .authorizedGrantTypes("client_credentials")
	            .and()
	            .withClient("client_b")
	            .secret(passwordEncoder().encode("password_b"))
	            .authorities("ROLE_B")
	            .scopes("all")
	            .authorizedGrantTypes("client_credentials");
	   }

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
    }

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(4);
    }
}
