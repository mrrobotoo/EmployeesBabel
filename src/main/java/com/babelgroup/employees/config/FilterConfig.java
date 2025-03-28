package com.babelgroup.employees.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for registering servlet filters.
 * This class registers a logging filter that intercepts requests to the API.
 */
@Configuration
public class FilterConfig {

    /**
     * Registers the {@link RequestLoggingFilter} to log incoming API requests.
     *
     * @return A {@link FilterRegistrationBean} configured with the logging filter.
     */
    @Bean
     FilterRegistrationBean<RequestLoggingFilter> loggingFilter() {
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLoggingFilter());
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }
}
