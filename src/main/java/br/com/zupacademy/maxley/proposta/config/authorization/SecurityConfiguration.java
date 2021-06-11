package br.com.zupacademy.maxley.proposta.config.authorization;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .antMatchers(HttpMethod.GET, "/propostas/**").hasAuthority("SCOPE_acessoTotal")
                        .antMatchers(HttpMethod.GET, "/cartoes/**").hasAuthority("SCOPE_acessoTotal")
                        .antMatchers(HttpMethod.POST, "/cartoes/**").hasAuthority("SCOPE_acessoTotal")
                        .antMatchers(HttpMethod.POST, "/propostas/**").hasAuthority("SCOPE_acessoTotal")
                        .anyRequest().authenticated()
        )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
