package com.CHRESTAPI.todolist.config;

import com.CHRESTAPI.todolist.config.jwtconfig.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig   {

    private final JwtAuthenticationFilter jwtAuthFilter ;
    private final  AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {



       httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/v1/**").permitAll()
                .antMatchers("/api/v1/task/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/user/**").permitAll()
                .antMatchers("**/home/**").permitAll()
                .antMatchers("**/getusers/**").hasAnyRole("USER","ADMIN")
                .antMatchers("**/getuserbyid/**").hasAnyRole("USER","ADMIN")
                .antMatchers("**/createuser/**").hasAnyRole("USER","ADMIN")
                .antMatchers("**/updateuser/**").hasAnyRole("USER","ADMIN")
                .antMatchers("**/findbytasklist/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("**/findbytaskstatus/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("**/findbydate/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("**/findbydatetimereminderbetween/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("**/findbytaskpriority/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("**/findbycategory/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("**/findbytagsin/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(headers -> headers.frameOptions().sameOrigin());

       return httpSecurity.build();

    }



}
