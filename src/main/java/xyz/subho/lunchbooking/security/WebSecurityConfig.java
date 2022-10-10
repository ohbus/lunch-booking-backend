package xyz.subho.lunchbooking.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import xyz.subho.lunchbooking.controllers.EndpointPropertyKey;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

  @Value("${app.security.cors.url}")
  private String corsUrl;

  @Bean
  public SecurityFilterChain securityFilterChain(
      HttpSecurity httpSecurity,
      JwtRequestFilter jwtRequestFilter,
      JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint)
      throws Exception {

    httpSecurity
        .cors()
        .and()
        .csrf()
        .disable()
        // dont authenticate this particular request
        .authorizeRequests()
        .antMatchers(EndpointPropertyKey.LOGIN_USER_REGISTRATION, EndpointPropertyKey.LOGIN_USER)
        .permitAll()
        .
        // all other requests need to be authenticated
        anyRequest()
        .authenticated()
        .and()
        .
        // make sure we use stateless session; session won't be used to
        // store user's state.
        exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Add a filter to validate the tokens with every request
    httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    return httpSecurity.build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration(corsUrl, new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }
}
