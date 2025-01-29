package com.example.Airport.config;

import com.example.Airport.security.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Value("${api-endpoint}")
    String endpoint;

    JpaUserDetailsService jpaUserDetailsService;

    public SecurityConfiguration(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
               .cors(withDefaults())
               .csrf(csrf -> csrf.disable())
               .formLogin(form -> form.disable())
               .logout(out -> out
                       .logoutUrl(endpoint + "/logout")
                       .invalidateHttpSession(true)
                       .deleteCookies("JSESSIONID"))
               .authorizeHttpRequests(auth -> auth
                    .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                       .requestMatchers(endpoint + "/public/**").permitAll()
                       .requestMatchers(HttpMethod.GET, endpoint + "/users/get-all").hasRole("ADMIN")
                       .requestMatchers(HttpMethod.PUT, endpoint + "/user/update-role").hasRole("ADMIN")
                       .requestMatchers(endpoint + "/airport").hasRole("ADMIN")
                       .requestMatchers(HttpMethod.GET,endpoint + "/flight/{id}").hasAnyRole("ADMIN", "USER")
                       .requestMatchers(endpoint + "/flight").hasRole("ADMIN")
                       .requestMatchers(endpoint + "/profile").hasRole("ADMIN")
                       .requestMatchers(HttpMethod.GET, endpoint + "/reservation").hasRole("ADMIN")
                       .requestMatchers(HttpMethod.DELETE, endpoint + "/reservation/{id}").hasRole("ADMIN")
                       .requestMatchers(HttpMethod.GET,endpoint + "/reservation/{id}").hasAnyRole("ADMIN", "USER")
                       .requestMatchers(endpoint + "/reservation/user/{id}").hasAnyRole("ADMIN", "USER")
                       .requestMatchers(HttpMethod.POST,endpoint + "/reservation").hasAnyRole("ADMIN", "USER")
                       .requestMatchers(HttpMethod.POST,endpoint + "/reservation/confirm").hasAnyRole("ADMIN", "USER")
                       .requestMatchers(HttpMethod.POST, endpoint + "/public/register").permitAll()
                       .requestMatchers(HttpMethod.POST, endpoint + "/public/login").permitAll()
                       .anyRequest().authenticated())
               .userDetailsService(jpaUserDetailsService)
               .httpBasic(withDefaults())
               .sessionManagement(session -> session
                       .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

       http.headers(header -> header.frameOptions(frame -> frame.sameOrigin()));

       return http.build();
   }
}
