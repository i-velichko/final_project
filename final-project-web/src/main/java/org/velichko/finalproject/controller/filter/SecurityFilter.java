package org.velichko.finalproject.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.velichko.finalproject.controller.command.CommandProvider;
import org.velichko.finalproject.controller.command.PageName;
import org.velichko.finalproject.controller.command.ParamName;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;

import java.io.IOException;
import java.util.List;

import static org.velichko.finalproject.logic.entity.type.UserRole.ANONYMOUS;

@WebFilter("/controller")
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        User user = (User) request.getSession().getAttribute(ParamName.USER_PARAM);
        List<UserRole> commandAccessLevel = CommandProvider.getInstance().getCommandAccessLevel(request);
        UserRole role = user != null ? user.getRole() : ANONYMOUS;
        if (!commandAccessLevel.contains(role)) {
            request.setAttribute("msg", "Время сессии истекло или у вас недостаточно прав для просмотра данной страницы. Вернуться на главную <a href=http://localhost:8080/final_project_web_war_exploded/controller?command=to_main_page>Home</a>");
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
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
