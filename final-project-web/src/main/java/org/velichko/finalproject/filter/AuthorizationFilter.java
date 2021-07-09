//package org.velichko.finalproject.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.velichko.finalproject.logic.entity.User;
//
//import java.io.IOException;
//import java.util.Set;
//
//import static org.velichko.finalproject.command.PageConstant.*;
//import static org.velichko.finalproject.command.ParamConstant.USER_PARAM;
//
//@WebFilter("/*")
//public class AuthorizationFilter implements Filter {
//    private static final Set<String> PUBLIC_PATH = Set.of("/login", "/registration", "index");
//
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
//        if (isPublicPath(uri) || isUserLoggedIn(servletRequest)) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            ((HttpServletResponse) servletResponse).sendRedirect("/login");
//        }
//    }
//
//    private boolean isUserLoggedIn(ServletRequest servletRequest) {
//        Object user = (User) ((HttpServletRequest) servletRequest).getSession().getAttribute(USER_PARAM);
//        return user != null;
//    }
//
//    private boolean isPublicPath(String uri) {
//        for (String s : PUBLIC_PATH) {
//            if (uri.startsWith(s)) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
//
