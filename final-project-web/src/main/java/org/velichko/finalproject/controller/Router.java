package org.velichko.finalproject.controller;

import org.velichko.finalproject.controller.command.PageName;

/**
 * @author Ivan Velichko
 *
 * The type Router.
 */
public class Router {

    /**
     * author Ivan Velichko
     * <p>
     * The enum Router type.
     */
    public enum RouterType {
        /**
         * Forward router type.
         */
        FORWARD,
        /**
         * Redirect router type.
         */
        REDIRECT
    }

    private String pagePath = PageName.INDEX_PAGE;
    private RouterType routerType = RouterType.FORWARD;
    private Integer errorCode = null;

    /**
     * Instantiates a new Router.
     */
    public Router() {
    }

    /**
     * Gets page path.
     *
     * @return the page path
     */
    public String getPagePath() {
        return pagePath;
    }

    /**
     * Sets page path.
     *
     * @param pagePath the page path
     */
    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    /**
     * Gets router type.
     *
     * @return the router type
     */
    public RouterType getRouterType() {
        return routerType;
    }

    /**
     * Sets router type.
     *
     * @param routerType the router type
     */
    public void setRouterType(RouterType routerType) {
        this.routerType = routerType;
    }

    /**
     * Gets error code.
     *
     * @return the error code
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * Sets error code.
     *
     * @param errorCode the error code
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Has error boolean.
     *
     * @return the boolean
     */
    public boolean hasError() {
        return errorCode != null;
    }
}
