package kg.attractor.headhunter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/login")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/register")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/vacancies")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/forgot_password")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/reset_password")).permitAll()

//                        .requestMatchers(HttpMethod.PUT, "/accounts").fullyAuthenticated()
//                        .requestMatchers(HttpMethod.POST, "/accounts/avatar").fullyAuthenticated()
//
//                        .requestMatchers(HttpMethod.GET, "/resumes/applicant").hasAuthority("APPLICANT")
//                        .requestMatchers(HttpMethod.POST, "/resumes").hasAuthority("APPLICANT")
//                        .requestMatchers(HttpMethod.PUT, "/resumes/**").hasAuthority("APPLICANT")
//                        .requestMatchers(HttpMethod.GET, "/resumes/**").hasAuthority("EMPLOYER")
//                        .requestMatchers(HttpMethod.DELETE, "/resumes/**").hasAuthority("APPLICANT")
//
//                        .requestMatchers(HttpMethod.GET, "/vacancies/employer").hasAuthority("EMPLOYER")
//                        .requestMatchers(HttpMethod.GET, "/vacancies/{vacancyId}/respondents").hasAuthority("EMPLOYER")
//                        .requestMatchers(HttpMethod.GET, "/vacancies//{id}/responded-vacancies").hasAuthority("EMPLOYER")
//                        .requestMatchers(HttpMethod.POST, "/vacancies").hasAuthority("EMPLOYER")
//                        .requestMatchers(HttpMethod.PUT, "/vacancies/**").hasAuthority("EMPLOYER")
//                        .requestMatchers(HttpMethod.GET, "/vacancies/**").hasAuthority("APPLICANT")

                        .anyRequest().authenticated())
                .exceptionHandling(Customizer.withDefaults());

//                .exceptionHandling(exception -> exception
//                        .accessDeniedHandler(customAccessDeniedHandler()));
        return http.build();
    }

    @Bean
    public AccessDeniedHandler customAccessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json;charset=UTF-8");

            Map<String, Object> data = new HashMap<>();
            data.put("error", "Недостаточно прав для доступа к этому ресурсу");

            response.getWriter().write(new ObjectMapper().writeValueAsString(data));
        };
    }
}
