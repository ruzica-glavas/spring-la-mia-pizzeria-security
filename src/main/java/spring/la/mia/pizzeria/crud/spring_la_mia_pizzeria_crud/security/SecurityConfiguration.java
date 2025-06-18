package spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SecurityConfiguration {


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/books/create", "/books/edit/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/books/**").hasAuthority("ADMIN")
                .requestMatchers("/categories", "/categories/**").hasAuthority("ADMIN")
                .requestMatchers("/books", "/books/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/**").permitAll()
                )
                .formLogin(withDefaults())
                .logout(withDefaults())
                .exceptionHandling(withDefaults());

        return http.build();             
    }

    @Bean
    DatabaseUserDetailService userDetailService(){
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider autheProvider = new DaoAuthenticationProvider();
        autheProvider.setUserDetailsService(userDetailService());
        autheProvider.setPasswordEncoder(passwordEncoder());
        return autheProvider;
    }


}
