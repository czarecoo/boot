package com.czareg.boot.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(OutputCaptureExtension.class)
class TraceLogFilterTest {

    HttpServletRequest request;
    HttpServletResponse response;
    FilterChain filterChain;

    @BeforeEach
    void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);
    }

    @Test
    void shouldLogCorrectMessageWhenFilterIsUsed(CapturedOutput output) throws ServletException, IOException {
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestURI()).thenReturn("/inputs");
        when(response.getStatus()).thenReturn(200);
        when(request.getParameterMap()).thenReturn(Map.of("size", new String[]{"5"}));
        when(request.getHeader("Accept")).thenReturn("*/*");

        new TraceLogFilter().doFilterInternal(request, response, filterChain);

        String expected = "Method=[GET], path=[/inputs], status=[200], params=[{size=[5]}], headers=[{Accept=*/*}]";
        assertThat(output.getOut(), containsString(expected));
    }

    @Test
    void shouldNotFilterRequestWhenPathStartsWithActuator() {
        when(request.getRequestURI()).thenReturn("/actuator/health");

        boolean result = new TraceLogFilter().shouldNotFilter(request);

        assertTrue(result);
    }

    @Test
    void shouldNotFilterRequestWhenPathStartsWithSwagger() {
        when(request.getRequestURI()).thenReturn("/swagger-ui/");

        boolean result = new TraceLogFilter().shouldNotFilter(request);

        assertTrue(result);
    }

    @Test
    void shouldFilterRequestWhenPathStartsWithInputs() {
        when(request.getRequestURI()).thenReturn("/inputs");

        boolean result = new TraceLogFilter().shouldNotFilter(request);

        assertFalse(result);
    }
}