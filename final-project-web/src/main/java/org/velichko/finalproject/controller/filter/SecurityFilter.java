package org.velichko.finalproject.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.controller.command.CommandProvider;
import org.velichko.finalproject.controller.command.PageName;
import org.velichko.finalproject.controller.command.ParamName;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;

import java.io.IOException;
import java.util.List;

import static org.velichko.finalproject.logic.entity.type.UserRole.*;

@WebFilter("/controller")
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        User user = (User) request.getSession().getAttribute(ParamName.USER_PARAM);
        UserRole role;
        List<UserRole> commandAccessLevel = CommandProvider.getInstance().getCommandAccessLevel(request);
        role = user != null ? user.getRole() : ANONYMOUS;
        if (!commandAccessLevel.contains(role)) {
            request.getRequestDispatcher(PageName.ERROR_PAGE).forward(servletRequest, servletResponse);
        }
        chain.doFilter(servletRequest, servletResponse);

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
