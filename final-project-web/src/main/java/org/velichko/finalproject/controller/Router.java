package org.velichko.finalproject.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.velichko.finalproject.controller.command.PageName;

public class Router {

    public enum RouterType {
        FORWARD, REDIRECT
    }

    private String pagePath = PageName.INDEX_PAGE;
    private RouterType routerType = RouterType.FORWARD;
    private Integer errorCode = null;

    public Router() {
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public RouterType getRouterType() {
        return routerType;
    }

    public void setRouterType(RouterType routerType) {
        this.routerType = routerType;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public boolean hasError() {
        return errorCode != null;
    }
}
