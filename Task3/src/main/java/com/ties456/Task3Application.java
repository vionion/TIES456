package com.ties456;

import com.ties456.config.JerseyInitialization;
import com.ties456.model.user.Authority;
import com.ties456.service.user.UserService;
import com.ties456.service.user.UserServiceImpl;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@EnableAutoConfiguration
public class Task3Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Task3Application.class).run(args);
    }

    @Bean
    public ServletRegistrationBean jerseyServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/*");
        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyInitialization.class.getName());
        return registration;
    }

    @Configuration
    @EnableGlobalMethodSecurity
    @EnableWebSecurity
    static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        private UserService userService = new UserServiceImpl();

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            DigestAuthenticationEntryPoint digestAuthenticationEntryPoint = new DigestAuthenticationEntryPoint();
            digestAuthenticationEntryPoint.setKey("Super Random Key");
            digestAuthenticationEntryPoint.setRealmName("TIES456 task 4");
            digestAuthenticationEntryPoint.setNonceValiditySeconds(3600);

            DigestAuthenticationFilter digestAuthenticationFilter = new DigestAuthenticationFilter();
            digestAuthenticationFilter.setAuthenticationEntryPoint(digestAuthenticationEntryPoint);
            digestAuthenticationFilter.setUserDetailsService(userService);

            http
                    .httpBasic()
                    .and()
                    .addFilter(digestAuthenticationFilter)
                    .exceptionHandling().authenticationEntryPoint(digestAuthenticationEntryPoint)
                    .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/directors").permitAll()
                    .antMatchers(HttpMethod.GET, "/movies").permitAll()
                    .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/directors/**").hasAnyRole(Authority.USER.name(), Authority.ADMIN.name())
                    .antMatchers(HttpMethod.GET, "/movies/**").hasAnyRole(Authority.USER.name(), Authority.ADMIN.name())
                    .antMatchers(HttpMethod.POST, "/**").hasRole(Authority.ADMIN.name())
                    .antMatchers(HttpMethod.PUT, "/**").hasRole(Authority.ADMIN.name())
                    .antMatchers(HttpMethod.DELETE, "/**").hasRole(Authority.ADMIN.name())
                    .antMatchers("/users").hasRole(Authority.ADMIN.name())
                    .and().userDetailsService(userService)
                    .csrf().disable();
        }


    }

}