package org.velichko.finalproject.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.command.PageName;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;

import java.io.IOException;

import static org.velichko.finalproject.controller.command.PageName.MAIN_PAGE;
import static org.velichko.finalproject.controller.command.ParamName.USER_PARAM;

@WebFilter("/pages/*")
public class UnsafeUserFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        User user = (User) ((HttpServletRequest) servletRequest).getSession().getAttribute(USER_PARAM);
        if (user == null || user.getRole() != UserRole.ADMIN) {
            logger.warn("Unsafe page opening detected. Redirecting to main page from {}", ((HttpServletRequest) servletRequest).getRequestURI());
            servletRequest.getRequestDispatcher(MAIN_PAGE).forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
