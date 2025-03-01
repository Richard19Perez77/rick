package com.rick.webservice.rick;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

/*
 * Logging all incoming requests before the controller processes them. 
 * 
 * Registered by Component and Filter automatically.
 * 
 * Filter interface provides doFilter() which, intercepts requests before they hit controllers. Intercepts responses before they are sent to clients. Since doFilter() is required by Filter, the servlet container automatically calls it.
 * 
 * Spring Boot registers the filter, and the servlet container executes it for every request.
 */
@Component
public class RequestLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    private final UserRequestLogRepository logRepository;

    public RequestLoggingFilter(UserRequestLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String method = req.getMethod();
        String url = req.getRequestURL().toString();
        String ipAddress = req.getRemoteAddr();

        // Collect headers
        Map<String, String> headersMap = new HashMap<>();
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headersMap.put(headerName, req.getHeader(headerName));
        }

        // Convert headers to JSON
        String headersJson = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(headersMap);

        // Save log entry
        UserRequestLog logEntry = new UserRequestLog(method, url, ipAddress, headersJson, "{}");
        // logRepository.save(logEntry);
        logger.info("Logged Request: {} {} from {}", method, url, ipAddress);

        logger.info("=== Incoming Request ===");
        logger.info("Method: {}", req.getMethod());
        logger.info("URL: {}", req.getRequestURL());
        logger.info("Query Params: {}", req.getQueryString());
        logger.info("Remote Address: {}", req.getRemoteAddr());

        // Log Headers
        headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            logger.info("Header: {} = {}", headerName, req.getHeader(headerName));
        }

        // Log Request Parameters
        req.getParameterMap().forEach((key, value) -> {
            logger.info("Param: {} = {}", key, String.join(",", value));
        });

        chain.doFilter(request, response);
    }
}
