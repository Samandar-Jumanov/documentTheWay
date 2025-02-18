package com.dtw.config;


import com.dtw.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class Security {


    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity  http ) throws  Exception {
         http.csrf().disable()
                 .authorizeHttpRequests(authorize -> {
                       authorize.anyRequest().authenticated();
                 }).httpBasic(Customizer.withDefaults());

         return  http.build();
    }

    @Bean
    public  static   PasswordEncoder passwordEncoder (){
        return  new BCryptPasswordEncoder();
    }

//   public  UserDetailsService userDetails( ){
//
//        UserDetails user = User.builder().
//                username("@lockUser")
//                .password(passwordEncoder().encode("password"))
//                .fullName("Samandar Jumanov")
//                .build();
//
//
//        UserDetails adminUser = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .fullName("Adminbek Admin")
//                .build();
//
//
//        return new  InMemoryUserDetailsManager(user , adminUser);
//
//    }
}
