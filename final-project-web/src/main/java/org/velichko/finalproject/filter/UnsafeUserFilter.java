package org.velichko.finalproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.entity.User;

import java.io.IOException;

@WebFilter(urlPatterns = {"/trainer", "/user", "/examinator"})
public class UnsafeUserFilter implements Filter {

    private static Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        User user = (User) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        if (user != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            logger.warn("Unsafe page opening detected. Redirecting to registration page from {}", ((HttpServletRequest) servletRequest).getRequestURI());
            ((HttpServletResponse) servletResponse).sendRedirect("pages/registration.jsp");
        }
    }
}
