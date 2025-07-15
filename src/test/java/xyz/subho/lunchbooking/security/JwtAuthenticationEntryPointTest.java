/*
 * Lunch Booking - Lunch Booking REST Application
 * Copyright © 2022 Subhrodip Mohanta (hello@subho.xyz)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package xyz.subho.lunchbooking.security;

import static org.mockito.Mockito.mock;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

@ContextConfiguration(classes = {JwtAuthenticationEntryPoint.class})
@ExtendWith(SpringExtension.class)
class JwtAuthenticationEntryPointTest {
  @Autowired private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  /**
   * Method under test: {@link JwtAuthenticationEntryPoint#commence(HttpServletRequest,
   * HttpServletResponse, AuthenticationException)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testCommence() throws IOException, ServletException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "org.apache.coyote.Response.isCommitted()"
    // because the return value of "org.apache.catalina.connector.Response.getCoyoteResponse()" is
    // null
    //       at org.apache.catalina.connector.Response.isCommitted(Response.java:619)
    //       at org.apache.catalina.connector.Response.sendError(Response.java:1304)
    //       at
    // xyz.subho.lunchbooking.security.JwtAuthenticationEntryPoint.commence(JwtAuthenticationEntryPoint.java:41)

    MockHttpServletRequest request = new MockHttpServletRequest();
    Response response = new Response();
    jwtAuthenticationEntryPoint.commence(request, response, new AccountExpiredException("Msg"));
  }

  /**
   * Method under test: {@link JwtAuthenticationEntryPoint#commence(HttpServletRequest,
   * HttpServletResponse, AuthenticationException)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testCommence2() throws IOException, ServletException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "org.apache.coyote.Response.isCommitted()"
    // because the return value of "org.apache.catalina.connector.Response.getCoyoteResponse()" is
    // null
    //       at org.apache.catalina.connector.Response.isCommitted(Response.java:619)
    //       at org.apache.catalina.connector.Response.sendError(Response.java:1304)
    //       at
    // xyz.subho.lunchbooking.security.JwtAuthenticationEntryPoint.commence(JwtAuthenticationEntryPoint.java:41)

    DefaultMultipartHttpServletRequest request = mock(DefaultMultipartHttpServletRequest.class);
    Response response = new Response();
    jwtAuthenticationEntryPoint.commence(request, response, new AccountExpiredException("Msg"));
  }

  /**
   * Method under test: {@link JwtAuthenticationEntryPoint#commence(HttpServletRequest,
   * HttpServletResponse, AuthenticationException)}
   */
  @Test
  void testCommence3() throws IOException, ServletException {
    // TODO: Complete this test.

    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();
    jwtAuthenticationEntryPoint.commence(request, response, new AccountExpiredException("Msg"));
  }
}
