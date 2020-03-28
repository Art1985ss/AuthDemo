package com.auth.AuthDemo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Autowired
    private SimpleAuthenticationSuccessHandler successHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select name, password, enabled from user where name = ?")
                .authoritiesByUsernameQuery("select s.name, a.authority from authorities a\n" +
                        "inner join user s on a.user_id = s.id\n" +
                        "where s.name = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/home"))
                .and().authorizeRequests()
                .antMatchers("/user/**").hasRole("STUDENT")
                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/").permitAll()
                .and().formLogin().successHandler(successHandler)
                .loginPage("/home").and().logout().permitAll();
//        http.formLogin().defaultSuccessUrl("/user").failureUrl("/test");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
