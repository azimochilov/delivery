package com.delivery.fastfood.configs;

import com.delivery.fastfood.securities.JwtConfigurer;
import com.delivery.fastfood.securities.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    public SecurityConfig(@Lazy UserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return  super.authenticationManager();
    }
    @Override
    protected void  configure(HttpSecurity http) throws  Exception{
        http
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/orders/**").hasAnyRole("ADMIN","WAITER")
                .antMatchers("/api/products/**").hasAnyRole("ADMIN","WAITER")
//                .antMatchers("/api/product/{id}").hasAnyRole("ADMIN","WAITER")
//                .antMatchers("/api/product/{name}").hasAnyRole("ADMIN","WAITER")
                .antMatchers("/api/products").permitAll()
                .antMatchers("/api/order/items").permitAll()
                .antMatchers("/api/orders").permitAll()
//                .antMatchers("/api/order/status/reject").hasAnyRole("ADMIN","WAITER")
//                .antMatchers("/api/order/status/accept").hasAnyRole("ADMIN","WAITER")
//                .antMatchers("/api/order/status/deliver").hasAnyRole("ADMIN","WAITER")
//                .antMatchers("/api/orders").hasAnyRole("ADMIN","WAITER")
//                .antMatchers("/api/orders/false").hasAnyRole("ADMIN","WAITER")
//                .antMatchers("/api/orders/true").hasAnyRole("ADMIN","WAITER")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
