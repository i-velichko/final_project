package org.velichko.finalproject.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


/**
 * @author Ivan Velichko
 *
 * The type Xss filter.
 */

@WebFilter("/*")
public class XssFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.log(Level.TRACE,"Start Xss filter");
        filterChain.doFilter(new XssRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
    }
}
