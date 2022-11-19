package xyz.subho.lunchbooking.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

@ContextConfiguration(classes = {JwtRequestFilter.class})
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
class JwtRequestFilterTest {
    @MockBean
    private JwtHelper jwtHelper;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    /**
     * Method under test: {@link JwtRequestFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal() throws IOException, ServletException {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtRequestFilter.doFilterInternal(mockHttpServletRequest, response, filterChain);
        verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        assertFalse(mockHttpServletRequest.isRequestedSessionIdFromURL());
        assertTrue(mockHttpServletRequest.isRequestedSessionIdFromCookie());
        assertFalse(mockHttpServletRequest.isAsyncSupported());
        assertFalse(mockHttpServletRequest.isAsyncStarted());
        assertTrue(mockHttpServletRequest.isActive());
        assertTrue(mockHttpServletRequest.getSession() instanceof MockHttpSession);
        assertEquals("", mockHttpServletRequest.getServletPath());
        assertEquals(80, mockHttpServletRequest.getServerPort());
        assertEquals("localhost", mockHttpServletRequest.getServerName());
        assertEquals("http", mockHttpServletRequest.getScheme());
        assertEquals("", mockHttpServletRequest.getRequestURI());
        assertEquals(80, mockHttpServletRequest.getRemotePort());
        assertEquals("localhost", mockHttpServletRequest.getRemoteHost());
        assertEquals("HTTP/1.1", mockHttpServletRequest.getProtocol());
        assertEquals("", mockHttpServletRequest.getMethod());
        assertEquals(80, mockHttpServletRequest.getLocalPort());
        assertEquals("localhost", mockHttpServletRequest.getLocalName());
        assertTrue(mockHttpServletRequest.getInputStream() instanceof DelegatingServletInputStream);
        assertEquals(DispatcherType.REQUEST, mockHttpServletRequest.getDispatcherType());
        assertEquals("", mockHttpServletRequest.getContextPath());
        assertEquals(-1L, mockHttpServletRequest.getContentLengthLong());
    }

    /**
     * Method under test: {@link JwtRequestFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDoFilterInternal2() throws IOException, ServletException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "javax.servlet.http.HttpServletRequest.getHeader(String)" because "request" is null
        //       at xyz.subho.lunchbooking.security.JwtRequestFilter.doFilterInternal(JwtRequestFilter.java:51)
        //   See https://diff.blue/R013 to resolve this issue.

        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtRequestFilter.doFilterInternal(null, response, filterChain);
    }

    /**
     * Method under test: {@link JwtRequestFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal3() throws IOException, ServletException {
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("https://example.org/example");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtRequestFilter.doFilterInternal(defaultMultipartHttpServletRequest, response, filterChain);
        verify(defaultMultipartHttpServletRequest).getHeader((String) any());
        verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
    }

    /**
     * Method under test: {@link JwtRequestFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal4() throws IOException, ServletException {
        when(jwtHelper.getAuthenticatedUserDetails((String) any(), (String) any()))
                .thenReturn(new LunchBookingPrincipal());
        when(jwtHelper.validateToken((String) any())).thenReturn(true);
        when(jwtHelper.extractUsername((String) any())).thenReturn("janedoe");
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getRemoteAddr()).thenReturn("42 Main St");
        when(defaultMultipartHttpServletRequest.getSession(anyBoolean())).thenReturn(new MockHttpSession());
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtRequestFilter.doFilterInternal(defaultMultipartHttpServletRequest, response, filterChain);
        verify(jwtHelper).validateToken((String) any());
        verify(jwtHelper).extractUsername((String) any());
        verify(jwtHelper).getAuthenticatedUserDetails((String) any(), (String) any());
        verify(defaultMultipartHttpServletRequest).getRemoteAddr();
        verify(defaultMultipartHttpServletRequest).getHeader((String) any());
        verify(defaultMultipartHttpServletRequest).getSession(anyBoolean());
        verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
    }

    /**
     * Method under test: {@link JwtRequestFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDoFilterInternal5() throws IOException, ServletException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.RuntimeException
        //       at xyz.subho.lunchbooking.security.JwtRequestFilter.doFilterInternal(JwtRequestFilter.java:83)
        //   See https://diff.blue/R013 to resolve this issue.

        when(jwtHelper.getAuthenticatedUserDetails((String) any(), (String) any()))
                .thenReturn(new LunchBookingPrincipal());
        when(jwtHelper.validateToken((String) any())).thenReturn(true);
        when(jwtHelper.extractUsername((String) any())).thenReturn("janedoe");
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getRemoteAddr()).thenThrow(new RuntimeException());
        when(defaultMultipartHttpServletRequest.getSession(anyBoolean())).thenThrow(new RuntimeException());
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtRequestFilter.doFilterInternal(defaultMultipartHttpServletRequest, response, filterChain);
    }

    /**
     * Method under test: {@link JwtRequestFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDoFilterInternal6() throws IOException, ServletException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "xyz.subho.lunchbooking.security.LunchBookingPrincipal.getAuthorities()" because "authDetails" is null
        //       at xyz.subho.lunchbooking.security.JwtRequestFilter.doFilterInternal(JwtRequestFilter.java:81)
        //   See https://diff.blue/R013 to resolve this issue.

        when(jwtHelper.getAuthenticatedUserDetails((String) any(), (String) any())).thenReturn(null);
        when(jwtHelper.validateToken((String) any())).thenReturn(true);
        when(jwtHelper.extractUsername((String) any())).thenReturn("janedoe");
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getRemoteAddr()).thenReturn("42 Main St");
        when(defaultMultipartHttpServletRequest.getSession(anyBoolean())).thenReturn(new MockHttpSession());
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtRequestFilter.doFilterInternal(defaultMultipartHttpServletRequest, response, filterChain);
    }

    /**
     * Method under test: {@link JwtRequestFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal7() throws IOException, ServletException {
        LunchBookingPrincipal lunchBookingPrincipal = mock(LunchBookingPrincipal.class);
        when((Set<GrantedAuthority>) lunchBookingPrincipal.getAuthorities()).thenReturn(new HashSet<>());
        when(jwtHelper.getAuthenticatedUserDetails((String) any(), (String) any())).thenReturn(lunchBookingPrincipal);
        when(jwtHelper.validateToken((String) any())).thenReturn(true);
        when(jwtHelper.extractUsername((String) any())).thenReturn("janedoe");
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getRemoteAddr()).thenReturn("42 Main St");
        when(defaultMultipartHttpServletRequest.getSession(anyBoolean())).thenReturn(new MockHttpSession());
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtRequestFilter.doFilterInternal(defaultMultipartHttpServletRequest, response, filterChain);
        verify(jwtHelper).validateToken((String) any());
        verify(jwtHelper).extractUsername((String) any());
        verify(jwtHelper).getAuthenticatedUserDetails((String) any(), (String) any());
        verify(lunchBookingPrincipal).getAuthorities();
        verify(defaultMultipartHttpServletRequest).getRemoteAddr();
        verify(defaultMultipartHttpServletRequest).getHeader((String) any());
        verify(defaultMultipartHttpServletRequest).getSession(anyBoolean());
        verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
    }

    /**
     * Method under test: {@link JwtRequestFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal8() throws IOException, ServletException {
        when(jwtHelper.getAuthenticatedUserDetails((String) any(), (String) any()))
                .thenReturn(mock(LunchBookingPrincipal.class));
        when(jwtHelper.validateToken((String) any())).thenReturn(false);
        when(jwtHelper.extractUsername((String) any())).thenReturn("janedoe");
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getRemoteAddr()).thenReturn("42 Main St");
        when(defaultMultipartHttpServletRequest.getSession(anyBoolean())).thenReturn(new MockHttpSession());
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtRequestFilter.doFilterInternal(defaultMultipartHttpServletRequest, response, filterChain);
        verify(jwtHelper).validateToken((String) any());
        verify(jwtHelper).extractUsername((String) any());
        verify(defaultMultipartHttpServletRequest).getHeader((String) any());
        verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
    }

    /**
     * Method under test: {@link JwtRequestFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal9() throws IOException, ServletException {
        when(jwtHelper.getAuthenticatedUserDetails((String) any(), (String) any()))
                .thenReturn(mock(LunchBookingPrincipal.class));
        when(jwtHelper.validateToken((String) any())).thenReturn(true);
        when(jwtHelper.extractUsername((String) any())).thenReturn("");
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getRemoteAddr()).thenReturn("42 Main St");
        when(defaultMultipartHttpServletRequest.getSession(anyBoolean())).thenReturn(new MockHttpSession());
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtRequestFilter.doFilterInternal(defaultMultipartHttpServletRequest, response, filterChain);
        verify(jwtHelper).extractUsername((String) any());
        verify(defaultMultipartHttpServletRequest).getHeader((String) any());
        verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
    }
}

