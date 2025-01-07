package com.example.csrf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthorizationServerConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        var authorizationServerConfigurer = OAuth2AuthorizationServerConfigurer.authorizationServer();
        return http
                .securityMatcher("/**")
                .with(authorizationServerConfigurer, authorizationServer ->
                        authorizationServer.registeredClientRepository(new RegisteredClientRepository() {
                            @Override
                            public void save(RegisteredClient registeredClient) {
                                // nothing to do
                            }

                            @Override
                            public RegisteredClient findById(String id) {
                                return null;
                            }

                            @Override
                            public RegisteredClient findByClientId(String clientId) {
                                return null;
                            }
                        }))
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/custom-endpoint").permitAll()
                                .anyRequest().authenticated())
//                .csrf(csrfConfigurer -> csrfConfigurer.ignoringRequestMatchers("/**"))
                .csrf(AbstractHttpConfigurer::disable)
                .requestCache(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .build();
    }

}
