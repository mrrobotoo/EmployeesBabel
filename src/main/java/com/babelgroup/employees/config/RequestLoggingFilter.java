package com.babelgroup.employees.config;

import java.io.IOException;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

/**
 * A servlet filter that logs all incoming HTTP request headers.
 * <p>
 * This filter captures and logs all headers of incoming HTTP requests
 * before passing the request along the filter chain.
 * </p>
 */
public class RequestLoggingFilter implements Filter {

    /** Logger instance for logging request headers. */
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    /**
     * Logs HTTP request headers and proceeds with the filter chain.
     *
     * @param request  The incoming servlet request.
     * @param response The outgoing servlet response.
     * @param chain    The filter chain to continue processing the request.
     * @throws IOException      If an I/O error occurs during filtering.
     * @throws ServletException If an error occurs in the servlet processing.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = httpServletRequest.getHeader(headerName);
                logger.info("Header Name: {} | Header Value: {}", headerName, headerValue);
            }
        }

        // Continue the request processing
        chain.doFilter(request, response);
    }
}
