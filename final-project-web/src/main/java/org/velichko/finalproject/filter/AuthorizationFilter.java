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
//import static org.velichko.finalproject.command.PageName.*;
//import static org.velichko.finalproject.command.ParamName.USER_PARAM;
//
//@WebFilter("/*")
//public class AuthorizationFilter implements Filter {
//    private static final Set<String> PUBLIC_PATH = Set.of("/pages/login.jsp", "/pages/registration.jsp", INDEX_PAGE);
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        var uri = ((HttpServletRequest) servletRequest).getRequestURI();
//        if (isPublicPath(uri) || isUserLoggedIn(servletRequest)) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            reject(servletRequest, servletResponse);
//        }
//    }
//
//    private boolean isUserLoggedIn(ServletRequest servletRequest) {
//        var user = (User) ((HttpServletRequest) servletRequest).getSession().getAttribute(USER_PARAM);
//        return user != null;
//    }
//
//    private boolean isPublicPath(String uri) {
//        for (String prefix : PUBLIC_PATH) {
//            if (uri.contains(prefix)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private void reject(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
//        var prevPage = ((HttpServletRequest) servletRequest).getHeader(REFERER);
//        ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : "pages/login.jsp");
//    }
//}
//
