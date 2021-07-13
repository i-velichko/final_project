package org.velichko.finalproject.controller;

import org.velichko.finalproject.command.PageName;

public class Router {
    public enum RouterType {
        FORWARD, REDIRECT
    }

    private String pagePath = PageName.INDEX_PAGE;
    private RouterType routerType = RouterType.FORWARD;

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
}
