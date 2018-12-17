package com.stackoverflow.nullpointer.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * <p> Requests com.stackoverflow.nullpointer.filter </p>
 * @author naman.nigam
 */
public class RequestFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {

        filterChain.doFilter(servletRequest, servletResponse);

    }

    public void destroy() {

    }
}