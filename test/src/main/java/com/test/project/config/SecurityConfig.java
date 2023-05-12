package com.test.project.config;

import com.test.project.member.repository.MemberRepository;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
//    @Autowired
//    private OAuth2UserService oAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeHttpRequests(request -> request.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/css/**", "/js/**", "/img/**,/topbottom/**").permitAll().requestMatchers("/**").permitAll()
				.requestMatchers("/**").hasAnyRole("USER", "ADMIN").anyRequest().authenticated()
        );

        http.formLogin().loginPage("/login")
                .loginProcessingUrl("/loginProcess")
                .defaultSuccessUrl("/").failureUrl("/login/error").usernameParameter("email")
              /*  .and().oauth2Login().loginPage("login/oauth2/kakao").defaultSuccessUrl("/")*/
                        /*oauth2login -> oauth2login.loginPage("/login").userInfoEndpoint(
                                userInfoEndpoint -> userInfoEndpoint.userService(oAuth2UserService)
                        )
                )*/
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true)
                .permitAll();



//        http.oauth2Login().defaultSuccessUrl("/");
        return http.build();


    }


    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

