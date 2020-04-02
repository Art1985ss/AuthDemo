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

/** Security configuration class. Ensures that only authorized users are allowed to access certain URL
 * adresses.
 */

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    private SimpleAuthenticationSuccessHandler successHandler;

    /**
     * This method specifies Users and Their roles from datasource. MySQL database, which is linked to
     * the project. Authorities and their roles are predefined in database.
     * @exception Exception If attempt for unauthorized access is made, exception is thrown.
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select name, password, enabled from user where name = ?")
                .authoritiesByUsernameQuery("select s.name, a.authority from authorities a\n" +
                        "inner join user s on a.user_id = s.id\n" +
                        "where s.name = ?");
    }

    /**
     * This method specifies access right to logged in users, by setting permissions to
     * matching URLs. On login form successhandler (on successful login) is provided - admins
     * are redirected to administration html page, regular users - to users html page.
     * On logout users are sent back to home page.
     * @throws Exception If attempt for unauthorized access is made, exception is thrown.
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/home")).and()
                .authorizeRequests()
                .antMatchers("/registration/", "/create/").permitAll()
                .antMatchers("/user/**").hasRole("STUDENT")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and().formLogin().successHandler(successHandler)
                .loginPage("/home").and().logout().permitAll();
//        http.formLogin().defaultSuccessUrl("/user").failureUrl("/test");
    }

    /**
     * Method for spring security provided password encoding
     */
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
