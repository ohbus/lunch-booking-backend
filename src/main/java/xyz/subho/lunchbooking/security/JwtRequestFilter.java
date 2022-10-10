package xyz.subho.lunchbooking.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import io.micrometer.core.instrument.util.StringUtils;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.subho.lunchbooking.services.LoginService;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

  private static final String AUTHORIZATION = "Authorization";
  private static final String AUTHORIZATION_BEARER = "Bearer ";

  @Autowired private JwtHelper jwtHelper;

  @Autowired private LoginService loginService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    final String requestTokenHeader = request.getHeader(AUTHORIZATION);

    String username = null;
    String jwtToken = null;

    if (StringUtils.isNotBlank(requestTokenHeader)
        && requestTokenHeader.startsWith(AUTHORIZATION_BEARER)) {
      jwtToken = requestTokenHeader.substring(7);
      try {
        username = jwtHelper.extractUsername(jwtToken);
      } catch (IllegalArgumentException e) {
        log.error("Unable to get JWT Token");
      } catch (JWTDecodeException e) {
        log.error("JWT Decode Error");
      }
    } else {
      log.warn("JWT does not being with 'Bearer ' String");
    }

    if (StringUtils.isNotBlank(username)
        && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {

      var currentUserDetails = loginService.getUserByUsername(username);

      if (jwtHelper.validateToken(jwtToken, currentUserDetails)) {

        var authToken =
            new UsernamePasswordAuthenticationToken(
                currentUserDetails, null, currentUserDetails.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
        log.info("AUTHORIZATION SUCCESS for 'username': {}", username);
      } else {
        log.error("INVALID JWT: {} for 'username': {}", jwtToken, username);
      }
    } else {
      log.debug("CANNOT perform AUTHORIZATION as 'username' NOT PRESENT");
    }

    filterChain.doFilter(request, response);
  }
}
