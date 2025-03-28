package com.babelgroup.employees.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.servlet.FilterChain;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class FilterConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Mock
    private FilterChain filterChain;

 
    @Test
    void testFilterRegistration() {
	    FilterRegistrationBean<?> registrationBean = applicationContext.getBean(FilterRegistrationBean.class);
	    
	    assertNotNull(registrationBean);
	    
	    assertTrue(registrationBean.getFilter() instanceof RequestLoggingFilter);
	    
	    assertTrue(registrationBean.getUrlPatterns().contains("/api/*"));
    }
}
