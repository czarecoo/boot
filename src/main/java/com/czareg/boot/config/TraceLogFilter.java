package com.czareg.boot.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
@Component
public class TraceLogFilter extends OncePerRequestFilter {

    private static final List<String> WHITE_LISTED_HEADERS = List.of("Accept", "Accept-Encoding", "Host");
    private static final List<String> BLACK_LISTED_PATH_STARTING_POINT = List.of("/swagger-ui", "/v3/api-docs", "/actuator");
    public static final String MESSAGE_TEMPLATE = "Method=[%s], path=[%s], status=[%s], params=[%s], headers=[%s]";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        filterChain.doFilter(request, response);
        var message = String.format(MESSAGE_TEMPLATE, request.getMethod(), request.getRequestURI(), response.getStatus(),
                buildParameterMap(request), buildHeadersMap(request));
        log.info(message);
    }

    private Map<String, List<String>> buildParameterMap(HttpServletRequest request) {
        return request.getParameterMap()
                .entrySet()
                .stream()
                .collect(toMap(Map.Entry::getKey, entry -> List.of(entry.getValue())));
    }

    private Map<String, String> buildHeadersMap(HttpServletRequest request) {
        return WHITE_LISTED_HEADERS.stream()
                .filter(headerName -> hasText(request.getHeader(headerName)))
                .collect(toMap(Function.identity(), request::getHeader));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return BLACK_LISTED_PATH_STARTING_POINT.stream().anyMatch(requestURI::startsWith);
    }
}
